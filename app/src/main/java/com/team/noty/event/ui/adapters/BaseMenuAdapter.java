package com.team.noty.event.ui.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.ui.models.UiMenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BaseMenuAdapter extends BaseAdapter<BaseMenuAdapter.ViewHolder> {

    private List<UiMenuItem> items;

    public BaseMenuAdapter() {
        items = new ArrayList<>();
    }

    public void setItems(List<UiMenuItem> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.menu_item;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.menu_item_icon)
        ImageView icon;
        @BindView(R.id.menu_item_text)
        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view ->
            {
                if (items.get(getAdapterPosition()).action != null)
                    items.get(getAdapterPosition()).action.call();
            });
        }

        @Override
        void bind(int pos) {
            icon.setImageResource(items.get(pos).drawable);
            textView.setText(items.get(pos).text);
        }
    }
}
