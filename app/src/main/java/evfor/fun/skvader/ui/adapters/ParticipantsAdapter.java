package evfor.fun.skvader.ui.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.SortedList;
import evfor.fun.skvader.ui.activities.EmptyActivity;
import evfor.fun.skvader.ui.holders.ParticipantHolder;
import evfor.fun.skvader.ui.models.UiUser;
import evfor.fun.skvader.utils.DateFormatter;
import evfor.fun.skvader.utils.callbacks.CallBack;
import evfor.fun.skvader.utils.callbacks.CallBack1;

import butterknife.BindView;
import butterknife.OnClick;
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ParticipantsAdapter extends FilterableListAdapter<UiUser, ParticipantsAdapter.ViewHolder> {

    private boolean checked = false;
    private boolean swiped = false;
    private boolean acepted = true;

    private CallBack setChecked;
    private CallBack1<String> setTitle;
    private CallBack1<String> acceptUser;
    private CallBack1<String> removeUser;

    public ParticipantsAdapter() {
        super(new SortedList<>());
    }

    public void disableAccept() {
        acepted = false;
    }

    public void enableAccept() {
        acepted = true;
    }

    public void setSwiped(boolean swiped) {
        this.swiped = swiped;
    }

    public void setActionUser(CallBack1<String> acceptUser, CallBack1<String> removeUser) {
        this.acceptUser = acceptUser;
        this.removeUser = removeUser;
    }

    public void setChecked(CallBack setChecked) {
        this.setChecked = setChecked;
    }

    public void setTitleCall(CallBack1<String> setTitleCall) {
        this.setTitle = setTitleCall;
    }

    private String countChecked() {
        int i = 0;
        for (UiUser u : list)
            if (u.isCheck)
                i++;
        return String.valueOf(i);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.part_list_item;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(parent, viewType));
    }

    class ViewHolder extends BaseAdapter.ViewHolder {
        private ParticipantHolder participantHolder;
        @BindView(R.id.part_swipper)
        SwipeLayout swipeLayout;
        @BindView(R.id.part_left_btn)
        View acceptBtn;
        @BindView(R.id.part_item)
        View partView;

        ViewHolder(View itemView) {
            super(itemView);
            swipeLayout.setSwipeEnabled(swiped);
            participantHolder = new ParticipantHolder(partView);
            partView.setOnClickListener(v -> onClick());
            partView.setOnLongClickListener(v -> onLongClick());
        }

        private boolean onLongClick() {
            checked = !checked;
            if (setChecked != null) {
                setChecked.call();
                return true;
            }
            return false;
        }

        private void onClick() {
            if (checked) {
                list.get(getAdapterPosition()).isCheck = !list.get(getAdapterPosition()).isCheck;
                notifyItemChanged(getAdapterPosition());
                setTitle.call(countChecked());
            } else
                EmptyActivity.startActivityCabinet(this.itemView.getContext(),
                        (String.valueOf(list.get(getAdapterPosition()).user.id())));
        }

        @OnClick(R.id.part_left_btn)
        void onClickLeft() {
            swipeLayout.reset();
            if (acceptUser != null)
                acceptUser.call(String.valueOf(list.get(getAdapterPosition()).user.id()));
        }

        @OnClick(R.id.part_right_btn)
        void onClickRight() {
            swipeLayout.reset();
            if (removeUser != null)
                removeUser.call(String.valueOf(list.get(getAdapterPosition()).user.id()));
        }

        @Override
        void bind(int pos) {

            UiUser uiUser = list.get(pos);
            if(uiUser.canWrite)
                participantHolder.showButton();
            else participantHolder.hideButton();
            participantHolder.setCity(uiUser.user.city);
            participantHolder.setName_age(uiUser.user.firstname, String.valueOf(DateFormatter.getAge(uiUser.user.date)));
            participantHolder.setPhoto(uiUser.user.imageUrl);
            participantHolder.setRate(uiUser.user.level);
            participantHolder.setUserId(String.valueOf(list.get(pos).user.id()));
            acceptBtn.setVisibility(acepted ? View.VISIBLE : View.GONE);
            setCheckedState(uiUser);
        }

        private void setCheckedState(UiUser uiUser) {
            if (!checked)
                uiUser.isCheck = false;
            participantHolder.check(uiUser.isCheck);

        }
    }
}
