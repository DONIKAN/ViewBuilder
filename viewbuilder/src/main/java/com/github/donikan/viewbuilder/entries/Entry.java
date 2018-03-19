package com.github.donikan.viewbuilder.entries;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class Entry<T> {
    private Long id;
    private String title;
    private boolean selected;
    private T data;

    public Entry(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Entry(Long id, String title, Boolean selected) {
        this.id = id;
        this.title = title;
        this.selected = selected;
    }

    public Entry(Long id, String title, T data) {
        this.id = id;
        this.title = title;
        this.data = data;
    }

    public Entry(Long id, String title, Boolean selected, T data) {
        this.id = id;
        this.title = title;
        this.selected = selected;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String entry = "Entry {" +
                "id = " + id +
                ", title = '" + title + '\'' +
                ", selected = " + selected;
        if (data != null) entry += ", data = {" + data.toString() + '}';

        return entry += '}';
    }
}
