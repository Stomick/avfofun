// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.dialogs;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditTextDialog_ViewBinding implements Unbinder {
  private EditTextDialog target;

  private View view2131361856;

  @UiThread
  public EditTextDialog_ViewBinding(final EditTextDialog target, View source) {
    this.target = target;

    View view;
    target.titleView = Utils.findRequiredViewAsType(source, R.id.textView, "field 'titleView'", TextView.class);
    target.editText = Utils.findRequiredViewAsType(source, R.id.editText, "field 'editText'", EditText.class);
    view = Utils.findRequiredView(source, R.id.button, "method 'onClick'");
    view2131361856 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(Utils.castParam(p0, "doClick", 0, "onClick", 0, Button.class));
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EditTextDialog target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.titleView = null;
    target.editText = null;

    view2131361856.setOnClickListener(null);
    view2131361856 = null;
  }
}
