// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.pixplicity.multiviewpager.MultiViewPager;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegistrationPrewActivity_ViewBinding implements Unbinder {
  private RegistrationPrewActivity target;

  @UiThread
  public RegistrationPrewActivity_ViewBinding(RegistrationPrewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public RegistrationPrewActivity_ViewBinding(RegistrationPrewActivity target, View source) {
    this.target = target;

    target.counterPager = Utils.findRequiredViewAsType(source, R.id.reg_prew_counter, "field 'counterPager'", MultiViewPager.class);
    target.pager = Utils.findRequiredViewAsType(source, R.id.reg_prew_pager, "field 'pager'", MultiViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    RegistrationPrewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.counterPager = null;
    target.pager = null;
  }
}
