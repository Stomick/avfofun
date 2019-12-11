package com.team.noty.event.ui.utils;

import com.team.noty.event.utils.Predicatable;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

public interface iListFilter<T extends Predicatable> {
    Observable<ChangeList<T>> filter(String word);

    class ChangeList<T> {
        private List<T> toRemove;
        private List<T> toAdd;

        public List<T> getToRemove(){
            if(toRemove==null)
                toRemove = new ArrayList<>();
            return toRemove;
        }

        public List<T> getToAdd(){
            if(toAdd==null)
                toAdd = new ArrayList<>();
            return toAdd;
        }
    }
}
