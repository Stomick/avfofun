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

public class PopupBot_ViewBinding implements Unbinder {
  private PopupBot target;

  @UiThread
  public PopupBot_ViewBinding(PopupBot target, View source) {
    this.target = target;

    target.top = Utils.findRequiredView(source, R.id.top, "field 'top'");
    target.bot = Utils.findRequiredView(source, R.id.bot, "field 'bot'");
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.popup_list, "field 'recyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PopupBot target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.top = null;
    target.bot = null;
    target.recyclerView = null;
  }
}
