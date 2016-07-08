package com.example.administrator.project_1___to_do_list;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class DeleteDialog extends AlertDialog {

    //private ToDoList mList;
    //private ListHolder mListHolder;
    //private int mPosition;
    //private RecyclerView.Adapter mAdapter;
    private Context mContext;

    private DialogInterface.OnClickListener mPositiveListener;
    private DialogInterface.OnClickListener mNegativeListener;


    protected DeleteDialog(@NonNull Context context, DialogInterface.OnClickListener positive, DialogInterface.OnClickListener negative) {
        super(context);
        //mList = list;
        //mPosition = position;
        mContext = context;
        //mAdapter = adapter;

        mPositiveListener = positive;
        mNegativeListener = negative;
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

