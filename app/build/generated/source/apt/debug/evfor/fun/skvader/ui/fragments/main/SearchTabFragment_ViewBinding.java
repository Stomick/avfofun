// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchTabFragment_ViewBinding implements Unbinder {
  private SearchTabFragment target;

  private View view2131362350;

  private View view2131362337;

  @UiThread
  public SearchTabFragment_ViewBinding(final SearchTabFragment target, View source) {
    this.target = target;

    View view;
    target.eventsView = Utils.findRequiredViewAsType(source, R.id.search_tab_events, "field 'eventsView'", RecyclerView.class);
    target.categoriesView = Utils.findRequiredViewAsType(source, R.id.search_tab_categories, "field 'categoriesView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.search_navigation, "method 'onViewClicked'");
    view2131362350 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.search, "method 'onFocusChange'");
    view2131362337 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFocusChange();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchTabFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.eventsView = null;
    target.categoriesView = null;

    view2131362350.setOnClickListener(null);
    view2131362350 = null;
    view2131362337.setOnClickListener(null);
    view2131362337 = null;
  }
}
