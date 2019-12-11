// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateTabFragment_ViewBinding implements Unbinder {
  private CreateTabFragment target;

  private View view2131361948;

  @UiThread
  public CreateTabFragment_ViewBinding(final CreateTabFragment target, View source) {
    this.target = target;

    View view;
    target.favoriteTabs = Utils.findRequiredViewAsType(source, R.id.favorite_tabs, "field 'favoriteTabs'", TabLayout.class);
    view = Utils.findRequiredView(source, R.id.create_cancel, "method 'onViewClicked'");
    view2131361948 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateTabFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.favoriteTabs = null;

    view2131361948.setOnClickListener(null);
    view2131361948 = null;
  }
}
