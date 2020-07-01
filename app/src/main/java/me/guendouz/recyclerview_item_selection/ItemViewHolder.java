package me.guendouz.recyclerview_item_selection;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class ItemViewHolder extends RecyclerView.ViewHolder {

  private final StringItemRecyclerViewAdapter adapter;
  private TextView textView;

  ItemViewHolder(StringItemRecyclerViewAdapter adapter, @NonNull View itemView) {
    super(itemView);
    this.adapter = adapter;
    textView = (TextView) itemView;
  }

  void bind(String item, boolean isSelected) {
    textView.setText(item);
    // If the item is selected then we change its state to activated
    textView.setActivated(isSelected);
  }

  /**
   * Create a new {@link StringItemDetails} for each string item, will be used later by {@link
   * StringItemDetailsLookup#getItemDetails(MotionEvent)}
   *
   * @return {@link StringItemDetails} instance
   */
  StringItemDetails getItemDetails() {
    return new StringItemDetails(getAdapterPosition(), itemKey());
  }

  private String itemKey() {
    return adapter.list.get(getAdapterPosition());
  }
}
