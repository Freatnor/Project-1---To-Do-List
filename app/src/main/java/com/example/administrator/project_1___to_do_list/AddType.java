package com.example.administrator.project_1___to_do_list;

public enum AddType{
    ITEM(1), LIST(0);
    private int value;

    private AddType(int value){
        this.value = value;
    }
}
