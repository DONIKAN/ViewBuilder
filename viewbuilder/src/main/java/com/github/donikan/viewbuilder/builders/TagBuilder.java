package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.github.donikan.viewbuilder.R;
import com.github.donikan.viewbuilder.adapters.TagAdapter;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class TagBuilder {

    // some internal vars
    // variable to check if a builder is only used once
    protected List<Entry> mEntries;
    protected OnItemClickListener mListener;
    protected TagAdapter mAdapter;

    // the context to use
    protected Context mContext;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected int mCustomView;
//    protected ViewGroup mRootView;

    /*public TagBuilder() {
        mEntries = new ArrayList<>();
    }*/

    public TagBuilder(Context context) {
        mEntries = new ArrayList<>();
        mContext = context;
        mCustomView = R.layout.item_tag;
    }

    public TagBuilder(Context context, List<Entry> entries) {
        this(context);
        mEntries = entries;
    }

    public void create() {

    }

    public TagBuilder setContext(Context context) {
        mContext = context;
        return this;
    }

    public TagBuilder setEntries(List<Entry> entries) {
        mEntries = entries;
        return this;
    }

    public TagBuilder setAdatper(TagAdapter adapter) {
        mAdapter = adapter;
        return this;
    }

    public TagBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        return this;
    }

    public TagBuilder setListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        return this;
    }

    public TagBuilder setCustomView(@IdRes int customView) {
        mCustomView = customView;
        return this;
    }

    public Context getContext() {
        return mContext;
    }

    public List<Entry> getEntries() {
        return mEntries;
    }
}
