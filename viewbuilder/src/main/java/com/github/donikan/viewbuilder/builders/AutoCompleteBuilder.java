package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.github.donikan.viewbuilder.adapters.SpinnerAdapter;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class SpinnerBuilder {

    protected List<Entry> mEntries;

    protected OnItemClickListener mListener;

    protected SpinnerAdapter mAdapter;

    protected Context mContext;
    protected Spinner mSpinner;
    protected int mCustomView;

    public SpinnerBuilder(Context context) {
        mEntries = new ArrayList<>();
        mContext = context;
        mCustomView = android.R.layout.simple_dropdown_item_1line;
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
    }

    public SpinnerBuilder setSpinner(@NonNull Spinner spinner) {
        mSpinner = spinner;
        return this;
    }

    public SpinnerBuilder setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        return this;
    }

    public SpinnerBuilder setCustomView(@LayoutRes int customView) {
        mCustomView = customView;
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public SpinnerBuilder addPlaceholder(String placeholder) {
        if (mEntries.size() > 0 && mEntries.get(0).getId() == -1L) {
            mEntries.remove(0);
        }
        mEntries.add(0, new Entry(-1L, placeholder));
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public SpinnerBuilder removePlaceholder() {
        if (mEntries.get(0).getId() == -1L) {
            mEntries.remove(0);
            mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        }
        return this;
    }

    public SpinnerBuilder setEntries(List<Entry> entries) {
        mEntries.addAll(entries);
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public SpinnerBuilder add(Entry entry) {
        mEntries.add(entry);
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public SpinnerBuilder add(List<Entry> entries) {
        mEntries.addAll(entries);
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public void create() {
        if (mSpinner != null) {
            mSpinner.setAdapter(mAdapter);
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if (mListener != null) {
                        Entry entry = null;
                        if (mAdapter.getItem(position).getId() != -1) entry = mAdapter.getItem(position);
                        mListener.OnItemClick(entry, position);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {

                }

            });
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
        return mAdapter.getItem(mSpinner.getSelectedItemPosition());
    }

    public int getCustomView() {
        return mCustomView;
    }
}
