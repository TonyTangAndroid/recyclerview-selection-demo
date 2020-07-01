package me.guendouz.recyclerview_item_selection;

import static androidx.recyclerview.selection.SelectionTracker.SelectionObserver;

import android.annotation.SuppressLint;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.selection.SelectionTracker;
import timber.log.Timber;

@SuppressLint({"DefaultLocale", "SetTextI18n"})
public class MainSelectionObserver extends SelectionObserver<String> {

  private final TextView tvStatus;
  private final SelectionTracker<String> tracker;

  public MainSelectionObserver(TextView tvStatus, SelectionTracker<String> tracker) {
    this.tvStatus = tvStatus;
    this.tracker = tracker;
  }

  @Override
  public void onItemStateChanged(@NonNull String key, boolean selected) {
    super.onItemStateChanged(key, selected);
    Timber.i("ItemStateChanged %s to %b", key, selected);
  }

  @Override
  public void onSelectionRefresh() {
    super.onSelectionRefresh();
    Timber.i("onSelectionRefresh()");
    tvStatus.setText("Selection Count: 0");
  }

  @Override
  public void onSelectionChanged() {
    super.onSelectionChanged();
    Timber.i("onSelectionChanged()");
    if (tracker.hasSelection()) {
      tvStatus.setText(String.format("Selection Count: %d", tracker.getSelection().size()));
    } else {
      tvStatus.setText("Selection Count: 0");
    }
  }

  @Override
  public void onSelectionRestored() {
    super.onSelectionRestored();
    Timber.i("onSelectionRestored()");
    tvStatus.setText("Selection Count: 0");
  }
}
