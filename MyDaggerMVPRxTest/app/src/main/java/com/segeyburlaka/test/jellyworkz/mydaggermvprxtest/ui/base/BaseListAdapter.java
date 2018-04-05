package com.segeyburlaka.test.jellyworkz.mydaggermvprxtest.ui.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseListAdapter<T, V extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<V> {

    private List<T> items = new ArrayList<>();

    protected BaseListAdapter(List<T> searchItemList) {
        this.items = searchItemList;
    }

    public BaseListAdapter() {
        this.items = new ArrayList<>();
    }

    public List<T> getItems() {
        return items;
    }

    @Override
    public int getItemCount() {
        return getItems() == null ? 0 : getItems().size();
    }

    public void clear() {
        items.clear();
    }

    public boolean addAll(@NonNull Collection<? extends T> collection) {
        return items.addAll(collection);
    }
}
