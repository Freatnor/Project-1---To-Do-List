package com.example.administrator.project_1___to_do_list;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.LinearLayout;

/**
 * Created by Jonathan Taylor on 7/7/16.
 */
public class AddDialog extends AlertDialog {


    //private ToDoList mList;
    //private ListHolder mListHolder;
    //private int mPosition;
    //private RecyclerView.Adapter mAdapter;
    private Context mContext;

    private DialogInterface.OnClickListener mPositiveListener;
    //private DialogInterface.OnClickListener mNegativeListener;

    private AddType mType;

    /**
     * Constructor for add dialog.
     * @param context
     * @param positive
     * @param type AddType.ITEM or AddType.LIST
     */
    protected AddDialog(@NonNull Context context, AddType type, DialogInterface.OnClickListener positive) {
        super(context);
        //mList = list;
        //mPosition = position;
        mContext = context;
        //mAdapter = adapter;

        mPositiveListener = positive;
         if(type == AddType.ITEM || type == AddType.LIST) {
             mType = type;
         }
        else{
             mType = null;
         }

        // Build the dialog and set up the button click handlers
        LinearLayout layout = new LinearLayout(mContext);
        layout.setOrientation(LinearLayout.VERTICAL);

        final EditText titleBox = new EditText(mContext);
        titleBox.setHint("Name");
        titleBox.setId(R.id.add_dialog_title_edit);
        layout.addView(titleBox);




        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        String message = "";
        String title = "Add new ";
        switch(mType){
            case ITEM:
                title += "To-Do Item";
                message = "Please add a Name and Description to the new message";
                final EditText descriptionBox = new EditText(mContext);
                descriptionBox.setHint("Description");
                descriptionBox.setId(R.id.add_dialog_description_edit);
                layout.addView(descriptionBox);
                break;
            case LIST:
                title += "To-Do List";
                message = "Please specify a Name for your new To-Do List";
                break;
            default:
                break;
        }
        builder.setView(layout);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("Add", mPositiveListener)
                .setNegativeButton("Cancel", null);
        builder.create();
        builder.show();
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

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//
//    }
}

;
