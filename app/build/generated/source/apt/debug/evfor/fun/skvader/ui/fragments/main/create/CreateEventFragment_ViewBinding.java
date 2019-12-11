// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.main.create;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CreateEventFragment_ViewBinding implements Unbinder {
  private CreateEventFragment target;

  private View view2131361947;

  private View view2131361966;

  private View view2131361971;

  private View view2131361972;

  private View view2131361978;

  private View view2131361973;

  private View view2131361970;

  private View view2131361977;

  @UiThread
  public CreateEventFragment_ViewBinding(final CreateEventFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.create_add_more, "field 'addMore' and method 'onCreateAddMore'");
    target.addMore = view;
    view2131361947 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateAddMore();
      }
    });
    target.createEventAddImageView = Utils.findRequiredViewAsType(source, R.id.create_event_add_imageView, "field 'createEventAddImageView'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.create_event_add_image, "field 'createEventAddImage' and method 'onAddImage'");
    target.createEventAddImage = Utils.castView(view, R.id.create_event_add_image, "field 'createEventAddImage'", FrameLayout.class);
    view2131361966 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddImage();
      }
    });
    target.createEventTitle = Utils.findRequiredViewAsType(source, R.id.create_event_title, "field 'createEventTitle'", EditText.class);
    target.createEventAddDescription = Utils.findRequiredViewAsType(source, R.id.create_event_add_description, "field 'createEventAddDescription'", EditText.class);
    target.createEventMaxParts = Utils.findRequiredViewAsType(source, R.id.create_event_max_parts, "field 'createEventMaxParts'", EditText.class);
    target.createEventPrice = Utils.findRequiredViewAsType(source, R.id.create_event_price, "field 'createEventPrice'", EditText.class);
    target.createEventModeration = Utils.findRequiredViewAsType(source, R.id.create_event_moderation, "field 'createEventModeration'", Switch.class);
    view = Utils.findRequiredView(source, R.id.create_event_category, "field 'createEventCategory' and method 'onCreateEventCategoryClicked'");
    target.createEventCategory = Utils.castView(view, R.id.create_event_category, "field 'createEventCategory'", TextView.class);
    view2131361971 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateEventCategoryClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_event_date, "field 'createEventDate' and method 'onCreateEventDateClicked'");
    target.createEventDate = Utils.castView(view, R.id.create_event_date, "field 'createEventDate'", TextView.class);
    view2131361972 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateEventDateClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_event_time, "field 'createEventTime' and method 'onCreateEventTimeClicked'");
    target.createEventTime = Utils.castView(view, R.id.create_event_time, "field 'createEventTime'", TextView.class);
    view2131361978 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateEventTimeClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_event_location, "field 'createEventLocation' and method 'onCreateEventLocationClicked'");
    target.createEventLocation = Utils.castView(view, R.id.create_event_location, "field 'createEventLocation'", TextView.class);
    view2131361973 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateEventLocationClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_event_age_limit, "field 'createEventAgeLimit' and method 'onCreateEventAgeLimitClicked'");
    target.createEventAgeLimit = Utils.castView(view, R.id.create_event_age_limit, "field 'createEventAgeLimit'", TextView.class);
    view2131361970 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateEventAgeLimitClicked();
      }
    });
    target.createEventAddImegeIcon = Utils.findRequiredViewAsType(source, R.id.create_event_add_imege_icon, "field 'createEventAddImegeIcon'", ImageView.class);
    target.createEventAddVideo = Utils.findRequiredViewAsType(source, R.id.create_event_add_video, "field 'createEventAddVideo'", EditText.class);
    view = Utils.findRequiredView(source, R.id.create_event_publish_button, "field 'createEventPublishButton' and method 'onCreateEventPublishButtonClicked'");
    target.createEventPublishButton = Utils.castView(view, R.id.create_event_publish_button, "field 'createEventPublishButton'", Button.class);
    view2131361977 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateEventPublishButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateEventFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.addMore = null;
    target.createEventAddImageView = null;
    target.createEventAddImage = null;
    target.createEventTitle = null;
    target.createEventAddDescription = null;
    target.createEventMaxParts = null;
    target.createEventPrice = null;
    target.createEventModeration = null;
    target.createEventCategory = null;
    target.createEventDate = null;
    target.createEventTime = null;
    target.createEventLocation = null;
    target.createEventAgeLimit = null;
    target.createEventAddImegeIcon = null;
    target.createEventAddVideo = null;
    target.createEventPublishButton = null;

    view2131361947.setOnClickListener(null);
    view2131361947 = null;
    view2131361966.setOnClickListener(null);
    view2131361966 = null;
    view2131361971.setOnClickListener(null);
    view2131361971 = null;
    view2131361972.setOnClickListener(null);
    view2131361972 = null;
    view2131361978.setOnClickListener(null);
    view2131361978 = null;
    view2131361973.setOnClickListener(null);
    view2131361973 = null;
    view2131361970.setOnClickListener(null);
    view2131361970 = null;
    view2131361977.setOnClickListener(null);
    view2131361977 = null;
  }
}
