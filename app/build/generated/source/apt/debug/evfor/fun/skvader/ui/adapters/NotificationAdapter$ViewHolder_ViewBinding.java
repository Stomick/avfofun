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

public class NotificationAdapter$ViewHolder_ViewBinding implements Unbinder {
  private NotificationAdapter.ViewHolder target;

  @UiThread
  public NotificationAdapter$ViewHolder_ViewBinding(NotificationAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.notifyAva = Utils.findRequiredViewAsType(source, R.id.notify_ava, "field 'notifyAva'", ImageView.class);
    target.notifySender = Utils.findRequiredViewAsType(source, R.id.notify_sender, "field 'notifySender'", TextView.class);
    target.notifyTime = Utils.findRequiredViewAsType(source, R.id.notify_time, "field 'notifyTime'", TextView.class);
    target.notifyWhat = Utils.findRequiredViewAsType(source, R.id.notify_why, "field 'notifyWhat'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NotificationAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.notifyAva = null;
    target.notifySender = null;
    target.notifyTime = null;
    target.notifyWhat = null;
  }
}
