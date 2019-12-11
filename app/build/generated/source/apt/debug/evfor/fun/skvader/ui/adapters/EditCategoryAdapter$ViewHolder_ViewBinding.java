// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.adapters;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditCategoryAdapter$ViewHolder_ViewBinding implements Unbinder {
  private EditCategoryAdapter.ViewHolder target;

  @UiThread
  public EditCategoryAdapter$ViewHolder_ViewBinding(EditCategoryAdapter.ViewHolder target,
      View source) {
    this.target = target;

    target.editCategoriesText = Utils.findRequiredViewAsType(source, R.id.edit_categories_text, "field 'editCategoriesText'", TextView.class);
    target.checkBox = Utils.findRequiredViewAsType(source, R.id.edit_categories_box, "field 'checkBox'", CheckBox.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditCategoryAdapter.ViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editCategoriesText = null;
    target.checkBox = null;
  }
}
