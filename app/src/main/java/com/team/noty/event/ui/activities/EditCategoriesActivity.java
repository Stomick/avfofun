package com.team.noty.event.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.team.noty.event.R;
import com.team.noty.event.models.Category;
import com.team.noty.event.mvp.presenters.EditCategoriesPresenter;
import com.team.noty.event.mvp.views.EditCategoriesView;
import com.team.noty.event.ui.adapters.EditCategoryAdapter;
import com.team.noty.event.ui.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class EditCategoriesActivity extends BaseActivity implements EditCategoriesView {

    @InjectPresenter
    EditCategoriesPresenter presenter;

    @BindView(R.id.edit_categories_list)
    RecyclerView editCategoriesList;
    public static List<String> categories = null;
    private EditCategoryAdapter adapter;
    List<String> checkedCategories = new ArrayList<>();
    public static void open(Context context) {
        context.startActivity(new Intent(context, EditCategoriesActivity.class));
    }

    @Override
    protected int layout() {
        return R.layout.edit_categories_activity;
    }

    @Override
    protected void initViews(@Nullable Bundle savedInstanceState) {
        presenter.loadList();
        adapter = new EditCategoryAdapter(checkedCategories);
        RecyclerUtils.setVerticalAdapter(editCategoriesList, adapter);

    }

    @Override
    protected int menuLayout() {
        return R.menu.enter_menu;
    }

    @Override
    protected void onCreateMenu(Menu menu) {
        menu.findItem(R.id.enter_button).setTitle(R.string.accept);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.enter_button) {
            presenter.sendCategories(adapter.getCheckedListId());
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void actionBar(@NonNull ActionBar bar) {
        bar.setTitle(R.string.my_interests);
        setBackArrow(bar);
    }

    @Override
    public void loadAllCategories(List<Category> categories) {
        adapter.setList(categories);
    }

    @Override
    public void checkCategory(String id) {
        //TODO  check categories more normal)))
        if (categories!=null)
            for (String s:categories)
                adapter.check(s);
        else {
            adapter.check(id);
            checkedCategories.add(id);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onComplete() {
        finish();
    }
}
