package evfor.fun.skvader.ui.models;

import android.support.annotation.NonNull;

public class UiCheckedItem<T extends Comparable<T>> implements Comparable<UiCheckedItem<T>> {
    public T item;
    public boolean checked = false;

    public UiCheckedItem(T item) {
        this.item = item;
    }

    @Override
    public int compareTo(@NonNull UiCheckedItem<T> tUiCheckedItem) {
        return item.compareTo(tUiCheckedItem.item);
    }
}
