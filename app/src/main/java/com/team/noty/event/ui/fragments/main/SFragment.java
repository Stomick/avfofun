package com.team.noty.event.ui.fragments.main;

import android.view.View;
import android.widget.EditText;

import com.team.noty.event.ui.fragments.BaseFragment;
import com.team.noty.event.ui.utils.listeners.SimpleTextListener;

public abstract class SFragment  extends BaseFragment {

    private boolean isSearch = false;
    private View left, search;

    protected void setSearchElement(EditText searchET, View leftView, View searchIconView) {
        search = searchET;
        SimpleTextListener.on(searchET, this::onSearchTextChange);
        left = leftView;
        searchIconView.setOnClickListener(this::searchOnClick);
    }

    protected abstract void onSearchTextChange(CharSequence charSequence);

    private void searchOnClick(View v) {
        if (isSearch)
            searchUnSelect();
        else searchSelect();
    }

    private void searchSelect() {
        isSearch = true;
        left.setVisibility(View.GONE);
        search.setVisibility(View.VISIBLE);
    }

    private void searchUnSelect() {
        left.setVisibility(View.VISIBLE);
        search.setVisibility(View.GONE);
        isSearch = false;
    }

    @Override
    public boolean onBackPressed() {
        if (isSearch) {
            searchUnSelect();
            return true;
        } else return false;
    }
}
