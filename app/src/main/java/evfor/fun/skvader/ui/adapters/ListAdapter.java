package evfor.fun.skvader.ui.adapters;

import java.util.Iterator;
import java.util.List;

public abstract class ListAdapter<T, H extends BaseAdapter.ViewHolder> extends BaseAdapter<H> {

    protected List<T> list;

    ListAdapter(List<T> list) {
        setList(list);
    }

    public void setList(List<T> list) {
        if (this.list == null)
            this.list = list;
        else {
            clear();
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    void removeAll(Iterator<T> removeIterator) {
        while (removeIterator.hasNext()) {
            T item = removeIterator.next();
            if (list.contains(item))
                remove(item);
        }
    }

    void addAll(Iterator<T> addIterator) {
        while (addIterator.hasNext()) {
            T item = addIterator.next();
            addNoContains(item);
        }
    }

    public void add(T t) {
        list.add(t);
        notifyItemInserted(list.indexOf(t));
    }

    public void addNoContains(T t) {
        if (!list.contains(t))
            add(t);
    }

    public void remove(T t) {
        int index = list.indexOf(t);
        if (list.remove(t))
            notifyItemRemoved(index);
    }

    public void clear() {
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
