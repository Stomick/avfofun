// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.utils.reg_step_view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Step4_ViewBinding implements Unbinder {
  private Step4 target;

  private View view2131362396;

  private View view2131362395;

  @UiThread
  public Step4_ViewBinding(final Step4 target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.step4_search_company, "method 'onSearch'");
    view2131362396 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearch();
      }
    });
    view = Utils.findRequiredView(source, R.id.step4_create, "method 'onCreate'");
    view2131362395 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreate();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131362396.setOnClickListener(null);
    view2131362396 = null;
    view2131362395.setOnClickListener(null);
    view2131362395 = null;
  }
}
