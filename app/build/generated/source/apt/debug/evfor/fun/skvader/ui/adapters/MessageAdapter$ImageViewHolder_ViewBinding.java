// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MessageAdapter$ImageViewHolder_ViewBinding extends MessageAdapter$BaseViewHolder_ViewBinding {
  private MessageAdapter.ImageViewHolder target;

  @UiThread
  public MessageAdapter$ImageViewHolder_ViewBinding(MessageAdapter.ImageViewHolder target,
      View source) {
    super(target, source);

    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.message_image, "field 'imageView'", ImageView.class);
  }

  @Override
  public void unbind() {
    MessageAdapter.ImageViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;

    super.unbind();
  }
}
