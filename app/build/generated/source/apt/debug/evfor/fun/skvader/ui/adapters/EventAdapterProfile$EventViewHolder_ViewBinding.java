// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventAdapterProfile$EventViewHolder_ViewBinding extends EventAdapterProfile$ViewHolder_ViewBinding {
  private EventAdapterProfile.EventViewHolder target;

  @UiThread
  public EventAdapterProfile$EventViewHolder_ViewBinding(EventAdapterProfile.EventViewHolder target,
      View source) {
    super(target, source);

    this.target = target;

    target.eventRate = Utils.findRequiredViewAsType(source, R.id.event_rate, "field 'eventRate'", TextView.class);
  }

  @Override
  public void unbind() {
    EventAdapterProfile.EventViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.eventRate = null;

    super.unbind();
  }
}
