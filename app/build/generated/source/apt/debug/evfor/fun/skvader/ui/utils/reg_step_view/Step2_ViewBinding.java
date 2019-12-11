// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.utils.reg_step_view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Step2_ViewBinding implements Unbinder {
  private Step2 target;

  @UiThread
  public Step2_ViewBinding(Step2 target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.reg_step2_imageView, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Step2 target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
  }
}
