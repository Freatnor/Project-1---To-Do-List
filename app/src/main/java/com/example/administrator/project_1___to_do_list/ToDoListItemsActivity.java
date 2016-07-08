package com.example.administrator.project_1___to_do_list;

import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ToDoListItemsActivity extends AppCompatActivity {

    private ToDoList mList;
    private ListHolder mListHolder;

    private LinearLayoutManager mLayoutManager;
    private ToDoListItemsAdapter mAdapter;
    private RecyclerView mView;

    private TextView mListName;
    private EditText mRename;
    private Button mDelete;
    private LinearLayout mLayout;

    private DeleteDialog mDialog;
    private AddDialog mAddDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mListHolder = ListHolder.getInstance();

        Intent intent = getIntent();
        final int position = intent.getIntExtra(ToDoListSelectionActivity.EXTRA_ITEM_LIST, -1);
        mList = mListHolder.get(position);

        /* Test Code

         */
//        mList.addToDoItem(new ToDoItem("Item 1", "Description"));
//        mList.addToDoItem(new ToDoItem("Item 2", "Description"));
//        mList.addToDoItem(new ToDoItem("Item 3", "Description", true));


        mListName = (TextView) findViewById(R.id.item_list_name);
        mRename = (EditText) findViewById(R.id.item_list_rename);
        mDelete = (Button) findViewById(R.id.item_list_delete);
        mLayout = (LinearLayout) findViewById(R.id.items_parent_layout);

        mLayout.setFocusableInTouchMode(true);
        mLayout.setFocusable(true);
        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.requestFocus();
            }
        });

        mListName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListName.setVisibility(View.INVISIBLE);
                mRename.setVisibility(View.VISIBLE);
                mRename.requestFocus();
            }
        });

        mListName.setText(mList.getName());
        mRename.setText(mList.getName());

        mRename.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                EditText e = (EditText) view;
                if(!b){
                    mList.setName(e.getText().toString().trim());
                    mRename.setText(mList.getName());
                    mListName.setText(mList.getName());

                    mRename.setVisibility(View.INVISIBLE);
                    mListName.setVisibility(View.VISIBLE);
                }
            }
        });

        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //spawn dialog
                //start dialog

                mDialog = new DeleteDialog(view.getContext(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //positive
                        mListHolder.remove(position);
                        finish();
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //negative!!!

                    }
                });

                //mDialog.show();

                //Toast.makeText(view.getContext(), "Delet This!", Toast.LENGTH_SHORT).show();
            }

        });

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

                mAddDialog = new AddDialog(view.getContext(), AddType.ITEM, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText title = (EditText) ((Dialog)dialogInterface).findViewById(R.id.add_dialog_title_edit);
                        EditText descr = (EditText) ((Dialog)dialogInterface).findViewById(R.id.add_dialog_description_edit);

                        mList.addToDoItem(new ToDoItem(title.getText().toString().trim(), descr.getText().toString().trim()));
                        mAdapter.notifyItemInserted(mList.size()-1);
                    }
                });

                //mAddDialog.show();
            }
        });
    }

}
