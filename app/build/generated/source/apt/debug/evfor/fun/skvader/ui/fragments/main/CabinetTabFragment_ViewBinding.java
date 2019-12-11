// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CabinetTabFragment_ViewBinding implements Unbinder {
  private CabinetTabFragment target;

  private View view2131361878;

  private View view2131361865;

  private View view2131361881;

  private View view2131361874;

  private View view2131361871;

  private View view2131361868;

  @UiThread
  public CabinetTabFragment_ViewBinding(final CabinetTabFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.cabinet_photo, "field 'cabinetPhoto' and method 'onSetPhoto'");
    target.cabinetPhoto = Utils.castView(view, R.id.cabinet_photo, "field 'cabinetPhoto'", ImageView.class);
    view2131361878 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSetPhoto();
      }
    });
    target.cabinetRank = Utils.findRequiredViewAsType(source, R.id.cabinet_rank, "field 'cabinetRank'", TextView.class);
    target.cabinetProgress = Utils.findRequiredViewAsType(source, R.id.cabinet_progress, "field 'cabinetProgress'", ProgressBar.class);
    target.cabinetName = Utils.findRequiredViewAsType(source, R.id.cabinet_name, "field 'cabinetName'", TextView.class);
    target.cabinetTimer = Utils.findRequiredViewAsType(source, R.id.cabinet_timer, "field 'cabinetTimer'", TextView.class);
    target.cabinetCreatedEvent = Utils.findRequiredViewAsType(source, R.id.cabinet_created_event, "field 'cabinetCreatedEvent'", TextView.class);
    target.cabinetVisitsEvent = Utils.findRequiredViewAsType(source, R.id.cabinet_visits_event, "field 'cabinetVisitsEvent'", TextView.class);
    target.cabinetAge = Utils.findRequiredViewAsType(source, R.id.cabinet_age, "field 'cabinetAge'", TextView.class);
    target.cabinetCity = Utils.findRequiredViewAsType(source, R.id.cabinet_city, "field 'cabinetCity'", TextView.class);
    target.cabinetSocial = Utils.findRequiredViewAsType(source, R.id.cabinet_social, "field 'cabinetSocial'", RecyclerView.class);
    target.cabinetPhone = Utils.findRequiredViewAsType(source, R.id.cabinet_phone, "field 'cabinetPhone'", TextView.class);
    target.cabinetMail = Utils.findRequiredViewAsType(source, R.id.cabinet_mail, "field 'cabinetMail'", TextView.class);
    target.cabinetAboutMe = Utils.findRequiredViewAsType(source, R.id.cabinet_about_me, "field 'cabinetAboutMe'", TextView.class);
    target.cabinetMyEvents = Utils.findRequiredViewAsType(source, R.id.cabinet_my_events, "field 'cabinetMyEvents'", RecyclerView.class);
    target.cabinetMyCommunity = Utils.findRequiredViewAsType(source, R.id.cabinet_my_community, "field 'cabinetMyCommunity'", RecyclerView.class);
    target.cabinetMyComments = Utils.findRequiredViewAsType(source, R.id.cabinet_my_comments, "field 'cabinetMyComments'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.cabinet_interests, "field 'cabinetInterests' and method 'onViewClicked'");
    target.cabinetInterests = view;
    view2131361865 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.cabinet_send_message, "field 'cabinetSendMessage' and method 'onSendMessage'");
    target.cabinetSendMessage = Utils.castView(view, R.id.cabinet_send_message, "field 'cabinetSendMessage'", Button.class);
    view2131361881 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendMessage();
      }
    });
    target.underPhone = Utils.findRequiredView(source, R.id.under_phone, "field 'underPhone'");
    target.underMail = Utils.findRequiredView(source, R.id.under_mail, "field 'underMail'");
    target.underInterests = Utils.findRequiredView(source, R.id.under_interests, "field 'underInterests'");
    target.barAddFriend = Utils.findRequiredViewAsType(source, R.id.cabinet_add_friend, "field 'barAddFriend'", ImageView.class);
    target.barCabinetTitle = Utils.findRequiredViewAsType(source, R.id.cabinetcabinet_title, "field 'barCabinetTitle'", TextView.class);
    target.barEdit = Utils.findRequiredViewAsType(source, R.id.cabinet_edit, "field 'barEdit'", ImageView.class);
    target.cabinetMyEvent = Utils.findRequiredViewAsType(source, R.id.cabinet_my_event, "field 'cabinetMyEvent'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cabinet_my_event_look, "field 'cabinetMyEventLook' and method 'onViewClicked'");
    target.cabinetMyEventLook = Utils.castView(view, R.id.cabinet_my_event_look, "field 'cabinetMyEventLook'", TextView.class);
    view2131361874 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.cabinetMyCommunities = Utils.findRequiredViewAsType(source, R.id.cabinet_my_communities, "field 'cabinetMyCommunities'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cabinet_my_communities_look, "field 'cabinetMyCommunitiesLook' and method 'onViewClicked'");
    target.cabinetMyCommunitiesLook = Utils.castView(view, R.id.cabinet_my_communities_look, "field 'cabinetMyCommunitiesLook'", TextView.class);
    view2131361871 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.cabinetMyCommentsTitle = Utils.findRequiredViewAsType(source, R.id.cabinet_my_comments_title, "field 'cabinetMyCommentsTitle'", TextView.class);
    view = Utils.findRequiredView(source, R.id.cabinet_my_comments_look, "field 'cabinetMyCommentsLook' and method 'onViewClicked'");
    target.cabinetMyCommentsLook = Utils.castView(view, R.id.cabinet_my_comments_look, "field 'cabinetMyCommentsLook'", TextView.class);
    view2131361868 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    CabinetTabFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.cabinetPhoto = null;
    target.cabinetRank = null;
    target.cabinetProgress = null;
    target.cabinetName = null;
    target.cabinetTimer = null;
    target.cabinetCreatedEvent = null;
    target.cabinetVisitsEvent = null;
    target.cabinetAge = null;
    target.cabinetCity = null;
    target.cabinetSocial = null;
    target.cabinetPhone = null;
    target.cabinetMail = null;
    target.cabinetAboutMe = null;
    target.cabinetMyEvents = null;
    target.cabinetMyCommunity = null;
    target.cabinetMyComments = null;
    target.cabinetInterests = null;
    target.cabinetSendMessage = null;
    target.underPhone = null;
    target.underMail = null;
    target.underInterests = null;
    target.barAddFriend = null;
    target.barCabinetTitle = null;
    target.barEdit = null;
    target.cabinetMyEvent = null;
    target.cabinetMyEventLook = null;
    target.cabinetMyCommunities = null;
    target.cabinetMyCommunitiesLook = null;
    target.cabinetMyCommentsTitle = null;
    target.cabinetMyCommentsLook = null;

    view2131361878.setOnClickListener(null);
    view2131361878 = null;
    view2131361865.setOnClickListener(null);
    view2131361865 = null;
    view2131361881.setOnClickListener(null);
    view2131361881 = null;
    view2131361874.setOnClickListener(null);
    view2131361874 = null;
    view2131361871.setOnClickListener(null);
    view2131361871 = null;
    view2131361868.setOnClickListener(null);
    view2131361868 = null;
  }
}
