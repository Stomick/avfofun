package evfor.fun.skvader.ui.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.app.AuthData;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.mvp.presenters.ActListPresenter;
import evfor.fun.skvader.mvp.views.EventCommListView;
import evfor.fun.skvader.ui.adapters.EventAdapterProfile;
import evfor.fun.skvader.ui.utils.RecyclerUtils;
import evfor.fun.skvader.ui.utils.TabLayoutUtils;
import evfor.fun.skvader.ui.utils.listeners.SimpleTextListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ActListFragment extends BaseFragment implements EventCommListView {

    public static final String TAG = ActListFragment.class.getSimpleName();
    public static final String ID = "id";

    @InjectPresenter
    ActListPresenter presenter;

    @BindView(R.id.eventcomTabs)
    TabLayout eventcomTabs;
    @BindView(R.id.eventcomList)
    RecyclerView eventcomList;
    @BindView(R.id.space_left)
    View spaceLeft;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.search)
    EditText search;
    private EventAdapterProfile adapter;
    private boolean isEvent = true;
    private String user_id;
    private boolean isSearch = false;

    public static ActListFragment create(boolean event, String user_id) {
        ActListFragment fragment = new ActListFragment();
        fragment.isEvent = event;
        if (user_id == null)
            fragment.user_id = AuthData.getID();
        else
            fragment.user_id = user_id;
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.act_list_fragment;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        presenter.loadUser(user_id, isEvent);
        loadIndicator.showLoad();
    }

    @Override
    public void onComplete() {
        adapter = new EventAdapterProfile();
        RecyclerUtils.setVerticalAdapter(eventcomList, adapter);
        TabLayoutUtils.setTitles(eventcomTabs, getString(R.string.part_template, 0), getString(R.string.creator_template, 0));
        eventcomTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectTab(tab);
                loadIndicator.showLoad();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                selectTab(tab);
            }
        });
        spaceLeft.setOnClickListener(v -> onBackPress());
        title.setText(isEvent ? R.string.events : R.string.communities);
        SimpleTextListener.on(search, charSequence -> adapter.filter(charSequence.toString()));
        TabLayoutUtils.selectTab(eventcomTabs, 0);
    }

    private void onBackPress() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    private void selectTab(TabLayout.Tab tab) {
        adapter.clear();
        boolean isCreator = false;
        switch (tab.getPosition()) {
            case 0:
                isCreator = false;
                break;
            case 1:
                isCreator = true;
                break;
        }
        presenter.loadActList(isCreator);

    }

    @Override
    public void loadList(List<Act> list) {
        adapter.setList(list);
        loadIndicator.hideLoad();
    }

    @Override
    public void setCounts(int partC, int creatorC) {
        TabLayoutUtils.setTitles(eventcomTabs, getString(R.string.part_template, partC), getString(R.string.creator_template, creatorC));
    }

    private void searchSelect() {
        spaceLeft.setVisibility(View.GONE);
        search.setVisibility(View.VISIBLE);
        search.requestFocus();
        isSearch = true;
    }

    private void searchUnSelect() {
        spaceLeft.setVisibility(View.VISIBLE);
        search.setVisibility(View.GONE);
        isSearch = false;
    }

    @OnClick(R.id.serch_btn)
    public void onSearchClicked() {
        if (isSearch)
            searchUnSelect();
        else searchSelect();
    }

    @Override
    public boolean onBackPressed() {
        if (isSearch) {
            searchUnSelect();
            return true;
        } else return false;
    }
}
