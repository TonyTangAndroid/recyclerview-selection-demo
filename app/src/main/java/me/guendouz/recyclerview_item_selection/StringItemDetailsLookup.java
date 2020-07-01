package me.guendouz.recyclerview_item_selection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 *  An implementation of a {@link ItemDetailsLookup} to be used to get details when a user make a selection of an item.
 */

public class StringItemDetailsLookup extends ItemDetailsLookup<String> {

    private final RecyclerView recyclerView;

    StringItemDetailsLookup(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Nullable
    @Override
    public StringItemDetails getItemDetails(@NonNull MotionEvent e) {
        View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
        if (view != null) {
            RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(view);
            if (holder instanceof ItemViewHolder) {
                return ((ItemViewHolder) holder).getItemDetails();
            }
        }
        return null;
    }
}
