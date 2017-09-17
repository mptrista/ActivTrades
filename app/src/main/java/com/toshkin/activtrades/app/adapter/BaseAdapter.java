package com.toshkin.activtrades.app.adapter;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class BaseAdapter<ItemType, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private List<ItemType> items;

    public BaseAdapter() {
        this.items = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ItemType getItem(int position) {
        return items.get(position);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void deleteItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    public void addItems(Collection<ItemType> newItems) {
        if (newItems == null) {
            throw new IllegalArgumentException("Null collection provided.");
        } else if (!newItems.isEmpty()) {
            int startPosition = getItemCount();
            items.addAll(newItems);
            notifyItemChanged(startPosition, newItems.size());
        }
    }

    public void addItemFirst(ItemType item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item provided.");
        } else {
            int startPosition = 0;
            items.add(startPosition, item);
            notifyItemRangeInserted(startPosition, 1);
        }
    }

    public void addItemLast(ItemType item) {
        if (item == null) {
            throw new IllegalArgumentException("Null item provided.");
        } else {
            int startPosition = getItemCount();
            items.add(item);
            notifyItemRangeInserted(startPosition, 1);
        }
    }

    public void clearItems() {
        int count = getItemCount();
        if (count > 0) {
            items.clear();
            notifyItemRangeRemoved(0, count);
        }
    }

    public void swapItem(int position, ItemType replacement) {
        items.set(position, replacement);
        notifyItemChanged(position, null);
    }

    protected final void setItems(List<ItemType> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    protected final List<ItemType> getItems() {
        return items;
    }
}
