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
import ru.rambler.libs.swipe_layout.SwipeLayout;

public class SearchEventAdapter$ViewHolder_ViewBinding implements Unbinder {
  private SearchEventAdapter.ViewHolder target;

  @UiThread
  public SearchEventAdapter$ViewHolder_ViewBinding(SearchEventAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.eventPhoto = Utils.findRequiredViewAsType(source, R.id.event_photo, "field 'eventPhoto'", ImageView.class);
    target.eventName = Utils.findRequiredViewAsType(source, R.id.event_name, "field 'eventName'", TextView.class);
    target.eventDateTime = Utils.findRequiredViewAsType(source, R.id.event_date_time, "field 'eventDateTime'", TextView.class);
    target.eventAddress = Utils.findRequiredViewAsType(source, R.id.event_address, "field 'eventAddress'", TextView.class);
    target.eventPrice = Utils.findRequiredViewAsType(source, R.id.event_price, "field 'eventPrice'", TextView.class);
    target.eventCount = Utils.findRequiredViewAsType(source, R.id.event_count, "field 'eventCount'", TextView.class);
    target.view = Utils.findRequiredView(source, R.id.space, "field 'view'");
    target.removeBtn = Utils.findRequiredView(source, R.id.search_remove_btn, "field 'removeBtn'");
    target.addBtn = Utils.findRequiredView(source, R.id.search_add_btn, "field 'addBtn'");
    target.swipeLayout = Utils.findRequiredViewAsType(source, R.id.swipper, "field 'swipeLayout'", SwipeLayout.class);
    target.check = Utils.findRequiredView(source, R.id.event_check, "field 'check'");
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchEventAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.eventPhoto = null;
    target.eventName = null;
    target.eventDateTime = null;
    target.eventAddress = null;
    target.eventPrice = null;
    target.eventCount = null;
    target.view = null;
    target.removeBtn = null;
    target.addBtn = null;
    target.swipeLayout = null;
    target.check = null;
  }
}
