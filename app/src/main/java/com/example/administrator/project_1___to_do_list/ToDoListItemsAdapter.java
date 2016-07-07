package com.example.administrator.project_1___to_do_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

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
        return null;
    }

    @Override
    public void onBindViewHolder(ToDoListItemsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

class ToDoListItemsViewHolder extends RecyclerView.ViewHolder{

    public ToDoListItemsViewHolder(View itemView) {
        super(itemView);
    }
}