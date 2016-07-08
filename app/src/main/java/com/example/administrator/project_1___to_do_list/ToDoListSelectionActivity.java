package com.example.administrator.project_1___to_do_list;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

public class ToDoListSelectionActivity extends AppCompatActivity {

    public static final String EXTRA_ITEM_LIST = "EXTRA_ITEM_LIST";
    public ListHolder mListHolder;
    private LinearLayoutManager mLayoutManager;
    private ToDoListSelectionAdapter mAdapter;
    private RecyclerView mView;
    private AddDialog mAddDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_selection);
        mListHolder = ListHolder.getInstance();

//        mListHolder.add(new ToDoList("List 1"));
//        mListHolder.add(new ToDoList("List 2"));
//        mListHolder.add(new ToDoList("List 3"));
//        mListHolder.add(new ToDoList("List 4"));
//        mListHolder.add(new ToDoList("List 5"));

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
                mAddDialog = new AddDialog(view.getContext(), AddType.LIST, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText title = (EditText) ((Dialog)dialogInterface).findViewById(R.id.add_dialog_title_edit);

                        mListHolder.add(new ToDoList(title.getText().toString().trim()));
                        mAdapter.notifyItemInserted(mListHolder.size()-1);

                    }
                });

                //mAddDialog.show();

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
