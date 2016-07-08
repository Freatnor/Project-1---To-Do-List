package com.example.administrator.project_1___to_do_list;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

/**
 * Created by Jonathan Taylor on 7/7/16.
 */
public class AddDialog extends AlertDialog {

    //private ToDoList mList;
    //private ListHolder mListHolder;
    //private int mPosition;
    //private RecyclerView.Adapter mAdapter;
    private Context mContext;
    private int mNumEdits;

    private DialogInterface.OnClickListener mPositiveListener;
    private DialogInterface.OnClickListener mNegativeListener;


    protected AddDialog(@NonNull Context context, DialogInterface.OnClickListener positive, int numEdits) {
        super(context);
        //mList = list;
        //mPosition = position;
        mContext = context;
        //mAdapter = adapter;

        mPositiveListener = positive;
    }

    /*
     * For use with Lists
     * @param context
     * @param position
     * @param listHolder

    public DeleteDialog(@NonNull Context context, int position, ListHolder listHolder, RecyclerView.Adapter adapter) {
        super(context);
        //mPosition = position;
        //mListHolder = listHolder;
        mContext = context;
        //mAdapter = adapter;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Build the dialog and set up the button click handlers
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setMessage("Are you sure you want to delete this?")
                .setPositiveButton("Delete", mPositiveListener)
                .setNegativeButton("Cancel", mNegativeListener);
        builder.create();
        builder.show();
    }
}
