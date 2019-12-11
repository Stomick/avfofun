package com.team.noty.event.ui.adapters;

import com.team.noty.event.ui.models.UiCheckedItem;

import java.util.ArrayList;
import java.util.List;

public abstract class CheckedAdapter<T extends Comparable<T>, H extends BaseAdapter.ViewHolder>
        extends ListAdapter<UiCheckedItem<T>, H> {

    CheckedAdapter(List<UiCheckedItem<T>> list) {
        super(list);
    }

    public void setTList(List<T> list) {
        List<UiCheckedItem<T>> list1 = new ArrayList<>(list.size());
        for(T t:list)
            list1.add(new UiCheckedItem<>(t));
        super.setList(list1);
    }

    public List<T> selectedIdList() {
        List<T> eventList = new ArrayList<>();
        for (UiCheckedItem<T> t : list)
            if (t.checked)
                eventList.add(t.item);
        return eventList;
    }
}
