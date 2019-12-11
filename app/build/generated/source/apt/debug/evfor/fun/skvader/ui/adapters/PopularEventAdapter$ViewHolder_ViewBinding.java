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

public class PopularEventAdapter$ViewHolder_ViewBinding implements Unbinder {
  private PopularEventAdapter.ViewHolder target;

  @UiThread
  public PopularEventAdapter$ViewHolder_ViewBinding(PopularEventAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.logo = Utils.findRequiredViewAsType(source, R.id.popular_item_logo, "field 'logo'", ImageView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.popular_item_title, "field 'title'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PopularEventAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.logo = null;
    target.title = null;
  }
}
