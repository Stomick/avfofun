// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ReviewActivity_ViewBinding implements Unbinder {
  private ReviewActivity target;

  @UiThread
  public ReviewActivity_ViewBinding(ReviewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ReviewActivity_ViewBinding(ReviewActivity target, View source) {
    this.target = target;

    target.rvReview = Utils.findRequiredViewAsType(source, R.id.rv_review, "field 'rvReview'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ReviewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvReview = null;
  }
}
