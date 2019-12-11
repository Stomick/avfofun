package com.team.noty.event.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.ui.activities.EventActivity;
import com.team.noty.event.ui.utils.ResUtils;
import com.team.noty.event.utils.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;

public class EventAdapterProfile extends FilterableListAdapter<Act, EventAdapterProfile.ViewHolder>{

    public EventAdapterProfile() {
        super(new ArrayList<>());
    }

    @Override
    public int getItemViewType(int position) {
        if (!list.get(position).isEvent)
            return R.layout.profile_comm_item;
        else return R.layout.profile_event_item;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case R.layout.profile_event_item:
                return new EventViewHolder(inflate(parent, viewType));
            case R.layout.profile_comm_item:
                return new CommViewHolder(inflate(parent, viewType));
        }
        return new ViewHolder(inflate(parent, R.layout.profile_event_item));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends BaseAdapter.ViewHolder {

        @BindView(R.id.event_photo)
        ImageView eventPhoto;
        @BindView(R.id.event_name)
        TextView eventName;
        @BindView(R.id.event_date_time)
        TextView eventDateTime;
        @BindView(R.id.event_price)
        TextView eventPrice;

        ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        void bind(int pos) {
            Act act = list.get(pos);
            ImageLoader.loadImage(act.imageUrl, eventPhoto);
            eventName.setText(act.title);
            eventDateTime.setText(act.displayDate());
            ResUtils.setPrice(act.price, eventPrice);
        }
    }

    class EventViewHolder extends ViewHolder {
        @BindView(R.id.event_rate)
        TextView eventRate;

        EventViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view ->
                    EventActivity.open(this.itemView.getContext(), list.get(getAdapterPosition()))
            );
        }

        @Override
        void bind(int pos) {
            super.bind(pos);
            Act act = list.get(pos);
            if (act.rate > 0)
                eventRate.setText(itemView.getContext().getString(R.string.overall_rating, String.valueOf(act.rate)));
            else
                eventRate.setVisibility(View.GONE);
        }
    }

    class CommViewHolder extends ViewHolder {

        @BindView(R.id.event_address)
        TextView address;
        @BindView(R.id.event_count)
        TextView count;

        CommViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(view ->
                    EventActivity.open(this.itemView.getContext(), list.get(getAdapterPosition()))
            );
        }

        @Override
        void bind(int pos) {
            super.bind(pos);
            Act act = list.get(pos);
            address.setText(act.address);
            count.setText(ResUtils.getCountText(act.count_peoples, itemView.getContext()));
        }
    }
}
