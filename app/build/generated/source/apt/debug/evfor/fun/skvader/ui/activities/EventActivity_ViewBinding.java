// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EventActivity_ViewBinding implements Unbinder {
  private EventActivity target;

  private View view2131362053;

  private View view2131362045;

  private View view2131362061;

  @UiThread
  public EventActivity_ViewBinding(EventActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EventActivity_ViewBinding(final EventActivity target, View source) {
    this.target = target;

    View view;
    target.root = Utils.findRequiredView(source, R.id.root, "field 'root'");
    target.eventYoutube = Utils.findRequiredViewAsType(source, R.id.event_youtube, "field 'eventYoutube'", ImageView.class);
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.titleCreator = Utils.findRequiredView(source, R.id.event_organizer_title, "field 'titleCreator'");
    target.titleSep = Utils.findRequiredView(source, R.id.event_creator_separator, "field 'titleSep'");
    target.eventDate = Utils.findRequiredViewAsType(source, R.id.event_date, "field 'eventDate'", TextView.class);
    target.eventAddress = Utils.findRequiredViewAsType(source, R.id.event_address, "field 'eventAddress'", TextView.class);
    target.eventPrice = Utils.findRequiredViewAsType(source, R.id.event_price, "field 'eventPrice'", TextView.class);
    target.eventCount = Utils.findRequiredViewAsType(source, R.id.event_count, "field 'eventCount'", TextView.class);
    target.eventDescription = Utils.findRequiredViewAsType(source, R.id.event_description, "field 'eventDescription'", TextView.class);
    target.eventParts = Utils.findRequiredViewAsType(source, R.id.event_parts, "field 'eventParts'", RecyclerView.class);
    target.eventImage = Utils.findRequiredViewAsType(source, R.id.event_image, "field 'eventImage'", ImageView.class);
    target.eventTitle = Utils.findRequiredViewAsType(source, R.id.event_title, "field 'eventTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.event_go_btn, "field 'goBtn' and method 'onEventGoBtnClicked'");
    target.goBtn = Utils.castView(view, R.id.event_go_btn, "field 'goBtn'", Button.class);
    view2131362053 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEventGoBtnClicked();
      }
    });
    target.eventCreatorView = Utils.findRequiredView(source, R.id.event_creator, "field 'eventCreatorView'");
    view = Utils.findRequiredView(source, R.id.event_chat, "field 'chat' and method 'onEventChatClicked'");
    target.chat = view;
    view2131362045 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEventChatClicked();
      }
    });
    target.partsTitle = Utils.findRequiredView(source, R.id.event_parts_title, "field 'partsTitle'");
    view = Utils.findRequiredView(source, R.id.event_party_btn, "method 'onEventPartyBtnClicked'");
    view2131362061 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEventPartyBtnClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EventActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.root = null;
    target.eventYoutube = null;
    target.toolbar = null;
    target.titleCreator = null;
    target.titleSep = null;
    target.eventDate = null;
    target.eventAddress = null;
    target.eventPrice = null;
    target.eventCount = null;
    target.eventDescription = null;
    target.eventParts = null;
    target.eventImage = null;
    target.eventTitle = null;
    target.goBtn = null;
    target.eventCreatorView = null;
    target.chat = null;
    target.partsTitle = null;

    view2131362053.setOnClickListener(null);
    view2131362053 = null;
    view2131362045.setOnClickListener(null);
    view2131362045 = null;
    view2131362061.setOnClickListener(null);
    view2131362061 = null;
  }
}
