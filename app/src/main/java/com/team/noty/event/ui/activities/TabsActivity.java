package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.team.noty.event.R;
import com.team.noty.event.app.App;
import com.team.noty.event.app.AuthData;
import com.team.noty.event.dagger2.qualifiers.PaperBook;
import com.team.noty.event.mvp.presenters.TabsPresenter;
import com.team.noty.event.mvp.views.TabbetView;
import com.team.noty.event.repository.PaperRepos;
import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.ui.fragments.main.CabinetTabFragment;
import com.team.noty.event.ui.fragments.main.CreateTabFragment;
import com.team.noty.event.ui.fragments.main.FavoriteTabFragment;
import com.team.noty.event.ui.fragments.main.NotificationTabFragment;
import com.team.noty.event.ui.fragments.main.SearchTabFragment;
import com.team.noty.event.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import io.paperdb.Paper;

public class TabsActivity extends BaseActivity implements TabbetView, ProfileUpdater {
    private static final String NEXT_STEP = "next";
    private static final String SEARCH = "s";
    private static final String CREATE = "c";
    private boolean doubleBackToExitPressedOnce;

    public static void openSearch(Context context) {
        context.startActivity(new Intent(context, TabsActivity.class).putExtra(NEXT_STEP, SEARCH).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    public static void openCreate(Context context) {
        context.startActivity(new Intent(context, TabsActivity.class).putExtra(NEXT_STEP, CREATE).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @InjectPresenter
    TabsPresenter presenter;

    @BindView(R.id.navigation)
    public
    AHBottomNavigation navigation;
    public static AHBottomNavigation navigation_stat;
    private List<BaseFragment> fragmentList;
    private int i;
    public static TabsActivity tabsActivity;

    @Override
    protected int layout() {
        return R.layout.activity_tabs;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        App.getINSTANSE().sendFBToken();
        Paper.init(this);
        navigation.addItems(createBot());
        Log.e("my", AuthData.getToken());
        navigation.addItems(new ArrayList<>());
        navigation_stat = navigation;
        tabsActivity = this;
        createNotyCount();
        navigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        navigation.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        navigation.setInactiveColor(ContextCompat.getColor(this, R.color.tab_gray));
        navigation.setOnTabSelectedListener(
                (position, wasSelected) -> {
                    if (!wasSelected) {
                        onNavigationItemSelected(position);
                        return true;
                    }
                    return false;
                });
        presenter.loadProfile();
    }

    private List<AHBottomNavigationItem> createBot() {
        List<AHBottomNavigationItem> items = new ArrayList<>();
        items.add(new AHBottomNavigationItem(R.string.title_search, R.drawable.ic_search, R.color.tab_gray));
        items.add(new AHBottomNavigationItem(R.string.title_favorite, R.drawable.ic_star, R.color.tab_gray));
        items.add(new AHBottomNavigationItem(R.string.title_add, R.drawable.ic_add_circle, R.color.tab_gray));
        items.add(new AHBottomNavigationItem(R.string.title_notifications, R.drawable.ic_notifications_black_24dp, R.color.tab_gray));
        items.add(new AHBottomNavigationItem(R.string.title_profile, R.drawable.ic_person, R.color.tab_gray));
        return items;
    }

    @Override
    public void onComplete() {
        createFragment();
        onNavigationItemSelected(0);
        next(getIntent().getExtras());
    }

    @Override
    public void update() {
        presenter.updateProfile();
    }

    private void createFragment() {
        fragmentList = new LinkedList<>();
        fragmentList.add(new SearchTabFragment());
        fragmentList.add(FavoriteTabFragment.create(this::openCreateEventCommFragment));
        fragmentList.add(new CreateTabFragment());
        fragmentList.add(new NotificationTabFragment());
        fragmentList.add(new CabinetTabFragment());
    }

    private void openCreateEventCommFragment(boolean isEvent) {
        if (fragmentList.size() > 2)
            if (fragmentList.get(2) instanceof CreateTabFragment) {
                ((CreateTabFragment) fragmentList.get(2)).setEvent(isEvent);
                navigation.setCurrentItem(2);
            }
    }

    private void setFragment(BaseFragment fragment) {
        i = fragmentList.indexOf(fragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment).commit();
    }

    private void next(Bundle bundle) {
        if (bundle != null)
            if (bundle.containsKey(NEXT_STEP)) {
                String next = bundle.getString(NEXT_STEP);
                if (next != null) {
                    if (next.equals(SEARCH))
                        navigation.setCurrentItem(0);
                    else if (next.equals(CREATE))
                        navigation.setCurrentItem(2);
                    bundle.remove(NEXT_STEP);
                }
                return;
            }
        navigation.setCurrentItem(0);
    }

    public void onNavigationItemSelected(int i) {
        showLoad();
        if (fragmentList != null) {
            setFragment(fragmentList.get(i));
        }
    }

    int a = 0;

    @Override
    public void setNotificationCount(String count) {
        a++;
        if (!count.equals("") && !count.equals("0"))
            count = String.valueOf(a);
        else {
            a = 0;
            count = null;
        }
        navigation.setNotification(StringUtils.emptyIfNull(count), 3);
    }

    static List<String> ids = new ArrayList<>();

    public static void setNotyCount(String s) {
        ids = Paper.book().read("noty",new ArrayList<>());
        ids.add(s);
        Paper.book().write("noty",ids);
        if(tabsActivity!=null) {
            if (ids.size() > 0)
                tabsActivity.runOnUiThread(() ->
                        navigation_stat.setNotification(StringUtils.emptyIfNull(ids.size()), 3));
            else
                tabsActivity.runOnUiThread(() ->
                        navigation_stat.setNotification("", 3));
        }
    }
    public static void createNotyCount(){
        ids = Paper.book().read("noty",new ArrayList<>());
        if(tabsActivity!=null) {
            if (ids.size() > 0)
                tabsActivity.runOnUiThread(() ->
                        navigation_stat.setNotification(StringUtils.emptyIfNull(ids.size()), 3));
            else
                tabsActivity.runOnUiThread(() ->//its for clear
                        navigation_stat.setNotification("", 3));
        }
    }
    public static void removeNotyCount(String s) {
        ids = Paper.book().read("noty",new ArrayList<>());
        ids.removeAll(Collections.singleton(s));
        Paper.book().write("noty",ids);
        if(tabsActivity!=null) {
            if (ids.size() > 0)
                tabsActivity.runOnUiThread(() ->
                        navigation_stat.setNotification(StringUtils.emptyIfNull(ids.size()), 3));
            else
                tabsActivity.runOnUiThread(() ->//its for clear
                        navigation_stat.setNotification("", 3));
        }
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            finishAffinity();
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(getWindow().getDecorView(), "Нажмите дважды для выхода", Snackbar.LENGTH_LONG).show();

        new Handler().postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fragmentList.get(i).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateNotificationCount();
    }
}
