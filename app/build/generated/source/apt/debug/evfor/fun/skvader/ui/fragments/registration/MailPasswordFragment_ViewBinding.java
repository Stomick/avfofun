// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.registration;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MailPasswordFragment_ViewBinding implements Unbinder {
  private MailPasswordFragment target;

  @UiThread
  public MailPasswordFragment_ViewBinding(MailPasswordFragment target, View source) {
    this.target = target;

    target.mpPassInput = Utils.findRequiredViewAsType(source, R.id.mp_pass_input, "field 'mpPassInput'", TextInputLayout.class);
    target.mpPassInputAgain = Utils.findRequiredViewAsType(source, R.id.mp_pass_input_again, "field 'mpPassInputAgain'", TextInputLayout.class);
    target.mailET = Utils.findRequiredViewAsType(source, R.id.mail_password_mail, "field 'mailET'", EditText.class);
    target.passET = Utils.findRequiredViewAsType(source, R.id.mail_password_password, "field 'passET'", EditText.class);
    target.passAgainET = Utils.findRequiredViewAsType(source, R.id.mail_password_password_again, "field 'passAgainET'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MailPasswordFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mpPassInput = null;
    target.mpPassInputAgain = null;
    target.mailET = null;
    target.passET = null;
    target.passAgainET = null;
  }
}
