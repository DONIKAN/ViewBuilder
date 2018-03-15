package com.github.donikan.viewbuidler.sample;

import com.github.donikan.viewbuilder.entries.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class DataFixture {
    public static final List<Category> getCategoies() {
        List<Category> categories = new ArrayList<Category>();
        categories.add(new Category(1L, "World"));
        categories.add(new Category(2L, "Sport"));
        categories.add(new Category(3L, "Business"));
        categories.add(new Category(4L, "Science"));
        categories.add(new Category(5L, "Health"));

        return categories;
    }

    /*public List<Entry> getTagCategories() {
        List<Entry> entries = new ArrayList<Entry>(
                new Entry<Category>()
        );
        return entries;
    }*/
}
