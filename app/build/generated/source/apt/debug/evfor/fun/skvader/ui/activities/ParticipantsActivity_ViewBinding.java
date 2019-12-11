// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ParticipantsActivity_ViewBinding implements Unbinder {
  private ParticipantsActivity target;

  @UiThread
  public ParticipantsActivity_ViewBinding(ParticipantsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ParticipantsActivity_ViewBinding(ParticipantsActivity target, View source) {
    this.target = target;

    target.partsList = Utils.findRequiredViewAsType(source, R.id.parts_list, "field 'partsList'", RecyclerView.class);
    target.partsTab = Utils.findRequiredViewAsType(source, R.id.parts_tab, "field 'partsTab'", TabLayout.class);
    target.botTitle = Utils.findRequiredViewAsType(source, R.id.participants_bot_title, "field 'botTitle'", TextView.class);
    target.emptyText = Utils.findRequiredViewAsType(source, R.id.parts_empty_text, "field 'emptyText'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ParticipantsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.partsList = null;
    target.partsTab = null;
    target.botTitle = null;
    target.emptyText = null;
  }
}
