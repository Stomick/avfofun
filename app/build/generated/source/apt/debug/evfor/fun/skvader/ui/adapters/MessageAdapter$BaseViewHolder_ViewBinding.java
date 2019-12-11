// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageAdapter$BaseViewHolder_ViewBinding implements Unbinder {
  private MessageAdapter.BaseViewHolder target;

  @UiThread
  public MessageAdapter$BaseViewHolder_ViewBinding(MessageAdapter.BaseViewHolder target,
      View source) {
    this.target = target;

    target.container = Utils.findRequiredView(source, R.id.message_container, "field 'container'");
    target.separator = Utils.findRequiredViewAsType(source, R.id.message_separstor, "field 'separator'", TextView.class);
    target.status = Utils.findRequiredViewAsType(source, R.id.message_status, "field 'status'", TextView.class);
    target.nameView = Utils.findRequiredViewAsType(source, R.id.message_name, "field 'nameView'", TextView.class);
    target.bg = Utils.findRequiredView(source, R.id.message_bg, "field 'bg'");
  }

  @Override
  @CallSuper
  public void unbind() {
    MessageAdapter.BaseViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.container = null;
    target.separator = null;
    target.status = null;
    target.nameView = null;
    target.bg = null;
  }
}
