package com.team.noty.event.ui.adapters;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public abstract class BaseAdapter<T extends BaseAdapter.ViewHolder> extends RecyclerView.Adapter<T> {

    public static View inflate(ViewGroup parent, @LayoutRes int res) {
        return LayoutInflater.from(parent.getContext()).inflate(res, parent, false);
    }

    @Override
    @LayoutRes
    public abstract int getItemViewType(int position);

    @Override
    public void onBindViewHolder(T holder, int position) {
        holder.bind(position);
    }

    abstract class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        abstract void bind(int pos);
    }

}
