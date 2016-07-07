package com.example.administrator.project_1___to_do_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton to hold the static list of ToDoLists
 * Created by Jonathan Taylor on 7/6/16.
 */
public class ListHolder {
    private static ListHolder ourInstance = new ListHolder();
    private static List<ToDoList> mToDoLists;

    public static ListHolder getInstance() {
        return ourInstance;
    }

    private ListHolder() {
        mToDoLists = new ArrayList<>();
    }

    public ToDoList get(int position){
        return mToDoLists.get(position);
    }

    public void remove(int position){
        mToDoLists.remove(position);
    }

    public void add(ToDoList list){
        mToDoLists.add(list);
    }

    public int size(){
        return mToDoLists.size();
    }

    public boolean isEmpty(){
        return mToDoLists.isEmpty();
    }

    public boolean clear(){
        mToDoLists = new ArrayList<>();
        if(mToDoLists.isEmpty()){
            return true;
        }
        return false;
    }
}
