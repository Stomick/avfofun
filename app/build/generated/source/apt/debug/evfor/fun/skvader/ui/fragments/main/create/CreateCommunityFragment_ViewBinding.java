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

public class CreateCommunityFragment_ViewBinding implements Unbinder {
  private CreateCommunityFragment target;

  private View view2131361950;

  private View view2131361955;

  private View view2131361956;

  private View view2131361962;

  private View view2131361963;

  private View view2131361957;

  private View view2131361954;

  private View view2131361947;

  private View view2131361961;

  @UiThread
  public CreateCommunityFragment_ViewBinding(final CreateCommunityFragment target, View source) {
    this.target = target;

    View view;
    target.createCommAddImageView = Utils.findRequiredViewAsType(source, R.id.create_comm_add_imageView, "field 'createCommAddImageView'", ImageView.class);
    target.createCommAddImegeIcon = Utils.findRequiredViewAsType(source, R.id.create_comm_add_imege_icon, "field 'createCommAddImegeIcon'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.create_comm_add_image, "field 'createCommAddImage' and method 'onAddImage'");
    target.createCommAddImage = Utils.castView(view, R.id.create_comm_add_image, "field 'createCommAddImage'", FrameLayout.class);
    view2131361950 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onAddImage();
      }
    });
    target.createCommunityTitle = Utils.findRequiredViewAsType(source, R.id.create_community_title, "field 'createCommunityTitle'", EditText.class);
    target.createCommAddDescription = Utils.findRequiredViewAsType(source, R.id.create_comm_add_description, "field 'createCommAddDescription'", EditText.class);
    target.createCommAddVideo = Utils.findRequiredViewAsType(source, R.id.create_comm_add_video, "field 'createCommAddVideo'", EditText.class);
    target.createCommunityMaxParts = Utils.findRequiredViewAsType(source, R.id.create_community_max_parts, "field 'createCommunityMaxParts'", EditText.class);
    target.createCommunityPrice = Utils.findRequiredViewAsType(source, R.id.create_community_price, "field 'createCommunityPrice'", EditText.class);
    target.createCommunityModeration = Utils.findRequiredViewAsType(source, R.id.create_community_moderation, "field 'createCommunityModeration'", Switch.class);
    view = Utils.findRequiredView(source, R.id.create_community_category, "field 'createCommunityCategory' and method 'onCreateCommunityCategoryClicked'");
    target.createCommunityCategory = Utils.castView(view, R.id.create_community_category, "field 'createCommunityCategory'", TextView.class);
    view2131361955 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityCategoryClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_community_days, "field 'createCommunityDays' and method 'onCreateCommunityDaysClicked'");
    target.createCommunityDays = Utils.castView(view, R.id.create_community_days, "field 'createCommunityDays'", TextView.class);
    view2131361956 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityDaysClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_community_time_begin, "field 'createCommunityTimeBegin' and method 'onCreateCommunityTimeBeginClicked'");
    target.createCommunityTimeBegin = Utils.castView(view, R.id.create_community_time_begin, "field 'createCommunityTimeBegin'", TextView.class);
    view2131361962 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityTimeBeginClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_community_time_end, "field 'createCommunityTimeEnd' and method 'onCreateCommunityTimeEndClicked'");
    target.createCommunityTimeEnd = Utils.castView(view, R.id.create_community_time_end, "field 'createCommunityTimeEnd'", TextView.class);
    view2131361963 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityTimeEndClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_community_location, "field 'createCommunityLocation' and method 'onCreateCommunityLocationClicked'");
    target.createCommunityLocation = Utils.castView(view, R.id.create_community_location, "field 'createCommunityLocation'", TextView.class);
    view2131361957 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityLocationClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_community_age_limit, "field 'createCommunityAgeLimit' and method 'onCreateCommunityAgeLimitClicked'");
    target.createCommunityAgeLimit = Utils.castView(view, R.id.create_community_age_limit, "field 'createCommunityAgeLimit'", TextView.class);
    view2131361954 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityAgeLimitClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_add_more, "field 'createAddMore' and method 'onCreateAddMoreClicked'");
    target.createAddMore = Utils.castView(view, R.id.create_add_more, "field 'createAddMore'", TextView.class);
    view2131361947 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateAddMoreClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.create_community_publish_button, "field 'button' and method 'onCreateCommunityPublishButtonClicked'");
    target.button = Utils.castView(view, R.id.create_community_publish_button, "field 'button'", Button.class);
    view2131361961 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCreateCommunityPublishButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CreateCommunityFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.createCommAddImageView = null;
    target.createCommAddImegeIcon = null;
    target.createCommAddImage = null;
    target.createCommunityTitle = null;
    target.createCommAddDescription = null;
    target.createCommAddVideo = null;
    target.createCommunityMaxParts = null;
    target.createCommunityPrice = null;
    target.createCommunityModeration = null;
    target.createCommunityCategory = null;
    target.createCommunityDays = null;
    target.createCommunityTimeBegin = null;
    target.createCommunityTimeEnd = null;
    target.createCommunityLocation = null;
    target.createCommunityAgeLimit = null;
    target.createAddMore = null;
    target.button = null;

    view2131361950.setOnClickListener(null);
    view2131361950 = null;
    view2131361955.setOnClickListener(null);
    view2131361955 = null;
    view2131361956.setOnClickListener(null);
    view2131361956 = null;
    view2131361962.setOnClickListener(null);
    view2131361962 = null;
    view2131361963.setOnClickListener(null);
    view2131361963 = null;
    view2131361957.setOnClickListener(null);
    view2131361957 = null;
    view2131361954.setOnClickListener(null);
    view2131361954 = null;
    view2131361947.setOnClickListener(null);
    view2131361947 = null;
    view2131361961.setOnClickListener(null);
    view2131361961 = null;
  }
}
