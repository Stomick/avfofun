// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoViewActivity_ViewBinding implements Unbinder {
  private PhotoViewActivity target;

  @UiThread
  public PhotoViewActivity_ViewBinding(PhotoViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoViewActivity_ViewBinding(PhotoViewActivity target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.image_view, "field 'imageView'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhotoViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.imageView = null;
  }
}
