// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class IntroActivity_ViewBinding implements Unbinder {
  private IntroActivity target;

  @UiThread
  public IntroActivity_ViewBinding(IntroActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public IntroActivity_ViewBinding(IntroActivity target, View source) {
    this.target = target;

    target.introToolbar = Utils.findRequiredViewAsType(source, R.id.intro_toolbar, "field 'introToolbar'", Toolbar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    IntroActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.introToolbar = null;
  }
}
