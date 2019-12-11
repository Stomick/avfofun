// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.login.widget.LoginButton;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EnterActivity_ViewBinding implements Unbinder {
  private EnterActivity target;

  private View view2131362038;

  private View view2131362043;

  private View view2131362039;

  @UiThread
  public EnterActivity_ViewBinding(EnterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EnterActivity_ViewBinding(final EnterActivity target, View source) {
    this.target = target;

    View view;
    target.enterMail = Utils.findRequiredViewAsType(source, R.id.enter_mail, "field 'enterMail'", EditText.class);
    target.enterPassword = Utils.findRequiredViewAsType(source, R.id.enter_password, "field 'enterPassword'", EditText.class);
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
    view = Utils.findRequiredView(source, R.id.enter_vk_button, "field 'vkLoginButton' and method 'vkClick'");
    target.vkLoginButton = Utils.castView(view, R.id.enter_vk_button, "field 'vkLoginButton'", Button.class);
    view2131362043 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.vkClick();
      }
    });
    target.progress = Utils.findRequiredView(source, R.id.progressBar, "field 'progress'");
    target.content = Utils.findRequiredView(source, R.id.content, "field 'content'");
    target.enterPasswordInput = Utils.findRequiredViewAsType(source, R.id.enter_password_input, "field 'enterPasswordInput'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.enter_forgot_password, "method 'onViewClicked'");
    view2131362039 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EnterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.enterMail = null;
    target.enterPassword = null;
    target.fbLoginButton = null;
    target.fbLoginSystemButton = null;
    target.vkLoginButton = null;
    target.progress = null;
    target.content = null;
    target.enterPasswordInput = null;

    view2131362038.setOnClickListener(null);
    view2131362038 = null;
    view2131362043.setOnClickListener(null);
    view2131362043 = null;
    view2131362039.setOnClickListener(null);
    view2131362039 = null;
  }
}
