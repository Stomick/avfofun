package evfor.fun.skvader.ui.models;

import evfor.fun.skvader.utils.Predicatable;

public class UiProfileEvent extends UiEvent implements Predicatable, Comparable<UiEvent> {
    public double rate;

    @Override
    public boolean contain(String word) {
        return title.toLowerCase()
                .contains(word.toLowerCase());
    }
}
