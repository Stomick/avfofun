// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities.search;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FilterActivity_ViewBinding implements Unbinder {
  private FilterActivity target;

  private View view2131362086;

  private View view2131362090;

  private View view2131362097;

  private View view2131362082;

  private View view2131362095;

  private View view2131362085;

  private View view2131362084;

  private View view2131362094;

  @UiThread
  public FilterActivity_ViewBinding(FilterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FilterActivity_ViewBinding(final FilterActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.filter_city_field, "field 'filterCityField' and method 'onViewClicked'");
    target.filterCityField = Utils.castView(view, R.id.filter_city_field, "field 'filterCityField'", TextView.class);
    view2131362086 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.filter_date_field, "field 'filterDateField' and method 'onFilterDateFieldClicked'");
    target.filterDateField = Utils.castView(view, R.id.filter_date_field, "field 'filterDateField'", TextView.class);
    view2131362090 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterDateFieldClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.filter_time_field, "field 'filterTimeField' and method 'onFilterTimeFieldClicked'");
    target.filterTimeField = Utils.castView(view, R.id.filter_time_field, "field 'filterTimeField'", TextView.class);
    view2131362097 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterTimeFieldClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.filter_age_field, "field 'filterAgeField' and method 'onFilterAgeFieldClicked'");
    target.filterAgeField = Utils.castView(view, R.id.filter_age_field, "field 'filterAgeField'", TextView.class);
    view2131362082 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterAgeFieldClicked();
      }
    });
    target.filterCountField = Utils.findRequiredViewAsType(source, R.id.filter_count_field, "field 'filterCountField'", TextView.class);
    target.filterPriceField = Utils.findRequiredViewAsType(source, R.id.filter_price_field, "field 'filterPriceField'", TextView.class);
    view = Utils.findRequiredView(source, R.id.filter_sort_field, "field 'filterSortField' and method 'onFilterSortFieldClicked'");
    target.filterSortField = Utils.castView(view, R.id.filter_sort_field, "field 'filterSortField'", TextView.class);
    view2131362095 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterSortFieldClicked();
      }
    });
    target.checkOnlyEvent = Utils.findRequiredViewAsType(source, R.id.check_only_event, "field 'checkOnlyEvent'", CheckBox.class);
    target.checkOnlyCommunities = Utils.findRequiredViewAsType(source, R.id.check_only_communities, "field 'checkOnlyCommunities'", CheckBox.class);
    view = Utils.findRequiredView(source, R.id.filter_city_cancel, "method 'onFilterCityCancelClicked'");
    view2131362085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterCityCancelClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.filter_apply_button, "method 'onFilterApplyButtonClicked'");
    view2131362084 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterApplyButtonClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.filter_reset_button, "method 'onFilterResetButtonClicked'");
    view2131362094 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onFilterResetButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    FilterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.filterCityField = null;
    target.filterDateField = null;
    target.filterTimeField = null;
    target.filterAgeField = null;
    target.filterCountField = null;
    target.filterPriceField = null;
    target.filterSortField = null;
    target.checkOnlyEvent = null;
    target.checkOnlyCommunities = null;

    view2131362086.setOnClickListener(null);
    view2131362086 = null;
    view2131362090.setOnClickListener(null);
    view2131362090 = null;
    view2131362097.setOnClickListener(null);
    view2131362097 = null;
    view2131362082.setOnClickListener(null);
    view2131362082 = null;
    view2131362095.setOnClickListener(null);
    view2131362095 = null;
    view2131362085.setOnClickListener(null);
    view2131362085 = null;
    view2131362084.setOnClickListener(null);
    view2131362084 = null;
    view2131362094.setOnClickListener(null);
    view2131362094 = null;
  }
}
