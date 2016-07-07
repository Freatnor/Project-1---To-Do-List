package com.example.administrator.project_1___to_do_list;

import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jonathan Taylor on 7/6/16.
 *
 * ToDoList Class that holds a list of ToDoItems. Keeps track of how many items are left to complete
 * and whether the list as a whole is done.
 *
 * Username for if the lists are specific to a user of the app
 */
public class ToDoList{
    private List<ToDoItem> mItems;
    private int mToDoCount;
    private int mCompletedCount;
    private boolean mIsDone;
    private String mName;

    //To use if made for multiple users...
    //String mUsername;

    /**
     * Constructor to load the list from storage
     * @param items
     * @param isDone
     * @param name
     */
    public ToDoList(List<ToDoItem> items, boolean isDone, String name) {
        mItems = items;
        mIsDone = isDone;
        mName = name;


        mToDoCount = 0;
        mCompletedCount = 0;
        if(isDone){
            mCompletedCount = items.size();
        }
        else if(!mItems.isEmpty()){
            processList();
        }
    }

    /**
     * internal method to check the new, non-zero length list for how many are completed
     */
    private void processList(){
        for (int i = 0; i < mItems.size(); i++) {
            if(mItems.get(i).isDone()) { mCompletedCount++; }
            else { mToDoCount++; }
        }
    }

    /**
     * Constructor for new ToDoLists
     * @param name
     */
    public ToDoList(String name) {
        mName = name;
        mToDoCount = 0;
        mCompletedCount = 0;
        mIsDone = false;
        mItems = new ArrayList<>();
    }

    public int getToDoCount() {
        return mToDoCount;
    }

    public void setToDoCount(int toDoCount) {
        mToDoCount = toDoCount;
    }

    public boolean isDone() {
        return mIsDone;
    }

    /**
     * Sets the mIsDone boolean to the value unless there are no ToDoItems. In that case the list
     * cannot be done so mIsDone is false.
     * @param done
     */
    public void setDone(boolean done) {
        if(mItems.isEmpty() && done){
            mIsDone = false;
            return;
        }
        mIsDone = done;
    }

    public int getCompletedCount() {
        return mCompletedCount;
    }

    public void setCompletedCount(int completedCount) {
        mCompletedCount = completedCount;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public ToDoItem getToDoItem(int position){
        return mItems.get(position);
    }

    public void addToDoItem(ToDoItem item){
        mItems.add(item);
    }

    public int size(){
        return mItems.size();
    }

}
