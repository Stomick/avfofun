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

public class SocAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SocAdapter.ViewHolder target;

  @UiThread
  public SocAdapter$ViewHolder_ViewBinding(SocAdapter.ViewHolder target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.soc_item_image, "field 'imageView'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.soc_item_text, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SocAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
    target.title = null;
  }
}
