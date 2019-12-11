// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectAddressActivity_ViewBinding implements Unbinder {
  private SelectAddressActivity target;

  private View view2131362361;

  private View view2131362357;

  private View view2131362360;

  @UiThread
  public SelectAddressActivity_ViewBinding(SelectAddressActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectAddressActivity_ViewBinding(final SelectAddressActivity target, View source) {
    this.target = target;

    View view;
    target.selectAddressField = Utils.findRequiredViewAsType(source, R.id.select_address_field, "field 'selectAddressField'", EditText.class);
    target.selectAddressList = Utils.findRequiredViewAsType(source, R.id.select_address_list, "field 'selectAddressList'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.select_address_map, "field 'selectAddressMap' and method 'onViewClicked'");
    target.selectAddressMap = Utils.castView(view, R.id.select_address_map, "field 'selectAddressMap'", TextView.class);
    view2131362361 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.select_address_close, "method 'onSelectAddressCloseClicked'");
    view2131362357 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectAddressCloseClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.select_address_location, "method 'onSelectAddressLocationClicked'");
    view2131362360 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSelectAddressLocationClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectAddressActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.selectAddressField = null;
    target.selectAddressList = null;
    target.selectAddressMap = null;

    view2131362361.setOnClickListener(null);
    view2131362361 = null;
    view2131362357.setOnClickListener(null);
    view2131362357 = null;
    view2131362360.setOnClickListener(null);
    view2131362360 = null;
  }
}
