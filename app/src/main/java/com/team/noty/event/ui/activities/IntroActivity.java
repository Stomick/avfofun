package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.mvp.presenters.IntroPresenter;
import com.team.noty.event.mvp.views.IntroView;
import com.team.noty.event.services.MessagesService;
import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.ui.fragments.intro.SignupFragment;
import com.team.noty.event.ui.fragments.intro.StartFragment;
import com.team.noty.event.ui.fragments.main.SearchTabFragment;

import butterknife.BindView;

import static com.team.noty.event.app.AuthData.fb_id;
import static com.team.noty.event.app.AuthData.token;

public class IntroActivity extends BaseActivity implements IntroView {

    public static void openNewTaskClearTop(Context context) {
        context.startActivity(new Intent(context, IntroActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
    }

    @InjectPresenter
    IntroPresenter presenter;
    private BaseFragment login, start;
    @BindView(R.id.intro_toolbar)
    Toolbar introToolbar;
    @IdRes
    final int container = R.id.intro_container;

    @Override
    protected int layout() {
        return R.layout.activity_intro;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        //startService(new Intent(this, MessagesService.class));
        introToolbar.setTitle("");
        if (!isOnline()) {
            showNetWorkFail();
        }
        setSupportActionBar(introToolbar);
        introToolbar.setNavigationOnClickListener(view -> onBackPressed());
        presenter.checkAuth();

    }

    public void showNetWorkFail() {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.turn_on,
                        (dialogInterface, i1) -> startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS)))
                .setNegativeButton(R.string.cancel,
                        (dialogInterface, i1) -> dialogInterface.dismiss())
                .setMessage(R.string.net_fail)
                .setOnDismissListener(dialogInterface -> finish())
                .setTitle("")
                .show();
    }

    public boolean isOnline() {
        ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectionManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    public void openMain() {
        startActivity(new Intent(this, TabsActivity.class));
        finish();
    }

    @Override
    public void needAuth() {
        start = StartFragment.create(this::onRegistration, this::onEnter, this::onWOReg);
        login = SignupFragment.create(
                presenter::continueWithVK,
                presenter::continueWithFB,
                this::onManualRegistration,
                view -> EnterActivity.start(this));
        setFragment(start);
    }

    @Override
    public void openReg() {
        startActivity(new Intent(this, RegistrationActivity.class));
    }

    private void setFragment(BaseFragment fragment) {
        showBackArrow(fragment.equals(login));
        getSupportFragmentManager().beginTransaction()
                .replace(container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(container) == null
                || getSupportFragmentManager().findFragmentById(container).equals(login))
            setFragment(start);
        else
            super.onBackPressed();
    }

    private void showBackArrow(boolean show) {
        getSupportActionBar().setHomeButtonEnabled(show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(show);
    }

    private void onEnter(View v) {
        startActivity(new Intent(this, EnterActivity.class));
    }

    private void onWOReg(View v) {
        EmptyActivity.startActivity(this, SearchTabFragment.TAG);
    }

    private void onRegistration(View v) {
        setFragment(login);
    }

    private void onManualRegistration(View v) {
        openReg();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (login != null)
            login.onActivityResult(requestCode, resultCode, data);
    }
}
