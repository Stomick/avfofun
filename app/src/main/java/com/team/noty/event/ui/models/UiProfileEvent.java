package com.team.noty.event.ui.models;

import com.team.noty.event.utils.Predicatable;

public class UiProfileEvent extends UiEvent implements Predicatable, Comparable<UiEvent> {
    public double rate;

    @Override
    public boolean contain(String word) {
        return title.toLowerCase()
                .contains(word.toLowerCase());
    }
}
