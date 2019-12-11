package com.team.noty.event.ui.adapters;

import android.annotation.SuppressLint;

import com.team.noty.event.ui.utils.ListFilter;
import com.team.noty.event.ui.utils.iListFilter;
import com.team.noty.event.utils.Predicatable;

import java.util.List;

public abstract class FilterableListAdapter<T extends Predicatable, H extends BaseAdapter.ViewHolder>
        extends ListAdapter<T, H> {

    private iListFilter<T> filter;

    FilterableListAdapter(List<T> list) {
        super(list);
    }

    @Override
    public void setList(List<T> list) {
        super.setList(list);
        filter = new ListFilter<>(list);
    }

    @SuppressLint("CheckResult")
    public void filter(String text) {
        filter.filter(text)
                .subscribe(this::onChangeList);
    }

    private void onChangeList(iListFilter.ChangeList<T> changeList) {
        addAll(changeList.getToAdd().listIterator());
        removeAll(changeList.getToRemove().listIterator());
    }
}
