// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.dialogs;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MarkSelectDialog$Adapter$ViewHolder_ViewBinding implements Unbinder {
  private MarkSelectDialog.Adapter.ViewHolder target;

  @UiThread
  public MarkSelectDialog$Adapter$ViewHolder_ViewBinding(MarkSelectDialog.Adapter.ViewHolder target,
      View source) {
    this.target = target;

    target.textView = Utils.findRequiredViewAsType(source, R.id.mark_text, "field 'textView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MarkSelectDialog.Adapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView = null;
  }
}
