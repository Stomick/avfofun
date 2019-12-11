package com.team.noty.event.ui.fragments.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.team.noty.event.R;
import com.team.noty.event.ui.activities.TabsActivity;
import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.ui.fragments.main.create.BaseCreateFragment;
import com.team.noty.event.ui.fragments.main.create.CreateCommunityFragment;
import com.team.noty.event.ui.fragments.main.create.CreateEventFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class CreateTabFragment extends BaseFragment {


    @BindView(R.id.favorite_tabs)
    TabLayout favoriteTabs;

    private CreateEventFragment eventFragment;
    private CreateCommunityFragment communityFragment;
    private boolean isEvent = true;

    public void setEvent(boolean event) {
        isEvent = event;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_create;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        eventFragment = new CreateEventFragment();
        communityFragment = new CreateCommunityFragment();
        favoriteTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        setPage(eventFragment);
                        isEvent = true;
                        break;
                    case 1:
                        setPage(communityFragment);
                        isEvent = false;
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setPage(isEvent ? eventFragment : communityFragment);
        loadIndicator.hideLoad();
    }

    @Override
    public void onResume() {
        super.onResume();
        TabLayout.Tab tab = favoriteTabs.getTabAt(isEvent ? 0 : 1);
        if (tab != null)
            tab.select();
        setPage(isEvent ? eventFragment : communityFragment);
    }

    private BaseCreateFragment getCurrent() {
        if (favoriteTabs.getSelectedTabPosition() == 0)
            return eventFragment;
        else
            return communityFragment;
    }

    @Override
    public boolean onBackPressed() {
        return getCurrent().onBackPressed();
    }

    private void setPage(BaseFragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getCurrent().onActivityResult(requestCode, resultCode, data);
    }

    @OnClick(R.id.create_cancel)
    public void onViewClicked() {
       back();
    }
    public void back()
    {
        TabsActivity tabsActivity = (TabsActivity) getActivity();
        tabsActivity.onNavigationItemSelected(0);
        tabsActivity.navigation.setCurrentItem(0);
    }
}
