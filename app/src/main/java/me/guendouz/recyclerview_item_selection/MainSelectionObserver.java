package me.guendouz.recyclerview_item_selection;

import static androidx.recyclerview.selection.SelectionTracker.SelectionObserver;

import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;
import timber.log.Timber;

public class MainSelectionObserver extends SelectionObserver {

  private final TextView tvSelectionCount;
  private final SelectionTracker mSelectionTracker;

  public MainSelectionObserver(TextView tvSelectionCount,
      SelectionTracker mSelectionTracker) {
    this.tvSelectionCount = tvSelectionCount;
    this.mSelectionTracker = mSelectionTracker;
  }

  @Override
  public void onItemStateChanged(@NonNull Object key, boolean selected) {
    super.onItemStateChanged(key, selected);
    Timber.i("ItemStateChanged %s to %b", key, selected);
  }

  @Override
  public void onSelectionRefresh() {
    super.onSelectionRefresh();
    Timber.i("onSelectionRefresh()");
    tvSelectionCount.setText("Selection Count: 0");
  }

  @Override
  public void onSelectionChanged() {
    super.onSelectionChanged();
    Timber.i("onSelectionChanged()");

    if (mSelectionTracker.hasSelection()) {
      tvSelectionCount.setText(String
          .format("Selection Count: %d", mSelectionTracker.getSelection().size()));
    } else {
      tvSelectionCount.setText("Selection Count: 0");
    }
  }

  @Override
  public void onSelectionRestored() {
    super.onSelectionRestored();
    Timber.i("onSelectionRestored()");
    tvSelectionCount.setText("Selection Count: 0");
  }
}
