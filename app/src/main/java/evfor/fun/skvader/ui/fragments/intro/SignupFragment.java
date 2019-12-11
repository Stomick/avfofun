package evfor.fun.skvader.ui.fragments.intro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.login.widget.LoginButton;
import evfor.fun.skvader.R;
import evfor.fun.skvader.ui.dialogs.DialogProvider;
import evfor.fun.skvader.ui.fragments.BaseFragment;
import evfor.fun.skvader.utils.callbacks.CallBack;
import evfor.fun.skvader.utils.callbacks.CallBack1;
import evfor.fun.skvader.utils.social.FaceBookLoginManager;
import evfor.fun.skvader.utils.social.SocialLogin;
import evfor.fun.skvader.utils.social.VKLoginManager;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;

public class SignupFragment extends BaseFragment {

    private View.OnClickListener onRegistration, onHaveAcc;
    private CallBack1<String> callBackFBID;
    private CallBack callBackVK;

    @BindView(R.id.intro_vk_button)
    Button vkLoginButton;
    @BindView(R.id.enter_fb_button)
    View fbLoginButton;
    @BindView(R.id.login_button)
    LoginButton fbLoginSystemButton;
    SocialLogin socialLogin;
    Disposable socialTask;

    public static SignupFragment create(
            CallBack onResumeWithVK,
            CallBack1<String> callBackFBID,
            View.OnClickListener onRegistration,
            View.OnClickListener onHaveAcc) {
        SignupFragment fragment = new SignupFragment();
        fragment.callBackVK = onResumeWithVK;
        fragment.callBackFBID = callBackFBID;
        fragment.onRegistration = onRegistration;
        fragment.onHaveAcc = onHaveAcc;
        return fragment;
    }

    @Override
    protected int layout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
    }

    @OnClick(R.id.intro_vk_button)
    void vkClick() {
        socialLogin = new VKLoginManager(getActivity());
        socialTask = socialLogin.requestId().subscribe(s -> callBackVK.call(),  throwable -> Log.e("my",throwable.getMessage()));
        setEnabledSocialButtons(false);
    }

    @OnClick(R.id.enter_fb_button)
    void fbClick() {
        socialLogin = new FaceBookLoginManager(fbLoginSystemButton);
        socialTask = socialLogin.requestId().subscribe(callBackFBID::call, throwable -> Log.e("my",throwable.getMessage()));
        fbLoginSystemButton.callOnClick();
        setEnabledSocialButtons(false);
    }

    private void setEnabledSocialButtons(boolean enable) {
        vkLoginButton.setEnabled(enable);
        fbLoginButton.setEnabled(enable);
    }

    @OnClick({R.id.intro_registration_button, R.id.intro_haveacc_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.intro_registration_button:
                if (onRegistration != null)
                    onRegistration.onClick(view);
                break;
            case R.id.intro_haveacc_button:
                if (onHaveAcc != null)
                    onHaveAcc.onClick(view);
                break;
        }
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

    private void showError(Throwable error) {
        DialogProvider.infoOk(getContext(), error.getMessage()).show();
    }
}
