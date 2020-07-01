package me.guendouz.recyclerview_item_selection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
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

  private SelectionTracker<String> mSelectionTracker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btnClearSelection = findViewById(R.id.btnClearSelection);
    btnClearSelection.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if (mSelectionTracker.hasSelection()) {
          mSelectionTracker.clearSelection();
        }
      }
    });

    TextView tvSelectionCount = findViewById(R.id.tvSelectionCount);

    RecyclerView mRecyclerView = findViewById(R.id.itemsRecyclerView);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    StringItemRecyclerViewAdapter mAdapter = new StringItemRecyclerViewAdapter(ITEMS);
    mRecyclerView.setAdapter(mAdapter);

    mSelectionTracker = new SelectionTracker.Builder<>(
        "string-items-selection",
        mRecyclerView,
        new StringItemKeyProvider(1, ITEMS),
        new StringItemDetailsLookup(mRecyclerView),
        StorageStrategy.createStringStorage()
    ).withSelectionPredicate(SelectionPredicates.<String>createSelectAnything())
        .build();

    mAdapter.setSelectionTracker(mSelectionTracker);

    mSelectionTracker.addObserver(new MainSelectionObserver(tvSelectionCount, mSelectionTracker));

    if (savedInstanceState != null) {
      mSelectionTracker.onRestoreInstanceState(savedInstanceState);
    }

  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    super.onSaveInstanceState(outState);
    mSelectionTracker.onSaveInstanceState(outState);
  }

}
