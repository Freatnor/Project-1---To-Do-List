package com.example.administrator.project_1___to_do_list;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ToDoListSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_LIST = "EXTRA_ITEM_LIST";
    public ListHolder mListHolder;
    private LinearLayoutManager mLayoutManager;
    private ToDoListSelectionAdapter mAdapter;
    private RecyclerView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_selection);
        mListHolder = ListHolder.getInstance();

        mListHolder.add(new ToDoList("List 1"));
        mListHolder.add(new ToDoList("List 2"));
        mListHolder.add(new ToDoList("List 3"));
        mListHolder.add(new ToDoList("List 4"));
        mListHolder.add(new ToDoList("List 5"));

        for (int i = 0; i < mListHolder.size(); i++) {
            ToDoList list = mListHolder.get(i);
            list.checkDone();
        }

        mView = (RecyclerView) findViewById(R.id.lists_selection_recycler_view);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mView.setLayoutManager(mLayoutManager);

        mAdapter = new ToDoListSelectionAdapter(this);
        mView.setAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setBackgroundTintList(ContextCompat.getColorStateList(this, R.color.colorPrimary));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add new ListItem to the List
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (int i = 0; i < mListHolder.size(); i++) {
            ToDoList list = mListHolder.get(i);
            list.checkDone();
        }
        mAdapter.notifyDataSetChanged();
    }
}
