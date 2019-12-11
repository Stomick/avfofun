// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.dialogs;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MarkSelectDialog_ViewBinding implements Unbinder {
  private MarkSelectDialog target;

  @UiThread
  public MarkSelectDialog_ViewBinding(MarkSelectDialog target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.mark_list, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MarkSelectDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
  }
}
