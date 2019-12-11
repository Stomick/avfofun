package com.team.noty.event.ui.fragments.main;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.Act;
import com.team.noty.event.models.Category;
import com.team.noty.event.mvp.presenters.SearchTabPresenter;
import com.team.noty.event.mvp.views.tabs.SearchTabView;
import com.team.noty.event.ui.activities.EventActivity;
import com.team.noty.event.ui.activities.MapActivity;
import com.team.noty.event.ui.activities.search.SearchActivity;
import com.team.noty.event.ui.adapters.InterestingAdapter;
import com.team.noty.event.ui.adapters.PopularEventAdapter;
import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.ui.utils.RecyclerUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearchTabFragment extends BaseFragment implements SearchTabView {

    public static final String TAG = SearchTabFragment.class.getSimpleName();

    @InjectPresenter
    SearchTabPresenter presenter;

    @BindView(R.id.search_tab_events)
    RecyclerView eventsView;
    @BindView(R.id.search_tab_categories)
    RecyclerView categoriesView;
    private PopularEventAdapter popAdapter;
    private InterestingAdapter adapter;

    @Override
    protected int layout() {
        return R.layout.fragment_search;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        popAdapter = new PopularEventAdapter();
        RecyclerUtils.setHorisontalAdapter(eventsView, popAdapter);
        RecyclerUtils.setVerticalTwoCol(categoriesView);
        popAdapter.setCallBack(this::open);
        adapter = new InterestingAdapter(false);
        adapter.setCallBackCategoryId(this::openEvents);
        categoriesView.setAdapter(adapter);
        presenter.getCategories();
        presenter.getPopular();
        showLoad();
    }

    private void open(Act act) {
        EventActivity.open(getContext(), act);
    }

    @Override
    public void openEvents(String categoryId) {
        if (getContext() != null)
            SearchActivity.open(getContext(), categoryId);
    }

    @OnClick(R.id.search_navigation)
    public void onViewClicked() {
        checkGPS();

    }
    public void checkGPS()
    {
        final LocationManager manager = (LocationManager)
                getActivity().getSystemService( Context.LOCATION_SERVICE );
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Ваш GPS отключен, хотите включить для продолжения работы?")
                    .setCancelable(false)
                    .setPositiveButton("Да", (dialog, id) -> startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),1))
                    .setNegativeButton("Нет", (dialog, id) -> dialog.cancel());
            final AlertDialog alert = builder.create();
            alert.show();
        }
        else
            MapActivity.open(getContext());
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final LocationManager manager = (LocationManager)
                getActivity().getSystemService( Context.LOCATION_SERVICE );
        if (resultCode == 1) {
            checkGPS();

        }
    }
    @Override
    public void showCategory(List<Category> interestItems) {
        if (adapter != null)
            adapter.setList(interestItems);
    }

    @Override
    public void showEvents(List<Act> events) {
        popAdapter.setList(events);
        hideLoad();
    }
    @OnClick(R.id.search)
    void onFocusChange() {
        startActivity(new Intent(getContext(), SearchActivity.class));
    }
}
