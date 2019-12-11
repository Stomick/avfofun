// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.activities;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class EditCategoriesActivity_ViewBinding implements Unbinder {
  private EditCategoriesActivity target;

  @UiThread
  public EditCategoriesActivity_ViewBinding(EditCategoriesActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public EditCategoriesActivity_ViewBinding(EditCategoriesActivity target, View source) {
    this.target = target;

    target.editCategoriesList = Utils.findRequiredViewAsType(source, R.id.edit_categories_list, "field 'editCategoriesList'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    EditCategoriesActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.editCategoriesList = null;
  }
}
