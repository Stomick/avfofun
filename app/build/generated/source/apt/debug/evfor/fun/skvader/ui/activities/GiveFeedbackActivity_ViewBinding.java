// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;

public class GiveFeedbackActivity_ViewBinding implements Unbinder {
  private GiveFeedbackActivity target;

  private View view2131362107;

  private TextWatcher view2131362107TextWatcher;

  private View view2131362103;

  @UiThread
  public GiveFeedbackActivity_ViewBinding(GiveFeedbackActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public GiveFeedbackActivity_ViewBinding(final GiveFeedbackActivity target, View source) {
    this.target = target;

    View view;
    target.giveImage = Utils.findRequiredViewAsType(source, R.id.give_image, "field 'giveImage'", ImageView.class);
    target.giveTitle = Utils.findRequiredViewAsType(source, R.id.give_title, "field 'giveTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.give_description_field, "field 'giveDescriptionField' and method 'textChange'");
    target.giveDescriptionField = Utils.castView(view, R.id.give_description_field, "field 'giveDescriptionField'", EditText.class);
    view2131362107 = view;
    view2131362107TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.textChange();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131362107TextWatcher);
    target.creatorView = Utils.findRequiredView(source, R.id.give_creator, "field 'creatorView'");
    target.ratingBar = Utils.findRequiredViewAsType(source, R.id.give_mark_field, "field 'ratingBar'", MaterialRatingBar.class);
    view = Utils.findRequiredView(source, R.id.give_button, "field 'button' and method 'onGiveButtonClicked'");
    target.button = Utils.castView(view, R.id.give_button, "field 'button'", Button.class);
    view2131362103 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGiveButtonClicked(Utils.castParam(p0, "doClick", 0, "onGiveButtonClicked", 0, Button.class));
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    GiveFeedbackActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.giveImage = null;
    target.giveTitle = null;
    target.giveDescriptionField = null;
    target.creatorView = null;
    target.ratingBar = null;
    target.button = null;

    ((TextView) view2131362107).removeTextChangedListener(view2131362107TextWatcher);
    view2131362107TextWatcher = null;
    view2131362107 = null;
    view2131362103.setOnClickListener(null);
    view2131362103 = null;
  }
}
