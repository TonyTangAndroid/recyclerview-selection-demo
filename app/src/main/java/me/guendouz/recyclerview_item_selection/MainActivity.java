package me.guendouz.recyclerview_item_selection;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.selection.SelectionPredicates;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public static final List<String> ITEMS = Arrays.asList(
      "A",
      "B",
      "C",
      "D",
      "E",
      "F",
      "G",
      "H",
      "I",
      "J",
      "K",
      "L"
  );

  private SelectionTracker<String> tracker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btnClearSelection = findViewById(R.id.btnClearSelection);
    btnClearSelection.setOnClickListener(view -> {
      if (tracker.hasSelection()) {
        tracker.clearSelection();
      }
    });

    TextView tvSelectionCount = findViewById(R.id.tvSelectionCount);

    RecyclerView recyclerView = findViewById(R.id.itemsRecyclerView);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    StringItemRecyclerViewAdapter adapter = new StringItemRecyclerViewAdapter(ITEMS);
    recyclerView.setAdapter(adapter);

    tracker = new SelectionTracker.Builder<>(
        "string-items-selection",
        recyclerView,
        new StringItemKeyProvider(1, ITEMS),
        new StringItemDetailsLookup(recyclerView),
        StorageStrategy.createStringStorage()
    ).withSelectionPredicate(SelectionPredicates.createSelectAnything()).build();

    adapter.setSelectionTracker(tracker);
    tracker.addObserver(new MainSelectionObserver(tvSelectionCount, tracker));
    tracker.select("B");
  }

}
