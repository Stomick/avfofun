package com.team.noty.event.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PopularEventAdapter extends BaseAdapter<PopularEventAdapter.ViewHolder> {

    private List<Act> list;

    private CallBack1<Act> callBack;

    public PopularEventAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<Act> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void setCallBack(CallBack1<Act> callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.popular_event_item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return list.size() < 10 ? list.size() : 10;
    }

    class ViewHolder extends BaseAdapter.ViewHolder {

        @BindView(R.id.popular_item_logo)
        ImageView logo;
        @BindView(R.id.popular_item_title)
        TextView title;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                if(callBack!=null)
                    callBack.call(list.get(getAdapterPosition()));
            });
        }

        @Override
        void bind(int pos) {
            ImageLoader.loadImage(list.get(pos).imageUrl, logo);
            title.setText(list.get(pos).title);
        }
    }
}
