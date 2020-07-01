package me.guendouz.recyclerview_item_selection;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.recyclerview.selection.SelectionTracker;

/**
 * A basic RecyclerView adapter for strings.
 */

public class StringItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemViewHolder> {


    /**
     * List of strings to be shown
     */
    List<String> list;
    /**
     * The SelectionTracker used by the RecyclerView, used mainly to update item's background color
     */
    private SelectionTracker<String> selectionTracker;

    public StringItemRecyclerViewAdapter(List<String> list) {
        this.list = list;
    }

    public void setSelectionTracker(SelectionTracker<String> tracker) {
        this.selectionTracker = tracker;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new ItemViewHolder(this, view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String item = list.get(position);
        holder.bind(item, selectionTracker.isSelected(item));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).hashCode();
    }

}
