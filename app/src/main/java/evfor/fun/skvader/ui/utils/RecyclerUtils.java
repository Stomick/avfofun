package evfor.fun.skvader.ui.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import evfor.fun.skvader.ui.models.UiCheckedItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerUtils {
    public static void setVerticalTwoCol(RecyclerView recyclerView) {
        GridLayoutManager manager = new GridLayoutManager(recyclerView.getContext(), 2);
        recyclerView.setLayoutManager(manager);
    }

    public static void setVerticalAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        setLinearManager(recyclerView, adapter, LinearLayoutManager.VERTICAL);
    }

    public static void setMessageList(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        setVerticalAdapter(recyclerView, adapter);
        if (recyclerView.getLayoutManager() instanceof LinearLayoutManager)
            ((LinearLayoutManager) recyclerView.getLayoutManager()).setStackFromEnd(true);
    }

    public static void setHorisontalAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        setLinearManager(recyclerView, adapter, LinearLayoutManager.HORIZONTAL);
    }

    private static void setLinearManager(RecyclerView recyclerView, RecyclerView.Adapter adapter, int orientation) {
        LinearLayoutManager manager = new LinearLayoutManager(recyclerView.getContext());
        manager.setOrientation(orientation);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    public static <T extends Comparable<T>> List<UiCheckedItem<T>> createList(List<T> categories){
        List<UiCheckedItem<T>> list = new ArrayList<>();
        for(T c:categories)
            list.add(new UiCheckedItem<>(c));
        return list;
    }
}
