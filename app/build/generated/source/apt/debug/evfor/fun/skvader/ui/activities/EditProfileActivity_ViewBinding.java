// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditProfileActivity_ViewBinding implements Unbinder {
  private EditProfileActivity target;

  private View view2131362015;

  private View view2131362009;

  private View view2131362023;

  private View view2131362014;

  private View view2131362017;

  private View view2131362018;

  @UiThread
  public EditProfileActivity_ViewBinding(EditProfileActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditProfileActivity_ViewBinding(final EditProfileActivity target, View source) {
    this.target = target;

    View view;
    target.registrationRbMale = Utils.findRequiredViewAsType(source, R.id.registration_rb_male, "field 'registrationRbMale'", RadioButton.class);
    target.registrationRbFemale = Utils.findRequiredViewAsType(source, R.id.registration_rb_female, "field 'registrationRbFemale'", RadioButton.class);
    target.editName = Utils.findRequiredViewAsType(source, R.id.edit_name, "field 'editName'", TextView.class);
    target.editLastname = Utils.findRequiredViewAsType(source, R.id.edit_lastname, "field 'editLastname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.edit_city, "field 'editCity' and method 'onClickCity'");
    target.editCity = Utils.castView(view, R.id.edit_city, "field 'editCity'", TextView.class);
    view2131362015 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCity();
      }
    });
    target.editPhone = Utils.findRequiredViewAsType(source, R.id.edit_phone, "field 'editPhone'", TextView.class);
    target.editMail = Utils.findRequiredViewAsType(source, R.id.edit_mail, "field 'editMail'", TextView.class);
    view = Utils.findRequiredView(source, R.id.edit_birthdate, "field 'editBirthdate' and method 'onEditBirthdateClicked'");
    target.editBirthdate = Utils.castView(view, R.id.edit_birthdate, "field 'editBirthdate'", TextView.class);
    view2131362009 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditBirthdateClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.edit_my_soc, "field 'editMySoc' and method 'onEditMySocClicked'");
    target.editMySoc = Utils.castView(view, R.id.edit_my_soc, "field 'editMySoc'", TextView.class);
    view2131362023 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditMySocClicked();
      }
    });
    target.editSendNotification = Utils.findRequiredViewAsType(source, R.id.edit_send_notification, "field 'editSendNotification'", Switch.class);
    target.editShareEvent = Utils.findRequiredViewAsType(source, R.id.edit_share_event, "field 'editShareEvent'", Switch.class);
    target.editShareCommunity = Utils.findRequiredViewAsType(source, R.id.edit_share_community, "field 'editShareCommunity'", Switch.class);
    view = Utils.findRequiredView(source, R.id.edit_change_password, "method 'onEditChangePasswordClicked'");
    view2131362014 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditChangePasswordClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.edit_exiting_account, "method 'onEditExitingAccountClicked'");
    view2131362017 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onEditExitingAccountClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.edit_feedback, "method 'onClick'");
    view2131362018 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    EditProfileActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.registrationRbMale = null;
    target.registrationRbFemale = null;
    target.editName = null;
    target.editLastname = null;
    target.editCity = null;
    target.editPhone = null;
    target.editMail = null;
    target.editBirthdate = null;
    target.editMySoc = null;
    target.editSendNotification = null;
    target.editShareEvent = null;
    target.editShareCommunity = null;

    view2131362015.setOnClickListener(null);
    view2131362015 = null;
    view2131362009.setOnClickListener(null);
    view2131362009 = null;
    view2131362023.setOnClickListener(null);
    view2131362023 = null;
    view2131362014.setOnClickListener(null);
    view2131362014 = null;
    view2131362017.setOnClickListener(null);
    view2131362017 = null;
    view2131362018.setOnClickListener(null);
    view2131362018 = null;
  }
}
