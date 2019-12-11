// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChangePasswordActivity_ViewBinding implements Unbinder {
  private ChangePasswordActivity target;

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePasswordActivity_ViewBinding(ChangePasswordActivity target, View source) {
    this.target = target;

    target.changePasswordOld = Utils.findRequiredViewAsType(source, R.id.change_password_old, "field 'changePasswordOld'", EditText.class);
    target.changePasswordNew = Utils.findRequiredViewAsType(source, R.id.change_password_new, "field 'changePasswordNew'", EditText.class);
    target.changePasswordNewAgain = Utils.findRequiredViewAsType(source, R.id.change_password_new_again, "field 'changePasswordNewAgain'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePasswordActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.changePasswordOld = null;
    target.changePasswordNew = null;
    target.changePasswordNewAgain = null;
  }
}
