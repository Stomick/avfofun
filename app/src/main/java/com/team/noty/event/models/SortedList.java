package com.team.noty.event.models;

import java.util.ArrayList;
import java.util.Collections;

public class SortedList<T extends Comparable<T>> extends ArrayList<T> {

    @Override
    public boolean add(T t) {
        int position = Collections.binarySearch(this, t);
        position = (position < 0) ? -position - 1 : position;
        super.add(position, t);
        return true;
    }
}
