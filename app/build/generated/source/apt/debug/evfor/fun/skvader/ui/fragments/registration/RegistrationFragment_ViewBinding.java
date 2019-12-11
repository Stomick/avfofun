// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.registration;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class RegistrationFragment_ViewBinding implements Unbinder {
  private RegistrationFragment target;

  private View view2131362306;

  private View view2131362304;

  @UiThread
  public RegistrationFragment_ViewBinding(final RegistrationFragment target, View source) {
    this.target = target;

    View view;
    target.registrationRbMale = Utils.findRequiredViewAsType(source, R.id.registration_rb_male, "field 'registrationRbMale'", RadioButton.class);
    target.registrationRbFeMale = Utils.findRequiredViewAsType(source, R.id.registration_rb_female, "field 'registrationRbFeMale'", RadioButton.class);
    target.registrationFirstName = Utils.findRequiredViewAsType(source, R.id.registration_first_name, "field 'registrationFirstName'", EditText.class);
    target.registrationLastName = Utils.findRequiredViewAsType(source, R.id.registration_last_name, "field 'registrationLastName'", EditText.class);
    view = Utils.findRequiredView(source, R.id.registration_city, "field 'registrationCity' and method 'onClickCity'");
    target.registrationCity = Utils.castView(view, R.id.registration_city, "field 'registrationCity'", TextView.class);
    view2131362306 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickCity();
      }
    });
    view = Utils.findRequiredView(source, R.id.registration_birth_date_selector, "field 'registrationBirthDateSelector' and method 'onViewClicked'");
    target.registrationBirthDateSelector = Utils.castView(view, R.id.registration_birth_date_selector, "field 'registrationBirthDateSelector'", TextView.class);
    view2131362304 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    RegistrationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.registrationRbMale = null;
    target.registrationRbFeMale = null;
    target.registrationFirstName = null;
    target.registrationLastName = null;
    target.registrationCity = null;
    target.registrationBirthDateSelector = null;

    view2131362306.setOnClickListener(null);
    view2131362306 = null;
    view2131362304.setOnClickListener(null);
    view2131362304 = null;
  }
}
