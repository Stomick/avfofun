// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.intro;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.login.widget.LoginButton;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SignupFragment_ViewBinding implements Unbinder {
  private SignupFragment target;

  private View view2131362154;

  private View view2131362038;

  private View view2131362151;

  private View view2131362147;

  @UiThread
  public SignupFragment_ViewBinding(final SignupFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.intro_vk_button, "field 'vkLoginButton' and method 'vkClick'");
    target.vkLoginButton = Utils.castView(view, R.id.intro_vk_button, "field 'vkLoginButton'", Button.class);
    view2131362154 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.vkClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.enter_fb_button, "field 'fbLoginButton' and method 'fbClick'");
    target.fbLoginButton = view;
    view2131362038 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.fbClick();
      }
    });
    target.fbLoginSystemButton = Utils.findRequiredViewAsType(source, R.id.login_button, "field 'fbLoginSystemButton'", LoginButton.class);
    view = Utils.findRequiredView(source, R.id.intro_registration_button, "method 'onViewClicked'");
    view2131362151 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.intro_haveacc_button, "method 'onViewClicked'");
    view2131362147 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SignupFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.vkLoginButton = null;
    target.fbLoginButton = null;
    target.fbLoginSystemButton = null;

    view2131362154.setOnClickListener(null);
    view2131362154 = null;
    view2131362038.setOnClickListener(null);
    view2131362038 = null;
    view2131362151.setOnClickListener(null);
    view2131362151 = null;
    view2131362147.setOnClickListener(null);
    view2131362147 = null;
  }
}
