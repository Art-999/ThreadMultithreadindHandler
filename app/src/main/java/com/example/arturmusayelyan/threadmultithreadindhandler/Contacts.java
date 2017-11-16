package com.example.arturmusayelyan.threadmultithreadindhandler;

import java.util.List;

/**
 * Created by artur.musayelyan on 15/11/2017.
 */

public class Contacts {
    private String id;
    private String name;
    private int count;
    private List<Object> childrenCatsList;

    public Contacts() {

    }

    public Contacts(String id, String name, int count) {
        this.id = id;
        this.name = name;
        this.count = count;
    }

    public Contacts(String id, String name, int count, List<Object> childrenCatsList) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.childrenCatsList = childrenCatsList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Object> getChildrenCatsList() {
        return childrenCatsList;
    }

    public void setChildrenCatsList(List<Object> list) {
        this.childrenCatsList = list;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
