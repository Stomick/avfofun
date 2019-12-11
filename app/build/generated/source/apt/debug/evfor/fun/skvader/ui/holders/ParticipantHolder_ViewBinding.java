// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.holders;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import de.hdodenhof.circleimageview.CircleImageView;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ParticipantHolder_ViewBinding implements Unbinder {
  private ParticipantHolder target;

  @UiThread
  public ParticipantHolder_ViewBinding(ParticipantHolder target, View source) {
    this.target = target;

    target.photo = Utils.findRequiredViewAsType(source, R.id.part_photo, "field 'photo'", CircleImageView.class);
    target.name_age = Utils.findRequiredViewAsType(source, R.id.part_name_age, "field 'name_age'", TextView.class);
    target.city = Utils.findRequiredViewAsType(source, R.id.part_city, "field 'city'", TextView.class);
    target.rate = Utils.findRequiredViewAsType(source, R.id.part_organizer_rank, "field 'rate'", TextView.class);
    target.sendBtn = Utils.findRequiredView(source, R.id.part_send, "field 'sendBtn'");
    target.checkView = Utils.findRequiredView(source, R.id.part_check, "field 'checkView'");
  }

  @Override
  @CallSuper
  public void unbind() {
    ParticipantHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.photo = null;
    target.name_age = null;
    target.city = null;
    target.rate = null;
    target.sendBtn = null;
    target.checkView = null;
  }
}
