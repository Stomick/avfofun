package evfor.fun.skvader.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.app.Injector;
import evfor.fun.skvader.mvp.presenters.RestorePresenter;
import evfor.fun.skvader.mvp.views.RestoreView;
import evfor.fun.skvader.network.api.ContentApi;
import evfor.fun.skvader.network.api.LoginApi;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.utils.ViewUtils;
import evfor.fun.skvader.utils.ErrorHandler;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AccessRecoveryActivity extends BaseActivity implements RestoreView, LoadIndicator, ErrorHandler.ErrorDisplay {

    @InjectPresenter
    RestorePresenter presenter;
    @BindView(R.id.restore_mail_field)
    EditText restoreMailField;
    @BindView(R.id.progressBar)
    View progressBar;
    @BindView(R.id.content)
    View content;
    private String mail;
    private MenuItem sendBtn;
    @Inject
    LoginApi api;
    @Override
    protected int layout() {
        return R.layout.activity_acces_recovery;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        Injector.get().getApp().inject(this);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.restore_access);
    }

    @Override
    protected int menuLayout() {
        return R.menu.reg_next_button;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (true) {

            if (item.getItemId() == android.R.id.home) {
                onBackPressed();
            }
            if (!restoreMailField.getText().toString().isEmpty()) {
                sendBtn = item;
                item.setEnabled(false);
                showLoad();
                //presenter.restore(restoreMailField.getText().toString());
                mail = restoreMailField.getText().toString();

               Disposable disposable = api.restore(ContentApi.APIKEY, mail)
                       .subscribeOn(Schedulers.io())
                       .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                result -> {
                                    if (result.success())
                                        onComplete();
                                    else
                                        DialogProvider.infoOk(this, result.message, this::finish).show();
                                    Log.d("RESULT", result.message);
                                },
                                error ->
                                {
                                    DialogProvider.infoOk(this, error.getMessage(), this::finish).show();
                                    Log.d("ERROR", error.getMessage());
                                }
                        );


//                api.restores(ContentApi.APIKEY, mail).enqueue(new Callback<RsBase>() {
//                    @Override
//                    public void onResponse(Call<RsBase> call, Response<RsBase> response) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<RsBase> call, Throwable t) {
//
//                    }
//                });

            } else ViewUtils.checkEmptyTV(restoreMailField, getString(R.string.enter_mail));
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete() {
        setEnableBtn();
        DialogProvider.infoOk(this, getString(R.string.restore_complete, mail), this::finish).show();
    }

    @Override
    public void showInfo(int messageId, Object... params) {
        super.showInfo(messageId, params);
        setEnableBtn();
    }

    private void setEnableBtn() {
        if (sendBtn == null)
            return;
        sendBtn.setEnabled(true);
        hideLoad();
        restoreMailField.setText("");
    }

    @Override
    public void hideLoad() {
        content.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoad() {
        content.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void display(String title, String message) {
        int a = 5;
    }
}
