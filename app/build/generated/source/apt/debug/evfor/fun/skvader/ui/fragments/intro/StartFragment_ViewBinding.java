// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.intro;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class StartFragment_ViewBinding implements Unbinder {
  private StartFragment target;

  private View view2131362150;

  private View view2131362146;

  private View view2131362155;

  @UiThread
  public StartFragment_ViewBinding(final StartFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.intro_reg_button, "method 'onViewClicked'");
    view2131362150 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.intro_enter_button, "method 'onViewClicked'");
    view2131362146 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.intro_woutregistration_button, "method 'onViewClicked'");
    view2131362155 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131362150.setOnClickListener(null);
    view2131362150 = null;
    view2131362146.setOnClickListener(null);
    view2131362146 = null;
    view2131362155.setOnClickListener(null);
    view2131362155 = null;
  }
}
