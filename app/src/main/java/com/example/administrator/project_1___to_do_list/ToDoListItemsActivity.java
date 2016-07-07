package com.example.administrator.project_1___to_do_list;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ToDoListItemsActivity extends AppCompatActivity {

    private ToDoList mList;
    private ListHolder mListHolder;
    private LinearLayoutManager mLayoutManager;
    private ToDoListItemsAdapter mAdapter;
    private RecyclerView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListHolder = ListHolder.getInstance();

        Intent intent = getIntent();
        int position = intent.getIntExtra(ToDoListSelectionActivity.EXTRA_ITEM_LIST, -1);
        mList = mListHolder.get(position);

        /* Test Code

         */
        mList.addToDoItem(new ToDoItem("Item 1", "Description"));
        mList.addToDoItem(new ToDoItem("Item 2", "Description"));
        mList.addToDoItem(new ToDoItem("Item 3", "Description", true));




        mView = (RecyclerView) findViewById(R.id.items_recycler_view);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mView.setLayoutManager(mLayoutManager);

        mAdapter = new ToDoListItemsAdapter(mList);
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

}
