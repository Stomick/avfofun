// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MySocNetActivity_ViewBinding implements Unbinder {
  private MySocNetActivity target;

  @UiThread
  public MySocNetActivity_ViewBinding(MySocNetActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MySocNetActivity_ViewBinding(MySocNetActivity target, View source) {
    this.target = target;

    target.mysocList = Utils.findRequiredViewAsType(source, R.id.mysoc_list, "field 'mysocList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MySocNetActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mysocList = null;
  }
}
