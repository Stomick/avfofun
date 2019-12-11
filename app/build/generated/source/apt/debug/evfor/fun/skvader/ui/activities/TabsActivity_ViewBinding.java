// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TabsActivity_ViewBinding implements Unbinder {
  private TabsActivity target;

  @UiThread
  public TabsActivity_ViewBinding(TabsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TabsActivity_ViewBinding(TabsActivity target, View source) {
    this.target = target;

    target.navigation = Utils.findRequiredViewAsType(source, R.id.navigation, "field 'navigation'", AHBottomNavigation.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TabsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.navigation = null;
  }
}
