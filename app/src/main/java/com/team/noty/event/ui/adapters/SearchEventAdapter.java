package com.team.noty.event.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.ui.models.UiCheckedItem;
import com.team.noty.event.ui.utils.ResUtils;
import com.team.noty.event.utils.ImageLoader;
import com.team.noty.event.utils.callbacks.CallBack1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Handler;

import butterknife.BindView;
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class SearchEventAdapter extends CheckedAdapter<Act, SearchEventAdapter.ViewHolder> {

    private CallBack1<Act> callBackId;
    private CallBack1<Act> removeId;
    private CallBack1<Act> addId;
    private CallBack1<String> setTitle;
    private boolean selectMode = false;
    boolean canclick = true;
    public SearchEventAdapter() {
        super(new ArrayList<>());
    }

    public void setTitleCall(CallBack1<String> setTitle) {
        this.setTitle = setTitle;
    }

    public void removeSelected(){
        for (Iterator<UiCheckedItem<Act>> iterator = list.listIterator(); iterator.hasNext();){
            UiCheckedItem<Act> event = iterator.next();
            if (event.checked) {
                notifyItemRemoved(list.indexOf(event));
                iterator.remove();
            }
        }
        selectMode = false;
        setTitle.call("");
    }

    public boolean isSelectMode() {
        return selectMode;
    }

    public void setSelectMode(boolean selectMode) {
        this.selectMode = selectMode;
        if (!selectMode) {
            resetSelected();
        }
        if (setTitle != null)
            setTitle.call(selectMode ? "0" : "");
    }

    public void setRemoveId(CallBack1<Act> removeId) {
        this.removeId = removeId;
    }

    public void setAddId(CallBack1<Act> addId) {
        this.addId = addId;
    }

    public void setCallBackId(CallBack1<Act> callBackId) {
        this.callBackId = callBackId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.search_list_item;
    }

    private Integer selectCount() {
        int i = 0;
        for (UiCheckedItem<Act> event : list)
            if (event.checked) i++;
        return i;
    }

    private void resetSelected() {
        for (UiCheckedItem<Act> event : list)
            event.checked = false;
        notifyDataSetChanged();
    }

    class ViewHolder extends BaseAdapter.ViewHolder {

        @BindView(R.id.event_photo)
        ImageView eventPhoto;
        @BindView(R.id.event_name)
        TextView eventName;
        @BindView(R.id.event_date_time)
        TextView eventDateTime;
        @BindView(R.id.event_address)
        TextView eventAddress;
        @BindView(R.id.event_price)
        TextView eventPrice;
        @BindView(R.id.event_count)
        TextView eventCount;
        @BindView(R.id.space)
        View view;
        @BindView(R.id.search_remove_btn)
        View removeBtn;
        @BindView(R.id.search_add_btn)
        View addBtn;
        @BindView(R.id.swipper)
        SwipeLayout swipeLayout;
        @BindView(R.id.event_check)
        View check;

        ViewHolder(View itemView) {
            super(itemView);

            swipeLayout.setOnSwipeListener(new SwipeLayout.OnSwipeListener() {
                @Override
                public void onBeginSwipe(SwipeLayout swipeLayout, boolean moveToRight) {
                    canclick = false;
                    new android.os.Handler().postDelayed(() -> canclick = true,300);
                }

                @Override
                public void onSwipeClampReached(SwipeLayout swipeLayout, boolean moveToRight) {

                }

                @Override
                public void onLeftStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {


                }

                @Override
                public void onRightStickyEdge(SwipeLayout swipeLayout, boolean moveToRight) {

                }
            });
            swipeLayout.setOnClickListener(view1 -> {
                if(canclick) {
                    if (callBackId != null && !selectMode)
                        callBackId.call(list.get(getAdapterPosition()).item);
                    if (selectMode)
                        select();
                }
            });
            addBtn.setOnClickListener(view1 -> {
                if (addId != null)
                    addId.call(list.get(getAdapterPosition()).item);
                swipeLayout.reset();
            });
            removeBtn.setOnClickListener(view1 -> {
                if (removeId != null) {
                    UiCheckedItem<Act> event = list.get(getAdapterPosition());
                    removeId.call(event.item);
                    notifyItemRemoved(list.indexOf(event));
                    list.remove(list.indexOf(event));
                }
            });
            swipeLayout.setOnLongClickListener(view1 ->
            {
                if (!selectMode) {
                    setSelectMode(true);
                    select();
                }
                return true;
            });
        }

        private void select() {
            list.get(getAdapterPosition()).checked = !list.get(getAdapterPosition()).checked;
            setChecked(getAdapterPosition());
            if (setTitle != null) {
                int count = selectCount();
                setTitle.call(count == 0 ? "" : String.valueOf(count));
                selectMode = count > 0;
            }
        }

        private void setChecked(int pos) {
            check.setVisibility(list.get(pos).checked && selectMode ? View.VISIBLE : View.GONE);
        }

        @Override
        void bind(int pos) {
            setChecked(pos);
            swipeLayout.setSwipeEnabled(!selectMode);
            swipeLayout.reset();
            view.setVisibility(pos == 0 ? View.VISIBLE : View.GONE);
            Act event = list.get(pos).item;
            ImageLoader.loadImage(event.imageUrl, eventPhoto);
            eventName.setText(event.title);
            eventDateTime.setText(event.displayDate());
            eventAddress.setText(event.address);
            ResUtils.setPrice(event.price, eventPrice);
            eventCount.setText(ResUtils.getCountText(event.count_peoples, itemView.getContext()));
        }
    }

}
