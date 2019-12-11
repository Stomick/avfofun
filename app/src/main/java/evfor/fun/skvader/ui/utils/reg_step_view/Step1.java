package evfor.fun.skvader.ui.utils.reg_step_view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Category;
import evfor.fun.skvader.ui.adapters.InterestingAdapter;
import evfor.fun.skvader.ui.models.UiCheckedItem;
import evfor.fun.skvader.ui.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Step1 extends BaseStep {

    @BindView(R.id.reg_step1_interesting_list)
    RecyclerView recyclerView;
    private InterestingAdapter adapter;

    @Override
    public void setView(View view) {
        super.setView(view);
        RecyclerUtils.setVerticalTwoCol(recyclerView);
        if (adapter == null)
            adapter = new InterestingAdapter(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }

    public void setContent(List<Category> items) {
        if (adapter != null)
            adapter.setList(items);
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        for (UiCheckedItem<Category> c : adapter.getCheckedList())
            categories.add(c.item.id);
        return categories;
    }
}
