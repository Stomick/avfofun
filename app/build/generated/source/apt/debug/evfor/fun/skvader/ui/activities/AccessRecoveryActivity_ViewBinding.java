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

public class AccessRecoveryActivity_ViewBinding implements Unbinder {
  private AccessRecoveryActivity target;

  @UiThread
  public AccessRecoveryActivity_ViewBinding(AccessRecoveryActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AccessRecoveryActivity_ViewBinding(AccessRecoveryActivity target, View source) {
    this.target = target;

    target.restoreMailField = Utils.findRequiredViewAsType(source, R.id.restore_mail_field, "field 'restoreMailField'", EditText.class);
    target.progressBar = Utils.findRequiredView(source, R.id.progressBar, "field 'progressBar'");
    target.content = Utils.findRequiredView(source, R.id.content, "field 'content'");
  }

  @Override
  @CallSuper
  public void unbind() {
    AccessRecoveryActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.restoreMailField = null;
    target.progressBar = null;
    target.content = null;
  }
}
