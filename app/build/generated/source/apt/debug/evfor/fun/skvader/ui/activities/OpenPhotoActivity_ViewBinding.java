// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.theartofdev.edmodo.cropper.CropImageView;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OpenPhotoActivity_ViewBinding implements Unbinder {
  private OpenPhotoActivity target;

  @UiThread
  public OpenPhotoActivity_ViewBinding(OpenPhotoActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public OpenPhotoActivity_ViewBinding(OpenPhotoActivity target, View source) {
    this.target = target;

    target.view = Utils.findRequiredViewAsType(source, R.id.cropImageView, "field 'view'", CropImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    OpenPhotoActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.view = null;
  }
}
