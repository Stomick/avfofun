// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.dialogs;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PopupMain$Adapter$ViewHolder_ViewBinding implements Unbinder {
  private PopupMain.Adapter.ViewHolder target;

  @UiThread
  public PopupMain$Adapter$ViewHolder_ViewBinding(PopupMain.Adapter.ViewHolder target,
      View source) {
    this.target = target;

    target.radioButton = Utils.findRequiredViewAsType(source, R.id.popup_item_button, "field 'radioButton'", RadioButton.class);
    target.textView = Utils.findRequiredViewAsType(source, R.id.popup_item_text, "field 'textView'", TextView.class);
    target.under_line = Utils.findRequiredView(source, R.id.under_line, "field 'under_line'");
  }

  @Override
  @CallSuper
  public void unbind() {
    PopupMain.Adapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.radioButton = null;
    target.textView = null;
    target.under_line = null;
  }
}
