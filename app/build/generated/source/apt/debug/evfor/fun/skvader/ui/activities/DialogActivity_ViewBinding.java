// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

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

public class DialogActivity_ViewBinding implements Unbinder {
  private DialogActivity target;

  private View view2131361998;

  private View view2131362002;

  @UiThread
  public DialogActivity_ViewBinding(DialogActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DialogActivity_ViewBinding(final DialogActivity target, View source) {
    this.target = target;

    View view;
    target.dialogMessageList = Utils.findRequiredViewAsType(source, R.id.dialog_message_list, "field 'dialogMessageList'", RecyclerView.class);
    target.inputPanel = Utils.findRequiredView(source, R.id.input_panel, "field 'inputPanel'");
    view = Utils.findRequiredView(source, R.id.dialog_attach_button, "method 'onDialogAttachButtonClicked'");
    view2131361998 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onDialogAttachButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.dialog_send_button, "method 'onSendMessage'");
    view2131362002 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendMessage();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DialogActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dialogMessageList = null;
    target.inputPanel = null;

    view2131361998.setOnClickListener(null);
    view2131361998 = null;
    view2131362002.setOnClickListener(null);
    view2131362002 = null;
  }
}
