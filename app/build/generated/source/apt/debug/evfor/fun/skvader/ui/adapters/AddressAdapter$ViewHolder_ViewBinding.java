// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AddressAdapter$ViewHolder_ViewBinding implements Unbinder {
  private AddressAdapter.ViewHolder target;

  @UiThread
  public AddressAdapter$ViewHolder_ViewBinding(AddressAdapter.ViewHolder target, View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.address_name, "field 'name'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.address_title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AddressAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.title = null;
  }
}
