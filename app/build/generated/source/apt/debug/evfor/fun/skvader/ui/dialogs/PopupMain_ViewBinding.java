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

public class PopupMain_ViewBinding implements Unbinder {
  private PopupMain target;

  @UiThread
  public PopupMain_ViewBinding(PopupMain target, View source) {
    this.target = target;

    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.popup_list, "field 'recyclerView'", RecyclerView.class);
    target.top = Utils.findRequiredView(source, R.id.top, "field 'top'");
  }

  @Override
  @CallSuper
  public void unbind() {
    PopupMain target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;
    target.top = null;
  }
}
