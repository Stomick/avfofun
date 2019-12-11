package evfor.fun.skvader.ui.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.facebook.Profile;
import com.facebook.login.widget.LoginButton;
import evfor.fun.skvader.R;
import evfor.fun.skvader.mvp.presenters.LoginPresenter;
import evfor.fun.skvader.mvp.views.LoginView;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.utils.StringUtils;
import evfor.fun.skvader.utils.social.FaceBookLoginManager;
import evfor.fun.skvader.utils.social.SocialLogin;
import evfor.fun.skvader.utils.social.VKLoginManager;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKSdk;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Maybe;
import io.reactivex.disposables.Disposable;

public class EnterActivity extends BaseActivity implements LoginView, LoadIndicator {

    @InjectPresenter
    LoginPresenter presenter;

    @BindView(R.id.enter_mail)
    EditText enterMail;
    @BindView(R.id.enter_password)
    EditText enterPassword;
    @BindView(R.id.enter_fb_button)
    View fbLoginButton;
    @BindView(R.id.login_button)
    LoginButton fbLoginSystemButton;
    SocialLogin socialLogin;
    Disposable socialTask;
    @BindView(R.id.enter_vk_button)
    Button vkLoginButton;
    @BindView(R.id.progressBar)
    View progress;
    @BindView(R.id.content)
    View content;
    @BindView(R.id.enter_password_input)
    TextInputLayout enterPasswordInput;

    public static void start(Context context) {
        context.startActivity(new Intent(context, EnterActivity.class));
    }

    @Override
    protected int layout() {
        return R.layout.activity_enter;
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        hideLoad();
    }

    @OnClick(R.id.enter_vk_button)
    void vkClick() {
        showLoad();
        socialLogin = new VKLoginManager(this);
        if (VKSdk.isLoggedIn()) {
            String s = VKAccessToken.currentToken().accessToken;
            presenter.login(VKAccessToken.currentToken().userId);
        } else {
            subscribeSocialLogin(socialLogin.requestId());
        }
    }

    @OnClick(R.id.enter_fb_button)
    void fbClick() {
        showLoad();
        socialLogin = new FaceBookLoginManager(fbLoginSystemButton);
        Profile current = Profile.getCurrentProfile();
        if (current != null)
            presenter.login(current.getId());
        else {
            subscribeSocialLogin(socialLogin.requestId());
            fbLoginSystemButton.callOnClick();
        }
    }

    private void subscribeSocialLogin(Maybe<String> maybe) {
        socialTask = maybe.subscribe(presenter::login, this::showError);
        setEnabledSocialButtons(false);
    }

    private void setEnabledSocialButtons(boolean enable) {
        vkLoginButton.setEnabled(enable);
        fbLoginButton.setEnabled(enable);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle(R.string.entrance);
    }

    @Override
    public void onComplete() {
        hideLoad();
        startActivity(new Intent(this, TabsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
        finish();
    }

    @Override
    protected int menuLayout() {
        return R.menu.enter_menu;
    }

    private void login() {
        if (!enterPassword.isFocused())
            enterPassword.getBackground().setColorFilter(getResources().getColor(R.color.under_line),
                    PorterDuff.Mode.SRC_ATOP);
        enterPasswordInput.setError(null);
        if (!enterMail.getText().toString().isEmpty()&&enterPassword.getText().length()>5
                && !enterPassword.getText().toString().isEmpty()
                && StringUtils.validEmail(enterMail.getText().toString())) {
            presenter.login(enterMail.getText().toString(), enterPassword.getText().toString());
            showLoad();
        }
        if (!StringUtils.validEmail(enterMail.getText().toString()))
            enterMail.setError(getString(R.string.incorrect));
        if (enterMail.getText().length() == 0)
            enterMail.setError(getString(R.string.must_filled));
        if (enterPassword.getText().length()<6) {
            enterPassword.getBackground().setColorFilter(getResources().getColor(R.color.red_button_text),
                    PorterDuff.Mode.SRC_ATOP);
            enterPassword.setError("Введите не менее 6 символов");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.enter_button) {
            login();
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void errorLogin() {
        hideLoad();
        DialogProvider.infoOk(this, R.string.error_login).show();
    }

    @Override
    public void gotoRegister() {
        RegistrationActivity.open(this);
    }

    @OnClick(R.id.enter_forgot_password)
    public void onViewClicked() {
        startActivity(new Intent(this, AccessRecoveryActivity.class));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        socialLogin.onActivityResult(requestCode, resultCode, data);
        setEnabledSocialButtons(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (socialLogin != null)
            socialLogin.onDestroy();
        if (socialTask != null && !socialTask.isDisposed())
            socialTask.dispose();
    }

    private void showError(Throwable ignore) {
        hideLoad();
    }

    @Override
    public void hideLoad() {
        progress.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoad() {
        content.setVisibility(View.GONE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInfo(int messageId, Object... params) {
        hideLoad();
        if (params.length > 0 && params[0] instanceof String && ((String)params[0]).contains("Incorrect"))
            super.showInfo(R.string.pass_mail_error);
        else super.showInfo(messageId, params);
    }

}
