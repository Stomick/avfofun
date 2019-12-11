// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageAdapter$TextViewHolder_ViewBinding extends MessageAdapter$BaseViewHolder_ViewBinding {
  private MessageAdapter.TextViewHolder target;

  @UiThread
  public MessageAdapter$TextViewHolder_ViewBinding(MessageAdapter.TextViewHolder target,
      View source) {
    super(target, source);

    this.target = target;

    target.textView = Utils.findRequiredViewAsType(source, R.id.message_text, "field 'textView'", TextView.class);
  }

  @Override
  public void unbind() {
    MessageAdapter.TextViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textView = null;

    super.unbind();
  }
}
