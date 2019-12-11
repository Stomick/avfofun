// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.registration;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class NumberPhoneFragment_ViewBinding implements Unbinder {
  private NumberPhoneFragment target;

  @UiThread
  public NumberPhoneFragment_ViewBinding(NumberPhoneFragment target, View source) {
    this.target = target;

    target.inputPhoneIndex = Utils.findRequiredViewAsType(source, R.id.input_phone_index, "field 'inputPhoneIndex'", TextView.class);
    target.phoneBotTitle = Utils.findRequiredViewAsType(source, R.id.phone_bot_title, "field 'phoneBotTitle'", TextView.class);
    target.inputPhoneCountry = Utils.findRequiredViewAsType(source, R.id.input_phone_country, "field 'inputPhoneCountry'", TextView.class);
    target.phoneNumberET = Utils.findRequiredViewAsType(source, R.id.input_phone_number, "field 'phoneNumberET'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    NumberPhoneFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.inputPhoneIndex = null;
    target.phoneBotTitle = null;
    target.inputPhoneCountry = null;
    target.phoneNumberET = null;
  }
}
