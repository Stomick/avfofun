// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.utils.reg_step_view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Step3_ViewBinding implements Unbinder {
  private Step3 target;

  @UiThread
  public Step3_ViewBinding(Step3 target, View source) {
    this.target = target;

    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Step3 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editText = null;
  }
}
