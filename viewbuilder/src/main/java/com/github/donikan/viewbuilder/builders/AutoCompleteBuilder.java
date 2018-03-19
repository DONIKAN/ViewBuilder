package com.github.donikan.viewbuilder.builders;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import com.github.donikan.viewbuilder.adapters.SpinnerAdapter;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class AutoCompleteBuilder {

    protected List<Entry> mEntries;

    protected OnItemClickListener mListener;

    protected SpinnerAdapter mAdapter;

    protected Context mContext;
    protected AutoCompleteTextView mAutoCompleteTextView;
    protected int mCustomView;

    public AutoCompleteBuilder(Context context) {
        mEntries = new ArrayList<>();
        mContext = context;
        mCustomView = android.R.layout.simple_dropdown_item_1line;
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
    }

    public AutoCompleteBuilder setAutoCompleteTextView(@NonNull AutoCompleteTextView autoCompleteTextView) {
        mAutoCompleteTextView = autoCompleteTextView;
        mAutoCompleteTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!mAutoCompleteTextView.getText().toString().equals(""))
                    if (mAutoCompleteTextView.getText().toString().trim().equals("")) {
                        mAutoCompleteTextView.setText("");
                        //mAutoCompleteTextView.showDropDown();
                    }
                if (hasFocus)
                    mAutoCompleteTextView.showDropDown();
            }
        });

        mAutoCompleteTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!mAutoCompleteTextView.getText().toString().equals(""))
                    if (mAutoCompleteTextView.getText().toString().trim().equals("")) {
                        mAutoCompleteTextView.setText("");
                        //mAutoCompleteTextView.showDropDown();
                    }
                mAutoCompleteTextView.showDropDown();
                return false;
            }
        });
        return this;
    }

    public AutoCompleteBuilder setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mListener = onItemClickListener;
        return this;
    }

    public AutoCompleteBuilder setCustomView(@LayoutRes int customView) {
        mCustomView = customView;
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public AutoCompleteBuilder setEntries(List<Entry> entries) {
        mEntries.addAll(entries);
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public AutoCompleteBuilder add(Entry entry) {
        mEntries.add(entry);
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public AutoCompleteBuilder add(List<Entry> entries) {
        mEntries.addAll(entries);
        mAdapter = new SpinnerAdapter(mContext, mCustomView, mEntries);
        return this;
    }

    public void create() {
        if (mAutoCompleteTextView != null) {
            mAutoCompleteTextView.setAdapter(mAdapter);
            mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    if (mListener != null) {
                        Entry entry = null;
                        mAutoCompleteTextView.setText("");
                        if (mAdapter.getItem(position).getId() != -1) {
                            entry = mAdapter.getItem(position);
                            mAutoCompleteTextView.setText(entry.getTitle());
                        }
                        mAutoCompleteTextView.setSelection(mAutoCompleteTextView.getText().length());
                        mListener.OnItemClick(entry, position);
                    }
                }

            });
        } else {
            throw new RuntimeException("AutoCompleteTextView cannot be null");
        }
    }

    public Context getContext() {
        return mContext;
    }

    public List<Entry> getEntries() {
        return mEntries;
    }

    /*public Entry getSelectedEntry() {
        return mAdapter.getItem(mAutoCompleteTextView.getSelectedItemPosition());
    }*/

    public int getCustomView() {
        return mCustomView;
    }
}
