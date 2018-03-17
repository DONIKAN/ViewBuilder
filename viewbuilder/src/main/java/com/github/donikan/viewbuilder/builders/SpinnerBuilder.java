package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.donikan.viewbuilder.entries.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class SpinnerBuilder {

    protected List<Entry> mEntries;
    protected List<String> mEntriesArray;

    protected AdapterView.OnItemClickListener mListener;

    protected ArrayAdapter<String> mAdapter;

    protected Context mContext;
    protected Spinner mSpinner;
    protected int mCustomView;

    public SpinnerBuilder(Context context) {
        mEntries = new ArrayList<>();
        mEntriesArray = new ArrayList<>();
        mContext = context;
        mCustomView = android.R.layout.simple_dropdown_item_1line;
    }

    public SpinnerBuilder setSpinner(@NonNull Spinner spinner) {
        mSpinner = spinner;
        return this;
    }

    public SpinnerBuilder setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        return this;
    }

    public SpinnerBuilder setCustomView(@LayoutRes int customView) {
        mCustomView = customView;
        return this;
    }

    public SpinnerBuilder setEntries(List<Entry> entries) {
        this.mEntries = entries;
        for (int i = 0; i < mEntries.size(); i++) {
            mEntriesArray.add(mEntries.get(i).getTitle());
            mAdapter.notifyDataSetChanged();
        }
        return this;
    }

    public SpinnerBuilder addPlaceholder(String placeholder) {
        mEntries.add(0, new Entry(-1L, placeholder));
        mEntriesArray.add(0, placeholder);
        mAdapter.notifyDataSetChanged();
        return this;
    }

    public SpinnerBuilder removePlaceholder() {
        if (mEntries.get(0).getId() == -1L) {
            mEntries.remove(0);
            mEntriesArray.remove(0);
            mAdapter.notifyDataSetChanged();
        }
        return this;
    }

    public SpinnerBuilder add(Entry entry) {
//        mAdapter.add(entry);
        return this;
    }

    public SpinnerBuilder add(List<Entry> entries) {
//        mAdapter.add(entries);
        return this;
    }

    public void create() {
        if (mSpinner != null) {
            for (int i = 0; i< mEntriesArray.size(); i++) Log.d("XXXX", mEntriesArray.get(i));
            mAdapter = new ArrayAdapter<String>(mContext, mCustomView, mEntriesArray);
            mSpinner.setAdapter(mAdapter);
            mSpinner.setOnItemClickListener(mListener);
        } else {
            throw new RuntimeException("Spinner cannot be null");
        }
    }

    public Context getContext() {
        return mContext;
    }

    public List<Entry> getEntries() {
        return mEntries;
    }

    public Entry getSelectedEntry() {
        return mEntries.get(mSpinner.getSelectedItemPosition());
    }

    public int getCustomView() {
        return mCustomView;
    }
}
