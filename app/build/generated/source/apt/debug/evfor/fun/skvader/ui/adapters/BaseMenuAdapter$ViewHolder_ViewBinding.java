// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseMenuAdapter$ViewHolder_ViewBinding implements Unbinder {
  private BaseMenuAdapter.ViewHolder target;

  @UiThread
  public BaseMenuAdapter$ViewHolder_ViewBinding(BaseMenuAdapter.ViewHolder target, View source) {
    this.target = target;

    target.icon = Utils.findRequiredViewAsType(source, R.id.menu_item_icon, "field 'icon'", ImageView.class);
    target.textView = Utils.findRequiredViewAsType(source, R.id.menu_item_text, "field 'textView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseMenuAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.icon = null;
    target.textView = null;
  }
}
