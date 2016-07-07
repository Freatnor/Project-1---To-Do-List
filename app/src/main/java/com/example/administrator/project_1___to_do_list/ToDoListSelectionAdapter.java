package com.example.administrator.project_1___to_do_list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter for the specific Recycler View for ToDoList Selection page. Includes binding listeners
 * for onClick, onLongClick, and onFling for go to list items, rename, and delete
 * Created by Jonathan Taylor on 7/6/16.
 */
public class ToDoListSelectionAdapter extends RecyclerView.Adapter<ToDoListSelectionViewHolder> {

    private ListHolder mListsHolder;
    private ToDoListSelectionActivity mActivity;

    public ToDoListSelectionAdapter(ToDoListSelectionActivity activity) {
        mListsHolder = ListHolder.getInstance();
        mActivity = activity;
    }

    @Override
    public ToDoListSelectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View parentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_to_do_list_selection, parent, false);
        return new ToDoListSelectionViewHolder(parentView);
    }

    @Override
    public void onBindViewHolder(final ToDoListSelectionViewHolder holder, final int position) {
         ToDoList list = mListsHolder.get(position);

        holder.setListName(list.getName());

        if(list.isDone()){
            holder.setDone();
        }
        else{
            holder.setCompletedNumber(list.getCompletedCount());
            holder.setToDoNumber(list.getToDoCount());
        }

        //onClick for going to the list of ListItems for the current row's List
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.getContext(), ToDoListItemsActivity.class);
                intent.putExtra(ToDoListSelectionActivity.EXTRA_ITEM_LIST, position);

                mActivity.startActivity(intent);
            }
        });

        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                //Start Dialog for deletion
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListsHolder.size();
    }
}

/**
 * Inner class view holder only for use with the To Do List Selection Adapter
 */
class ToDoListSelectionViewHolder extends RecyclerView.ViewHolder{

    private TextView mToDoNumber;
    private TextView mCompletedNumber;
    private TextView mListName;
    private LinearLayout mListLayoutWrapper;

    //the divider text view for the two numbers. Here in case the List is totally done
    private TextView mNumberDivider;


    public ToDoListSelectionViewHolder(View itemView) {
        super(itemView);

        mToDoNumber = (TextView) itemView.findViewById(R.id.number_to_do);
        mCompletedNumber = (TextView) itemView.findViewById(R.id.number_completed);
        mListName = (TextView) itemView.findViewById(R.id.selection_list_name);
        mListLayoutWrapper = (LinearLayout) itemView.findViewById(R.id.selection_list_wrapper_layout);
        mNumberDivider = (TextView) itemView.findViewById(R.id.number_divider);
    }

    public void setListName(String name){
        mListName.setText(name);
    }

    public void setToDoNumber(int num){
        resetIfWasDone();
        mToDoNumber.setText(num + " To Do");
    }

    public void setCompletedNumber(int num){
        resetIfWasDone();
        mCompletedNumber.setText(num + " Completed");
    }

    /**
     * sets the List to a done state where the numbers completed and to do are removed and the
     * divider now shows a message of "Done!"
     */
    public void setDone(){
        mToDoNumber.setText("");
        mCompletedNumber.setText("");

        mNumberDivider.setText("Done!");
    }

    /**
     * check if the List was previously set to done and if so resets it to have the divider and
     * 0 values for numbers
     */
    private void resetIfWasDone(){
        if(!mNumberDivider.getText().toString().equals("|")){
            mNumberDivider.setText("|");

            mToDoNumber.setText("0 To Do");
            mCompletedNumber.setText("0 Completed");
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        mListLayoutWrapper.setOnClickListener(listener);
    }

    public void setOnLongClickListener(View.OnLongClickListener listener){

    }

    public void setOnFlingListener(GestureDetector.SimpleOnGestureListener lisener){

    }

    public Context getContext(){
        return mToDoNumber.getContext();
    }
}

