// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class InputPanelHolder_ViewBinding implements Unbinder {
  private InputPanelHolder target;

  private View view2131362000;

  private TextWatcher view2131362000TextWatcher;

  @UiThread
  public InputPanelHolder_ViewBinding(final InputPanelHolder target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.dialog_editText, "field 'dialogEditText' and method 'onTextChange'");
    target.dialogEditText = Utils.castView(view, R.id.dialog_editText, "field 'dialogEditText'", EditText.class);
    view2131362000 = view;
    view2131362000TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onTextChange(p0);
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131362000TextWatcher);
    target.dialogSendButton = Utils.findRequiredViewAsType(source, R.id.dialog_send_button, "field 'dialogSendButton'", ImageView.class);
    target.linearLayout2 = Utils.findRequiredViewAsType(source, R.id.linearLayout2, "field 'linearLayout2'", LinearLayout.class);
    target.voiceCancel = Utils.findRequiredViewAsType(source, R.id.voice_cancel, "field 'voiceCancel'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    InputPanelHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dialogEditText = null;
    target.dialogSendButton = null;
    target.linearLayout2 = null;
    target.voiceCancel = null;

    ((TextView) view2131362000).removeTextChangedListener(view2131362000TextWatcher);
    view2131362000TextWatcher = null;
    view2131362000 = null;
  }
}
