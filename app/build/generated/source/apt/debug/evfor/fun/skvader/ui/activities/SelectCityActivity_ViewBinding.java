// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SelectCityActivity_ViewBinding implements Unbinder {
  private SelectCityActivity target;

  private View view2131361903;

  private View view2131361904;

  private View view2131361905;

  private TextWatcher view2131361905TextWatcher;

  @UiThread
  public SelectCityActivity_ViewBinding(SelectCityActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SelectCityActivity_ViewBinding(final SelectCityActivity target, View source) {
    this.target = target;

    View view;
    target.recyclerView = Utils.findRequiredViewAsType(source, R.id.select_city_list, "field 'recyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.city_close, "method 'onViewClicked'");
    view2131361903 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.city_location, "method 'onLocationClicked'");
    view2131361904 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLocationClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.city_search, "method 'searchTextChange'");
    view2131361905 = view;
    view2131361905TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.searchTextChange(p0);
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131361905TextWatcher);
  }

  @Override
  @CallSuper
  public void unbind() {
    SelectCityActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.recyclerView = null;

    view2131361903.setOnClickListener(null);
    view2131361903 = null;
    view2131361904.setOnClickListener(null);
    view2131361904 = null;
    ((TextView) view2131361905).removeTextChangedListener(view2131361905TextWatcher);
    view2131361905TextWatcher = null;
    view2131361905 = null;
  }
}
