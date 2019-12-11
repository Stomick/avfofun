// Generated code from Butter Knife. Do not modify!
package evfor.fun.skvader.ui.fragments.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import evfor.fun.skvader.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FavoriteTabFragment_ViewBinding implements Unbinder {
  private FavoriteTabFragment target;

  private View view2131362074;

  private View view2131362326;

  @UiThread
  public FavoriteTabFragment_ViewBinding(final FavoriteTabFragment target, View source) {
    this.target = target;

    View view;
    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.favorite_tabs, "field 'tabLayout'", TabLayout.class);
    target.sad_title = Utils.findRequiredViewAsType(source, R.id.sad_titel_textView, "field 'sad_title'", TextView.class);
    target.favoritsList = Utils.findRequiredViewAsType(source, R.id.favorits_list, "field 'favoritsList'", RecyclerView.class);
    target.favoritsEmpty = Utils.findRequiredViewAsType(source, R.id.favorits_empty, "field 'favoritsEmpty'", LinearLayout.class);
    target.left = Utils.findRequiredView(source, R.id.space_left, "field 'left'");
    target.right = Utils.findRequiredView(source, R.id.favorite_serch_btn, "field 'right'");
    target.favoritesSearch = Utils.findRequiredViewAsType(source, R.id.favorites_search, "field 'favoritesSearch'", EditText.class);
    view = Utils.findRequiredView(source, R.id.favorite_spinner, "method 'clickPopup'");
    view2131362074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.clickPopup(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.sad_title_button, "method 'onViewClicked'");
    view2131362326 = view;
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
    FavoriteTabFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.sad_title = null;
    target.favoritsList = null;
    target.favoritsEmpty = null;
    target.left = null;
    target.right = null;
    target.favoritesSearch = null;

    view2131362074.setOnClickListener(null);
    view2131362074 = null;
    view2131362326.setOnClickListener(null);
    view2131362326 = null;
  }
}
