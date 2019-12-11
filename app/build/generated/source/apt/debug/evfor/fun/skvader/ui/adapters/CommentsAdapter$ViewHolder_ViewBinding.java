// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class CommentsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private CommentsAdapter.ViewHolder target;

  @UiThread
  public CommentsAdapter$ViewHolder_ViewBinding(CommentsAdapter.ViewHolder target, View source) {
    this.target = target;

    target.clMain = Utils.findRequiredViewAsType(source, R.id.cl_main, "field 'clMain'", ConstraintLayout.class);
    target.commnetPhoto = Utils.findRequiredViewAsType(source, R.id.commnet_photo, "field 'commnetPhoto'", CircleImageView.class);
    target.commentName = Utils.findRequiredViewAsType(source, R.id.comment_name, "field 'commentName'", TextView.class);
    target.commentRating = Utils.findRequiredViewAsType(source, R.id.comment_rating, "field 'commentRating'", MaterialRatingBar.class);
    target.commentDate = Utils.findRequiredViewAsType(source, R.id.comment_date, "field 'commentDate'", TextView.class);
    target.commentText = Utils.findRequiredViewAsType(source, R.id.comment_text, "field 'commentText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    CommentsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.clMain = null;
    target.commnetPhoto = null;
    target.commentName = null;
    target.commentRating = null;
    target.commentDate = null;
    target.commentText = null;
  }
}
