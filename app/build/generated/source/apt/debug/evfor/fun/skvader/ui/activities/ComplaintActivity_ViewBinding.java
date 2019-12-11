// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.CharSequence;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ComplaintActivity_ViewBinding implements Unbinder {
  private ComplaintActivity target;

  private View view2131361930;

  private View view2131361928;

  private TextWatcher view2131361928TextWatcher;

  private View view2131361931;

  private View view2131361927;

  @UiThread
  public ComplaintActivity_ViewBinding(ComplaintActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ComplaintActivity_ViewBinding(final ComplaintActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.comp_select, "field 'compSelect' and method 'onCompSelectClicked'");
    target.compSelect = Utils.castView(view, R.id.comp_select, "field 'compSelect'", TextView.class);
    view2131361930 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCompSelectClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.comp_description, "field 'compDescription' and method 'onTextChanged'");
    target.compDescription = Utils.castView(view, R.id.comp_description, "field 'compDescription'", EditText.class);
    view2131361928 = view;
    view2131361928TextWatcher = new TextWatcher() {
      @Override
      public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
        target.onTextChanged();
      }

      @Override
      public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
      }

      @Override
      public void afterTextChanged(Editable p0) {
      }
    };
    ((TextView) view).addTextChangedListener(view2131361928TextWatcher);
    view = Utils.findRequiredView(source, R.id.comp_select_title, "field 'compSelectTitle' and method 'onCompSelectClicked'");
    target.compSelectTitle = Utils.castView(view, R.id.comp_select_title, "field 'compSelectTitle'", TextView.class);
    view2131361931 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCompSelectClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.comp_button, "field 'compButton' and method 'onCompButtonClicked'");
    target.compButton = Utils.castView(view, R.id.comp_button, "field 'compButton'", Button.class);
    view2131361927 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onCompButtonClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ComplaintActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.compSelect = null;
    target.compDescription = null;
    target.compSelectTitle = null;
    target.compButton = null;

    view2131361930.setOnClickListener(null);
    view2131361930 = null;
    ((TextView) view2131361928).removeTextChangedListener(view2131361928TextWatcher);
    view2131361928TextWatcher = null;
    view2131361928 = null;
    view2131361931.setOnClickListener(null);
    view2131361931 = null;
    view2131361927.setOnClickListener(null);
    view2131361927 = null;
  }
}
