package me.guendouz.recyclerview_item_selection;

import androidx.annotation.Nullable;

import androidx.recyclerview.selection.ItemDetailsLookup;

/**
 * An {@link ItemDetailsLookup.ItemDetails} that holds details about a {@link String} item like its position and its value.
 */

public class StringItemDetails extends ItemDetailsLookup.ItemDetails<String> {

    private int position;
    private String item;

    public StringItemDetails(int position, String item) {
        this.position = position;
        this.item = item;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Nullable
    @Override
    public String getSelectionKey() {
        return item;
    }
}
