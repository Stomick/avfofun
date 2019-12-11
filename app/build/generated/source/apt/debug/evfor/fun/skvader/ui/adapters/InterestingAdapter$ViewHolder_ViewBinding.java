// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InterestingAdapter$ViewHolder_ViewBinding implements Unbinder {
  private InterestingAdapter.ViewHolder target;

  private View view2131362144;

  private View view2131362143;

  private View view2131362142;

  @UiThread
  public InterestingAdapter$ViewHolder_ViewBinding(final InterestingAdapter.ViewHolder target,
      View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.interesting_title, "field 'title' and method 'onClick'");
    target.title = Utils.castView(view, R.id.interesting_title, "field 'title'", TextView.class);
    view2131362144 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    view = Utils.findRequiredView(source, R.id.interesting_image, "field 'image' and method 'onClick'");
    target.image = Utils.castView(view, R.id.interesting_image, "field 'image'", ImageView.class);
    view2131362143 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
    target.check = Utils.findRequiredView(source, R.id.interesting_check, "field 'check'");
    view = Utils.findRequiredView(source, R.id.interesting_filter, "field 'filter' and method 'onClick'");
    target.filter = view;
    view2131362142 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    InterestingAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.title = null;
    target.image = null;
    target.check = null;
    target.filter = null;

    view2131362144.setOnClickListener(null);
    view2131362144 = null;
    view2131362143.setOnClickListener(null);
    view2131362143 = null;
    view2131362142.setOnClickListener(null);
    view2131362142 = null;
  }
}
