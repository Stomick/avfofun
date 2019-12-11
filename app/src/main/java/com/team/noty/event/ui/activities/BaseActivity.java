package com.team.noty.event.ui.activities;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.team.noty.event.R;
import com.team.noty.event.app.Injector;
import com.team.noty.event.mvp.views.BaseView;
import com.team.noty.event.ui.dialogs.DialogProvider;
import com.team.noty.event.ui.dialogs.ProgressDialog;
import com.team.noty.event.utils.AccountPreferenceManager;
import com.team.noty.event.utils.DateFormatter;
import com.wang.avi.AVLoadingIndicatorView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.team.noty.event.ui.utils.ViewUtils.overrideFonts;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseView, LoadIndicator {
    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    //private com.wang.avi.AVLoadingIndicatorView progressDialog;
    private AlertDialog errorDialog;

    @LayoutRes
    protected abstract int layout();

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           progressDialog = new ProgressDialog(this);

        if (layout() != 0) {
            setContentView(layout());
            unbinder = ButterKnife.bind(this);
            initViews(savedInstanceState);

            if (getSupportActionBar() != null)
                actionBar(getSupportActionBar());
            overrideFonts(this, getWindow().getDecorView());
        }
        DateFormatter.today = getString(R.string.today);
        DateFormatter.yesterday = getString(R.string.yesterday);
        errorDialog = DialogProvider.infoOk(this);

        KeyboardVisibilityEvent.setEventListener(this, this::toggleSoftKayBoard);
    }

    protected void toggleSoftKayBoard(boolean keyboardVisible){

    }

    @Override
    public void hideLoad() {
        progressDialog.close();
    }

    @Override
    public void showLoad() {
        progressDialog.show();
    }

    protected void actionBar(@NonNull ActionBar bar) {

    }

    @Override
    public final boolean onCreateOptionsMenu(Menu menu) {
        if (menuLayout() != 0) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(menuLayout(), menu);
            onCreateMenu(menu);
            return true;
        } else return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCreateMenu(Menu menu) {

    }

    @MenuRes
    protected int menuLayout() {
        return 0;
    }

    protected final void setBackArrow(ActionBar bar) {
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Injector.get().getApp().accountPreferenceManager().checkValue(AccountPreferenceManager.ID))
            Injector.get().getApp().accountPreferenceManager().readInToData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Injector.get().getApp()
                .errorHandler().setErrorDisplay(this::showError);
    }

    protected void showError(String title, String message){
        errorDialog.setTitle(title);
        errorDialog.setMessage(message);
        errorDialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Injector.get().getApp().errorHandler().removeErrorDisplay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        progressDialog.close();
    }

    protected abstract void initViews(@Nullable Bundle savedInstanceState);

    @Override
    public void showInfo(@StringRes int messageId, Object... params) {
        if (messageId != 0)
                DialogProvider.infoOk(this, getString(messageId, params)).show();
        else DialogProvider.infoOk(this, String.valueOf(params[0])).show();
    }

    @Override
    public void onBackPressed() {
        progressDialog.close();
        super.onBackPressed();
    }
}
