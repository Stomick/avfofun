// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ActListFragment_ViewBinding implements Unbinder {
  private ActListFragment target;

  private View view2131362368;

  @UiThread
  public ActListFragment_ViewBinding(final ActListFragment target, View source) {
    this.target = target;

    View view;
    target.eventcomTabs = Utils.findRequiredViewAsType(source, R.id.eventcomTabs, "field 'eventcomTabs'", TabLayout.class);
    target.eventcomList = Utils.findRequiredViewAsType(source, R.id.eventcomList, "field 'eventcomList'", RecyclerView.class);
    target.spaceLeft = Utils.findRequiredView(source, R.id.space_left, "field 'spaceLeft'");
    target.title = Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", TextView.class);
    target.search = Utils.findRequiredViewAsType(source, R.id.search, "field 'search'", EditText.class);
    view = Utils.findRequiredView(source, R.id.serch_btn, "method 'onSearchClicked'");
    view2131362368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSearchClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ActListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.eventcomTabs = null;
    target.eventcomList = null;
    target.spaceLeft = null;
    target.title = null;
    target.search = null;

    view2131362368.setOnClickListener(null);
    view2131362368 = null;
  }
}
