// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NotificationTabFragment_ViewBinding implements Unbinder {
  private NotificationTabFragment target;

  private View view2131362238;

  @UiThread
  public NotificationTabFragment_ViewBinding(final NotificationTabFragment target, View source) {
    this.target = target;

    View view;
    target.bar = Utils.findRequiredViewAsType(source, R.id.bar, "field 'bar'", LinearLayout.class);
    view = Utils.findRequiredView(source, R.id.notification_spinner, "field 'spinner' and method 'clickPopup'");
    target.spinner = Utils.castView(view, R.id.notification_spinner, "field 'spinner'", TextView.class);
    view2131362238 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickPopup();
      }
    });
    target.notificationList = Utils.findRequiredViewAsType(source, R.id.notification_list, "field 'notificationList'", RecyclerView.class);
    target.left = Utils.findRequiredView(source, R.id.left, "field 'left'");
    target.notificationSerchBtn = Utils.findRequiredViewAsType(source, R.id.notification_serch_btn, "field 'notificationSerchBtn'", ImageView.class);
    target.searchEt = Utils.findRequiredViewAsType(source, R.id.search_et, "field 'searchEt'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationTabFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.bar = null;
    target.spinner = null;
    target.notificationList = null;
    target.left = null;
    target.notificationSerchBtn = null;
    target.searchEt = null;

    view2131362238.setOnClickListener(null);
    view2131362238 = null;
  }
}
