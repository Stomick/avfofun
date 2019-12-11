// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventAdapterProfile$ViewHolder_ViewBinding implements Unbinder {
  private EventAdapterProfile.ViewHolder target;

  @UiThread
  public EventAdapterProfile$ViewHolder_ViewBinding(EventAdapterProfile.ViewHolder target,
      View source) {
    this.target = target;

    target.eventPhoto = Utils.findRequiredViewAsType(source, R.id.event_photo, "field 'eventPhoto'", ImageView.class);
    target.eventName = Utils.findRequiredViewAsType(source, R.id.event_name, "field 'eventName'", TextView.class);
    target.eventDateTime = Utils.findRequiredViewAsType(source, R.id.event_date_time, "field 'eventDateTime'", TextView.class);
    target.eventPrice = Utils.findRequiredViewAsType(source, R.id.event_price, "field 'eventPrice'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EventAdapterProfile.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.eventPhoto = null;
    target.eventName = null;
    target.eventDateTime = null;
    target.eventPrice = null;
  }
}
