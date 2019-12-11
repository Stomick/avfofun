// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class ParticipantsAdapter$ViewHolder_ViewBinding implements Unbinder {
  private ParticipantsAdapter.ViewHolder target;

  private View view2131362258;

  private View view2131362262;

  @UiThread
  public ParticipantsAdapter$ViewHolder_ViewBinding(final ParticipantsAdapter.ViewHolder target,
      View source) {
    this.target = target;

    View view;
    target.swipeLayout = Utils.findRequiredViewAsType(source, R.id.part_swipper, "field 'swipeLayout'", SwipeLayout.class);
    view = Utils.findRequiredView(source, R.id.part_left_btn, "field 'acceptBtn' and method 'onClickLeft'");
    target.acceptBtn = view;
    view2131362258 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLeft();
      }
    });
    target.partView = Utils.findRequiredView(source, R.id.part_item, "field 'partView'");
    view = Utils.findRequiredView(source, R.id.part_right_btn, "method 'onClickRight'");
    view2131362262 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickRight();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ParticipantsAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.swipeLayout = null;
    target.acceptBtn = null;
    target.partView = null;

    view2131362258.setOnClickListener(null);
    view2131362258 = null;
    view2131362262.setOnClickListener(null);
    view2131362262 = null;
  }
}
