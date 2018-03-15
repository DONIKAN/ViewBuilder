package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.donikan.viewbuilder.R;
import com.github.donikan.viewbuilder.adapters.RadioAdapter;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class RadioBuilder {

    protected List<Entry> mEntries;
    protected OnItemClickListener mListener;

    protected RadioAdapter mAdapter;

    protected Context mContext;
    protected RecyclerView mRecyclerview;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected int mCustomView;

    public RadioBuilder(Context context) {
        mEntries = new ArrayList<>();
        mContext = context;
        mAdapter = new RadioAdapter();
        mCustomView = R.layout.item_radio;
        mLayoutManager = new LinearLayoutManager(mContext);
        ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
    }

    public RadioBuilder(Context context, List<Entry> entries) {
        this(context);
        mEntries = entries;
    }

    public RadioBuilder setOrientation(int orientation) {
        ((LinearLayoutManager) mLayoutManager).setOrientation(orientation);
        return this;
    }

    public RadioBuilder setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        return this;
    }

    public RadioBuilder setRecyclerView(@NonNull RecyclerView recyclerView) {
        mRecyclerview = recyclerView;
        return this;
    }

    public RadioBuilder setListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        return this;
    }

    public RadioBuilder setDefaultStyle() {
        mAdapter.setDefaultStyle();
        return this;
    }

    public RadioBuilder setCustomView(@LayoutRes int customView) {
        mAdapter.setCustomView(customView);
        return this;
    }

    public RadioBuilder setEntries(List<Entry> entries) {
        mAdapter.setEntries(entries);
        return this;
    }

    public RadioBuilder setOnItemClickListener(OnItemClickListener onItemClickListener) {
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

    public int getCustomView() {
        return mAdapter.getCustomView();
    }
}
