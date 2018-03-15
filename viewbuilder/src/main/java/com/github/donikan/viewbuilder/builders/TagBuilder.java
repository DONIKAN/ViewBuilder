package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    protected List<Entry> mEntries;
    protected OnItemClickListener mListener;

    protected TagAdapter mAdapter;

    protected Context mContext;
    protected RecyclerView mRecyclerview;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected int mCustomView;

    public TagBuilder(Context context) {
        mEntries = new ArrayList<>();
        mContext = context;
        mAdapter = new TagAdapter();
        mCustomView = R.layout.item_tag;
        mLayoutManager = new LinearLayoutManager(mContext);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
    }

    public TagBuilder(Context context, List<Entry> entries) {
        this(context);
        mEntries = entries;
    }

    public TagBuilder setOrientation(int orientation) {
        ((LinearLayoutManager) mLayoutManager).setOrientation(orientation);
        return this;
    }

    public TagBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        return this;
    }

    public TagBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
        mRecyclerview = recyclerView;
        return this;
    }

    public TagBuilder setListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        return this;
    }

    public TagBuilder setDefaultStyle() {
        mAdapter.setDefaultStyle();
        return this;
    }

    public TagBuilder setCustomView(@LayoutRes int customView) {
        mAdapter.setCustomView(customView);
        return this;
    }

    public TagBuilder setCustomSelectedStyle(@StyleRes int customSelectedStyle, @DrawableRes int customSelectedBackground) {
        mAdapter.setCustomSelectedStyle(customSelectedStyle, customSelectedBackground);
        return this;
    }

    public TagBuilder setCustomUnselectedStyle(@StyleRes int customUnselectedStyle, @DrawableRes int customUnselectedBackground) {
        mAdapter.setCustomUnselectedStyle(customUnselectedStyle, customUnselectedBackground);
        return this;
    }

    public TagBuilder setSelectable(boolean selectable) {
        mAdapter.setSelectable(selectable);
        return this;
    }

    public TagBuilder setEntries(List<Entry> entries) {
        mAdapter.setEntries(entries);
        return this;
    }

    public TagBuilder add(Entry entry) {
        mAdapter.add(entry);
        return this;
    }

    public TagBuilder add(List<Entry> entries) {
        mAdapter.add(entries);
        return this;
    }

    public TagBuilder setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mAdapter.setOnItemClickListener(onItemClickListener);
        return this;
    }

    public void create() {
        if (mRecyclerview != null) {
            mRecyclerview.setHasFixedSize(true);
            mRecyclerview.setLayoutManager(mLayoutManager);
            mRecyclerview.setAdapter(mAdapter);
        } else {
            throw new RuntimeException("RecyclerView cannot be null");
        }
    }

    public Context getContext() {
        return mContext;
    }

    public List<Entry> getEntries() {
        return mAdapter.getEntries();
    }

    public Entry getSelectedEntry() {
        return mAdapter.getSelectedEntry();
    }

    public boolean isSelectable() {
        return mAdapter.isSelectable();
    }

    public int getCustomView() {
        return mAdapter.getCustomView();
    }

    public int getTagSelectedStyle() {
        return mAdapter.getTagSelectedStyle();
    }

    public int getTagSelectedBackground() {
        return mAdapter.getTagSelectedBackground();
    }

    public int getTagUnselectedStyle() {
        return mAdapter.getTagUnselectedStyle();
    }

    public int getTagUnselectedBackground() {
        return mAdapter.getTagUnselectedBackground();
    }
}
