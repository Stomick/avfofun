package evfor.fun.skvader.ui.activities.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.arellomobile.mvp.presenter.InjectPresenter;
import evfor.fun.skvader.R;
import evfor.fun.skvader.models.Act;
import evfor.fun.skvader.models.FilterModel;
import evfor.fun.skvader.mvp.presenters.SearchPresenter;
import evfor.fun.skvader.mvp.views.SearchView;
import evfor.fun.skvader.ui.activities.BaseActivity;
import evfor.fun.skvader.ui.activities.EventActivity;
import evfor.fun.skvader.ui.adapters.SearchEventAdapter;
import evfor.fun.skvader.ui.utils.RecyclerUtils;
import evfor.fun.skvader.ui.utils.listeners.SimpleTextListener;

import java.util.List;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements SearchView {

    private static final String CATEGORY = "category";
    private static final String DATE = "date";

    public static void open(Context context, String categoryId) {
        context.startActivity(new Intent(context, SearchActivity.class).putExtra(CATEGORY, categoryId));
    }

    public static Intent getIntent(Context context, String categoryId) {
        return new Intent(context, SearchActivity.class).putExtra(CATEGORY, categoryId);
    }

    public static Intent getIntent(Context context, String categoryId, String date) {
        return new Intent(context, SearchActivity.class).putExtra(CATEGORY, categoryId).putExtra(DATE, date);
    }

    @InjectPresenter
    SearchPresenter presenter;

    @BindView(R.id.search_list)
    RecyclerView listView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.search)
    EditText search;
    private MenuItem setting, remove;

    private SearchEventAdapter adapter;
    SimpleTextListener textListener;

    @Override
    protected int layout() {
        return R.layout.activity_search;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        adapter = new SearchEventAdapter();
        adapter.setRemoveId(presenter::removeId);
        adapter.setAddId(presenter::requestIn);
        adapter.setTitleCall(this::setTitle);
        RecyclerUtils.setVerticalAdapter(listView, adapter);
        setSupportActionBar(toolbar);
        adapter.setCallBackId(this::openEvent);
        presenter.loadList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textListener.remove();
    }

    @Override
    protected void onResume() {
        textListener = SimpleTextListener.on(search,
                charSequence -> presenter.loadList(charSequence.toString()));
        super.onResume();
        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(CATEGORY)) {
            if (getIntent().getExtras().containsKey(DATE))
                presenter.loadListWithCategoryAndDate(
                        getIntent().getExtras().getString(CATEGORY),
                        getIntent().getExtras().getString(DATE));
            else
                presenter.loadListWithCategory(getIntent().getExtras().getString(CATEGORY));
            getIntent().removeExtra(CATEGORY);
        }
    }

    private void openEvent(Act act) {
        textListener.remove();
        EventActivity.open(this, act);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        setBackArrow(bar);
        bar.setTitle("");
    }

    private void setTitle(String title) {
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(title);
        search.setVisibility(title.isEmpty() ? View.VISIBLE : View.GONE);
        if (title.isEmpty()) {
            setting.setVisible(true);
            remove.setVisible(false);
        } else {
            setting.setVisible(false);
            remove.setVisible(true);
        }
    }

    @Override
    protected int menuLayout() {
        return R.menu.search_menu;
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        setting = menu.findItem(R.id.setting);
        remove = menu.findItem(R.id.remove);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.setting:
                presenter.openFilter();
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.remove:
                for (Act act : adapter.selectedIdList())
                    presenter.removeId(act);
                adapter.removeSelected();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openFilter(FilterModel model) {
        FilterActivity.start(this, model);
    }

    @Override
    public void showList(List<Act> events) {
        if (adapter != null)
            adapter.setTList(events);
    }

    @Override
    public void onBackPressed() {
        if (adapter.isSelectMode())
            adapter.setSelectMode(false);
        else {
            textListener.remove();
            search.setText("");
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        FilterModel model = FilterActivity.getResult(data);
        if (model != null)
            presenter.request(model);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
