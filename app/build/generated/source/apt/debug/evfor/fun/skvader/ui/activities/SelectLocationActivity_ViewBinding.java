// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectLocationActivity_ViewBinding implements Unbinder {
  private SelectLocationActivity target;

  private View view2131362187;

  private View view2131362188;

  @UiThread
  public SelectLocationActivity_ViewBinding(SelectLocationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectLocationActivity_ViewBinding(final SelectLocationActivity target, View source) {
    this.target = target;

    View view;
    target.mapMinus = Utils.findRequiredViewAsType(source, R.id.map_minus, "field 'mapMinus'", ImageView.class);
    target.mapPlus = Utils.findRequiredViewAsType(source, R.id.map_plus, "field 'mapPlus'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.map_exit, "method 'exit'");
    view2131362187 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.exit();
      }
    });
    view = Utils.findRequiredView(source, R.id.map_gps, "method 'myLocationClick'");
    view2131362188 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.myLocationClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectLocationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mapMinus = null;
    target.mapPlus = null;

    view2131362187.setOnClickListener(null);
    view2131362187 = null;
    view2131362188.setOnClickListener(null);
    view2131362188 = null;
  }
}
