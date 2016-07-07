package com.example.administrator.project_1___to_do_list;

import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Adapter for the ListItems Activity and Items Recycler View. Has the onClicks for the checkbox
 * and the row, as well as one for longClicks to delete.
 * Created by Jonathan Taylor on 7/6/16.
 */
public class ToDoListItemsAdapter extends RecyclerView.Adapter<ToDoListItemsViewHolder>  {

    private ToDoList mList;

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

        holder.setCheckBoxOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList.getToDoItem(position).setDone(((CheckBox) view).isChecked());
                holder.convertViewForChecked();

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
    private TextView mItemDescription;
    private CheckBox mCheckBox;

    public ToDoListItemsViewHolder(View itemView) {
        super(itemView);

        mLayout = (RelativeLayout) itemView.findViewById(R.id.item_wrapper_layout);
        mItemName = (TextView) itemView.findViewById(R.id.item_name);
        mItemDescription = (TextView) itemView.findViewById(R.id.item_description);
        mCheckBox = (CheckBox) itemView.findViewById(R.id.item_checkbox);

        convertViewForChecked();
    }

    public void setItemName(String name){
        mItemName.setText(name);
    }

    public String getItemName(){
        return mItemName.getText().toString();
    }

    public void setItemDescription(String name){
        mItemDescription.setText(name);
    }

    public String getItemDescription(){
        return mItemDescription.getText().toString();
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
}