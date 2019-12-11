package com.team.noty.event.ui.fragments.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.NotificationCount;
import com.team.noty.event.models.NotificationFilter;
import com.team.noty.event.mvp.presenters.NotificationPresenter;
import com.team.noty.event.mvp.views.NotificationView;
import com.team.noty.event.ui.adapters.NotificationAdapter;
import com.team.noty.event.ui.dialogs.PopupMain;
import com.team.noty.event.ui.fragments.SearchFragment;
import com.team.noty.event.models.Notification;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.utils.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NotificationTabFragment extends SearchFragment implements NotificationView {

    @InjectPresenter
    NotificationPresenter presenter;
    @BindView(R.id.bar)
    LinearLayout bar;
    @BindView(R.id.notification_spinner)
    TextView spinner;
    @BindView(R.id.notification_list)
    RecyclerView notificationList;
    @BindView(R.id.left)
    View left;
    @BindView(R.id.notification_serch_btn)
    ImageView notificationSerchBtn;
    @BindView(R.id.search_et)
    EditText searchEt;
    private PopupMain popupMain;
    private NotificationAdapter adapter;
    private int selected = 0;

    @Override
    protected int layout() {
        return R.layout.fragment_notification;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSearchElement(searchEt, left, notificationSerchBtn);
        popupMain = PopupMain.createPopup(Arrays.asList(getResources().getStringArray(R.array.notification_spinner)));
        popupMain.setListener(this::select);
        adapter = new NotificationAdapter();
        RecyclerUtils.setVerticalAdapter(notificationList, adapter);
        presenter.loadList(NotificationFilter.ALL);
        showLoad();
    }

    @Override
    protected void onSearchTextChange(CharSequence charSequence) {
        adapter.filter(charSequence.toString());
    }

    @OnClick(R.id.notification_spinner)
    void clickPopup() {
        spinner.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_white, 0);
        popupMain.setPos(bar.getBottom());
        popupMain.show(getFragmentManager(), selected);
    }

    private void select(int pos) {
        showLoad();
        selected = pos;
        spinner.setText(getResources().getStringArray(R.array.notification_spinner)[pos]);
        spinner.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_white, 0);
        presenter.loadList(getFilter(pos));
    }

    @Override
    public void onResume() {
        //EventBus.post(new NotificationCount(""));
        super.onResume();
    }

    private NotificationFilter getFilter(int pos) {
        switch (pos) {
            case 1:
                return NotificationFilter.MESSAGE;
            case 2:
                return NotificationFilter.SYSTEM;
            case 3:
                return NotificationFilter.NEW;
            default:
                return NotificationFilter.ALL;
        }
    }

    @Override
    public void showList(List<Notification> notifications) {

        List<Notification> list = new ArrayList<Notification>(new HashSet<Notification>(notifications));
        Collections.sort(list);
        adapter.setList(list);
        hideLoad();
    }

    @Override
    public void addNotification(Notification notification) {
        adapter.add(notification);
    }
}
