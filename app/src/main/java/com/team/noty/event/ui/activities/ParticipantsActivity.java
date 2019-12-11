package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.ActId;
import com.team.noty.event.models.Participant;
import com.team.noty.event.mvp.presenters.PartsPresenter;
import com.team.noty.event.mvp.views.PartsView;
import com.team.noty.event.ui.adapters.ParticipantsAdapter;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.models.UiUser;
import com.team.noty.event.ui.utils.RecyclerUtils;

import butterknife.BindView;

public class ParticipantsActivity extends BaseActivity implements PartsView {

    public static final String CREATOR = "creator";

    @InjectPresenter
    PartsPresenter presenter;

    @BindView(R.id.parts_list)
    RecyclerView partsList;
    SearchView searchView;
    MenuItem delete, search;
    @BindView(R.id.parts_tab)
    TabLayout partsTab;
    @BindView(R.id.participants_bot_title)
    TextView botTitle;
    @BindView(R.id.parts_empty_text)
    TextView emptyText;
    private ParticipantsAdapter adapter;
    private int empty_string_res = R.string.empty_users;

    public static void open(Context context, ActId actId, boolean creator) {
        context.startActivity(intent(context, actId, creator));
    }

    public static Intent intentAdmin(Context context, ActId actId) {
        return new Intent(context, ParticipantsActivity.class)
                .putExtra(ActId.TAG, actId)
                .putExtra(CREATOR, true);
    }

    public static Intent intent(Context context, ActId actId, boolean creator) {
        return new Intent(context, ParticipantsActivity.class)
                .putExtra(ActId.TAG, actId)
                .putExtra(CREATOR, creator);
    }

    @Override
    protected int layout() {
        return R.layout.activity_participants;
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.all_party);
        bar.setElevation(0f);
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
//        showLoad();
        adapter = new ParticipantsAdapter();
        adapter.setChecked(this::setCheckBar);
        adapter.setTitleCall(this::setTitle);
        showRequestCount(0);
        adapter.setActionUser(presenter::acceptUser, presenter::removeUser);
        partsTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        RecyclerUtils.setVerticalAdapter(partsList, adapter);
        getIds(getIntent().getExtras());
    }

    private void getIds(Bundle bundle) {
        if (bundle != null && bundle.containsKey(CREATOR) && bundle.containsKey(ActId.TAG)) {
            boolean creator = bundle.getBoolean(CREATOR, false);
            if (creator) {
                imCreator();
                adapter.setSwiped(true);
            }
            presenter.loadAct((ActId) bundle.getSerializable(ActId.TAG), creator);
        }
    }

    private void selectTab(int pos) {
        emptyText.setText(empty_string_res);
        emptyText.setVisibility(View.VISIBLE);
        adapter.clear();
        switch (pos) {
            case 0:
                presenter.getUsers(PartsPresenter.TYPE.JOINING);
                empty_string_res = R.string.empty_users;
                adapter.disableAccept();
                break;
            case 1:
                presenter.getUsers(PartsPresenter.TYPE.REQUEST);
                empty_string_res = R.string.empty_request_users;
                adapter.enableAccept();
                break;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
    }

    private void setCheckBar() {
        search.setVisible(false);
        delete.setVisible(true);
    }

    private void disableCheckBar() {
        search.setVisible(true);
        delete.setVisible(false);
    }

    @Override
    public void onBackPressed() {
        if (adapter.isChecked()) {
            adapter.setChecked(false);
            disableCheckBar();
            setTitle(R.string.all_party);
        } else
            super.onBackPressed();
    }

    @Override
    protected int menuLayout() {
        return R.menu.search_bar_participants;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        delete = menu.findItem(R.id.action_delete).setVisible(false);
        search = menu.findItem(R.id.action_search);
        search.setVisible(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
        super.onCreateMenu(menu);
    }

    @Override
    public void addUser(UiUser user) {
        emptyText.setVisibility(View.GONE);
        showRequestCount(presenter.request.size());
        adapter.add(user);
    }

    @Override
    public void onComplete() {
        hideLoad();
    }

    @Override
    public void showRequestCount(int count) {
        TabLayout.Tab tab = partsTab.getTabAt(1);
        if (tab != null)
            tab.setText(getString(R.string.new_applications, count));
    }

    @Override
    public void showDeleteDialog(String name, String ecName, String id) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DialogProvider.deleteUserDialog(ParticipantsActivity.this, name, ecName,
                        () -> ComplaintActivity.openDeleteUser(
                                ParticipantsActivity.this,
                                id,
                                presenter.getActId().isEvent,
                                presenter.getActId().id)
                ).show();
            }
        });

    }

    @Override
    public void removed(UiUser uiUser) {
        runOnUiThread(() -> {
            adapter.remove(uiUser);
        });

    }

    public void imCreator() {
        partsTab.setVisibility(View.VISIBLE);
        botTitle.setVisibility(View.VISIBLE);
    }
}
