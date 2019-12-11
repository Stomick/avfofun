package evfor.fun.skvader.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.User;
import evfor.fun.skvader.utils.ImageLoader;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import java.util.List;

public class EventEnterPeopleAdapter extends ListAdapter<User,EventEnterPeopleAdapter.ViewHolder> {
    private CallBack1<String> callBack;

    public EventEnterPeopleAdapter(List<User> list) {
        super(list);
    }

    public void setCallBack(CallBack1<String> callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.user_item_photo;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            if (itemView instanceof ImageView)
                imageView = (ImageView) itemView;
            itemView.setOnClickListener(view -> {
                if(callBack!=null) callBack.call(String.valueOf(list.get(getAdapterPosition()).id));
            });
        }

        @Override
        void bind(int pos) {
            if (imageView!=null)
                ImageLoader.loadImage(list.get(pos).imageUrl, imageView);
        }
    }
}
