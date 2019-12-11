// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventAdapterProfile$CommViewHolder_ViewBinding extends EventAdapterProfile$ViewHolder_ViewBinding {
  private EventAdapterProfile.CommViewHolder target;

  @UiThread
  public EventAdapterProfile$CommViewHolder_ViewBinding(EventAdapterProfile.CommViewHolder target,
      View source) {
    super(target, source);

    this.target = target;

    target.address = Utils.findRequiredViewAsType(source, R.id.event_address, "field 'address'", TextView.class);
    target.count = Utils.findRequiredViewAsType(source, R.id.event_count, "field 'count'", TextView.class);
  }

  @Override
  public void unbind() {
    EventAdapterProfile.CommViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.address = null;
    target.count = null;

    super.unbind();
  }
}
