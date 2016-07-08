package com.example.administrator.project_1___to_do_list;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Adapter for the ListItems Activity and Items Recycler View. Has the onClicks for the checkbox
 * and the row, as well as one for longClicks to delete.
 * Created by Jonathan Taylor on 7/6/16.
 */
public class ToDoListItemsAdapter extends RecyclerView.Adapter<ToDoListItemsViewHolder>  {

    private ToDoList mList;
    private DeleteDialog mDialog;

    public ToDoListItemsAdapter(ToDoList list) {
        mList = list;
    }

    @Override
    public ToDoListItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.inner_content_to_do_list_items, parent, false);
        return new ToDoListItemsViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(final ToDoListItemsViewHolder holder, final int position) {
        ToDoItem item = mList.getToDoItem(position);


        holder.setCheckBox(item.isDone());
        holder.setItemDescription(item.getDescription());
        holder.setItemName(item.getName());
        holder.setEditName(item.getName());
        holder.setEditDescription(item.getName());

        holder.setCheckBoxOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.getToDoItem(position).setDone(((CheckBox) view).isChecked());
                holder.convertViewForChecked();
                mList.updateToDoCount(((CheckBox) view).isChecked());

            }
        });

        //Set Layout onClick listener to take focus from EditTexts
        holder.setLayoutOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.requestFocus();
            }
        });

        //EditName's onFocus and onClick listeners:
        holder.setNameOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleEditName(true);
            }
        });

        holder.setNameFocusListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String newName = holder.toggleEditName(false);
                    mList.getToDoItem(position).setName(newName);
                }
            }
        });

        //EditDescription's onFocus and onClick listeners
        holder.setDescriptionOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.toggleEditDescription(true);
            }
        });

        holder.setDescriptionFocusListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!b){
                    String newDescription = holder.toggleEditDescription(false);
                    mList.getToDoItem(position).setDescription(newDescription);
                }
            }
        });

        //OnLongClick for deleting the current Item
        holder.setLayoutOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //start dialog

                mDialog = new DeleteDialog(view.getContext(), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //positive
                        mList.removeToDoItem(position);
                        notifyItemRemoved(position);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //negative!!!

                    }
                });

                mDialog.show();

                Toast.makeText(view.getContext(), "Delet This!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });



    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class ToDoListItemsViewHolder extends RecyclerView.ViewHolder{

    private RelativeLayout mLayout;

    private TextView mItemName;
    private EditText mEditName;

    private TextView mItemDescription;
    private EditText mEditDescription;
    private CheckBox mCheckBox;


    public ToDoListItemsViewHolder(View itemView) {
        super(itemView);

        mLayout = (RelativeLayout) itemView.findViewById(R.id.item_wrapper_layout);
        mItemName = (TextView) itemView.findViewById(R.id.item_name);
        mItemDescription = (TextView) itemView.findViewById(R.id.item_description);
        mCheckBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);

        mEditDescription = (EditText) itemView.findViewById(R.id.item_edit_description);
        mEditName = (EditText) itemView.findViewById(R.id.item_edit_name);

        convertViewForChecked();
    }

    public void setItemName(String name){
        mItemName.setText(name);
    }

    public void setEditName(String name){
        mEditName.setText(name);
    }

    public String getItemName(){
        return mItemName.getText().toString();
    }

    /**
     * Method to change the visibility of the edit and text views for Item Name. Returns null if the
     * EditText is toggled on, returns the value of EditText when it hides it.
     * @param visible
     * @return
     */
    public String toggleEditName(boolean visible){
        if(visible){
            mItemName.setVisibility(View.INVISIBLE);
            mEditName.setVisibility(View.VISIBLE);
            mEditName.requestFocus();
            return null;
        }
        else{
            String newName = mEditName.getText().toString();

            mEditName.setText(newName);
            mItemName.setText(newName);

            mEditName.setVisibility(View.INVISIBLE);
            mItemName.setVisibility(View.VISIBLE);

            return newName;
        }
    }

    public void setItemDescription(String name){
        mItemDescription.setText(name);
    }

    public void setEditDescription(String name){
        mEditDescription.setText(name);
    }

    public String getItemDescription(){
        return mItemDescription.getText().toString();
    }
    
    public String toggleEditDescription(boolean visible){
        if(visible){
            mItemDescription.setVisibility(View.INVISIBLE);
            mEditDescription.setVisibility(View.VISIBLE);
            mEditDescription.requestFocus();
            return null;
        }
        else{
            String newDescription = mEditDescription.getText().toString();

            mEditDescription.setText(newDescription);
            mItemDescription.setText(newDescription);

            mEditDescription.setVisibility(View.INVISIBLE);
            mItemDescription.setVisibility(View.VISIBLE);

            return newDescription;
        }
    }

    public boolean isDone(){
        return mCheckBox.isChecked();
    }

    public void setCheckBox(boolean checked){
        mCheckBox.setChecked(checked);
        convertViewForChecked();
    }

    //changes the color and text of a list item depending if it is completed or not
    public void convertViewForChecked(){
        if(!isDone()){
            mLayout.setBackgroundColor(ContextCompat.getColor(mLayout.getContext(), android.R.color.white));
            //mItemName.setPaintFlags(mItemName.getPaintFlags() | Paint.LINEAR_TEXT_FLAG);
            mItemName.setPaintFlags(0);

            return;
        }
        //need better color
        mLayout.setBackgroundColor(ContextCompat.getColor(mLayout.getContext(), R.color.colorPrimaryDark));
        mItemName.setPaintFlags(mItemName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public void setCheckBoxOnClickListener(View.OnClickListener listener){
        mCheckBox.setOnClickListener(listener);
    }

    public void setLayoutOnClickListener(View.OnClickListener listener){
        mLayout.setOnClickListener(listener);
    }

    public void setLayoutOnLongClickListener(View.OnLongClickListener listener){
        mLayout.setOnLongClickListener(listener);
    }

    public void setNameFocusListener(View.OnFocusChangeListener listener){
        mEditName.setOnFocusChangeListener(listener);
    }

    public void setDescriptionFocusListener(View.OnFocusChangeListener listener){
        mEditDescription.setOnFocusChangeListener(listener);
    }

    public void setNameOnClickListener(View.OnClickListener listener){
        mItemName.setOnClickListener(listener);
    }

    public void setDescriptionOnClickListener(View.OnClickListener listener){
        mEditDescription.setOnClickListener(listener);
    }
}