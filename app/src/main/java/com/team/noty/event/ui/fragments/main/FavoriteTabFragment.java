package com.team.noty.event.ui.fragments.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.mvp.presenters.FavoritePresenter;
import com.team.noty.event.mvp.views.FavoriteView;
import com.team.noty.event.ui.adapters.EventAdapterProfile;
import com.team.noty.event.ui.dialogs.PopupMain;
import com.team.noty.event.ui.fragments.SearchFragment;
import com.team.noty.event.ui.models.UiProfileEvent;
import com.team.noty.event.ui.utils.RecyclerUtils;
import com.team.noty.event.ui.utils.TabLayoutUtils;
import com.team.noty.event.utils.callbacks.CallBack1;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class FavoriteTabFragment extends SearchFragment implements FavoriteView {

    public static FavoriteTabFragment create(CallBack1<Boolean> createEventComm) {
        FavoriteTabFragment fragment = new FavoriteTabFragment();
        fragment.create = createEventComm;
        return fragment;
    }

    @InjectPresenter
    FavoritePresenter presenter;

    @BindView(R.id.favorite_tabs)
    TabLayout tabLayout;
    @BindView(R.id.sad_titel_textView)
    TextView sad_title;
    @BindView(R.id.favorits_list)
    RecyclerView favoritsList;
    @BindView(R.id.favorits_empty)
    LinearLayout favoritsEmpty;
    @BindView(R.id.space_left)
    View left;
    @BindView(R.id.favorite_serch_btn)
    View right;
    @BindView(R.id.favorites_search)
    EditText favoritesSearch;
    PopupMain popupMain;
    CallBack1<Boolean> create;

    private EventAdapterProfile adapter;
    private int filter = 0;
    private boolean isEvents = true;

    @Override
    protected int layout() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setSearchElement(favoritesSearch, left, right);
        loadIndicator.showLoad();
        adapter = new EventAdapterProfile();
        RecyclerUtils.setVerticalAdapter(favoritsList, adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTab(tab.getPosition());
                loadIndicator.showLoad();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                adapter.clear();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                selectTab(tab.getPosition());
            }
        });
        presenter.getEvents(filter);
        popupMain = PopupMain.createPopup(Arrays.asList(getResources().getStringArray(R.array.favorite_spinner)));
        popupMain.setListener(this::selectFilter);

        TabLayoutUtils.setTitles(tabLayout, getString(R.string.my_event_n, 0), getString(R.string.my_communities_n, 0));
    }

    @Override
    protected void onSearchTextChange(CharSequence charSequence) {
        adapter.filter(charSequence.toString());
    }

    private void selectTab(int pos) {
        favoritsList.setVisibility(View.GONE);
        favoritsEmpty.setVisibility(View.GONE);
        switch (pos) {
            case 0:
                presenter.getEvents(filter);
                sad_title.setText(R.string.you_dont_have_event);
                isEvents = true;
                loadIndicator.hideLoad();
                break;
            case 1:
                presenter.getCommunities(filter);
                sad_title.setText(R.string.you_dont_have_communities);
                isEvents = false;
                loadIndicator.hideLoad();
                break;
        }
    }

    @OnClick(R.id.favorite_spinner)
    void clickPopup(View v) {
        setUpDrop();
        popupMain.show(getFragmentManager(), filter);
        popupMain.setPos(v.getBottom());
    }

    private void selectFilter(int i) {
        filter = i;
        selectTab(tabLayout.getSelectedTabPosition());
        setDownDrop();
    }

    private void setUpDrop() {
        if (getView() != null)
            ((TextView) getView().findViewById(R.id.favorite_spinner))
                    .setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_up_white, 0);
    }

    private void setDownDrop() {
        if (getView() != null)
            ((TextView) getView().findViewById(R.id.favorite_spinner))
                    .setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_drop_down_white, 0);
    }

    @Override
    public void setCounts(int events, int comm) {
        TabLayoutUtils.setTitles(tabLayout, getString(R.string.my_event_n, events), getString(R.string.my_communities_n, comm));
    }

    @Override
    public void showList(List<Act> events) {
        if (events.isEmpty()) {
            favoritsList.setVisibility(View.GONE);
            favoritsEmpty.setVisibility(View.VISIBLE);
        } else {
            adapter.setList(events);
            favoritsList.setVisibility(View.VISIBLE);
            favoritsEmpty.setVisibility(View.GONE);
        }
        hideLoad();
    }

    @OnClick(R.id.sad_title_button)
    public void onViewClicked() {
        if (create != null)
            create.call(isEvents);
    }
}
