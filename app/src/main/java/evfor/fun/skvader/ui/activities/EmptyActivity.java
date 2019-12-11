package evfor.fun.skvader.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.fragments.BarEditor;
import evfor.fun.skvader.ui.fragments.BaseFragment;
import evfor.fun.skvader.ui.fragments.ActListFragment;
import evfor.fun.skvader.ui.fragments.main.CabinetTabFragment;
import evfor.fun.skvader.ui.fragments.main.create.CreateCommunityFragment;
import evfor.fun.skvader.ui.fragments.main.create.CreateEventFragment;
import evfor.fun.skvader.ui.utils.factory.FragmentFactory;

public class EmptyActivity extends BaseActivity {
    public static final String FRAGMENT = "F";

    public static void startActivityEventsProfile(Context context, String id) {
        context.startActivity(new Intent(context, EmptyActivity.class)
                .putExtra(EmptyActivity.FRAGMENT, ActListFragment.TAG)
                .putExtra(ActListFragment.TAG, true)
                .putExtra(ActListFragment.ID, id));
    }

    public static void startActivityCommProfile(Context context, String id) {
        context.startActivity(new Intent(context, EmptyActivity.class)
                .putExtra(EmptyActivity.FRAGMENT, ActListFragment.TAG)
                .putExtra(ActListFragment.TAG, false)
                .putExtra(ActListFragment.ID, id)
        );
    }

    public static void startActivity(Context context, String tag) {
        context.startActivity(new Intent(context, EmptyActivity.class).putExtra(EmptyActivity.FRAGMENT, tag));
    }

    public static void startActivityCabinet(Context context, String id) {
        context.startActivity(cabinetIntent(context, id));
    }

    public static Intent cabinetIntent(Context context, String id){
        return new Intent(context, EmptyActivity.class)
                .putExtra(EmptyActivity.FRAGMENT, CabinetTabFragment.TAG)
                .putExtra(CabinetTabFragment.TAG, id);
    }

    public static void startEditComm(Context context, String id) {
        context.startActivity(new Intent(context, EmptyActivity.class)
                .putExtra(EmptyActivity.FRAGMENT, CreateCommunityFragment.TAG)
                .putExtra(CreateCommunityFragment.TAG, id)
        );
    }

    public static void startEditEvent(Context context, String id) {
        context.startActivity(new Intent(context, EmptyActivity.class)
                .putExtra(EmptyActivity.FRAGMENT, CreateEventFragment.TAG)
                .putExtra(CreateEventFragment.TAG, id)
        );
    }

    private BaseFragment fragment;

    @Override
    protected int layout() {
        return R.layout.activity_empty;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        String tag = getIntent().getStringExtra(FRAGMENT);
        getFragment(tag);
    }

    private void getFragment(String tag) {
        if (tag != null) {
            fragment = FragmentFactory.create(tag, getIntent().getExtras());
            setFragment(fragment);
            setBar(fragment);
        }
    }

    private void setFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    private void setBar(BaseFragment fragment) {
        if (fragment instanceof BarEditor && getSupportActionBar() != null)
            ((BarEditor) fragment).provideActionBar(getSupportActionBar());
    }

    @Override
    public void onBackPressed() {
        if (fragment != null && !fragment.onBackPressed())
            super.onBackPressed();
    }
}
