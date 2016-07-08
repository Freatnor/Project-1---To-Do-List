package com.example.administrator.project_1___to_do_list;

/**
 * Created by Jonathan Taylor on 7/6/16.
 *
 * ToDoItem class that holds the name and description of an item in a ToDoList. Also keeps track of
 * whether it is done or not.
 */
public class ToDoItem {
    private String mName;
    private String mDescription;
    private boolean mIsDone;

    public ToDoItem(String name, String description) {
        mName = name;
        mDescription = description;
        mIsDone = false;
    }

    /**
     * Constructor if loading from storage
     *
     * @param name
     * @param description
     * @param isDone
     */
    public ToDoItem(String name, String description, boolean isDone) {
        mName = name;
        mDescription = description;
        mIsDone = isDone;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isDone() {
        return mIsDone;
    }

    public void setDone(boolean done) {
        mIsDone = done;
    }
}
