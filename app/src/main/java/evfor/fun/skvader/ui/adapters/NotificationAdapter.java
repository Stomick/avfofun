package evfor.fun.skvader.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Notification;

import java.util.ArrayList;

import butterknife.BindView;

public class NotificationAdapter extends FilterableListAdapter<Notification, NotificationAdapter.ViewHolder> {
    Context context;
    public NotificationAdapter() {
        super(new ArrayList<>());
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.notification_item;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.notify_ava)
        ImageView notifyAva;
        @BindView(R.id.notify_sender)
        TextView notifySender;
        @BindView(R.id.notify_time)
        TextView notifyTime;
        @BindView(R.id.notify_why)
        TextView notifyWhat;

        ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this::onClick);
        }

        @Override
        void bind(int pos) {
            Notification notification = list.get(pos);
//            ImageLoader.loadImage(notification.image_url, notifyAva);
            notifyTime.setText(notification.date);
            if(notification.image_url!=null)
                Glide.with(context).load(notification.image_url).into(notifyAva);
            notifySender.setText(notification.title);
            notifyWhat.setText(notification.message);
        }

        void onClick(View v) {
            if(!list.get(getAdapterPosition()).title.toLowerCase().contains("сключен"))
            try {
                v.getContext().startActivity(list.get(getAdapterPosition()).intent);
            }catch (Exception e)
            {}

            //EventBus.removeAll(list.get(getAdapterPosition()));
        }
    }

}
