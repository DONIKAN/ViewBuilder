package com.github.donikan.viewbuilder.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.donikan.viewbuilder.entries.Entry;

import java.util.List;

/**
 * Created by DONIKAN on 19/03/2018.
 */

public class SpinnerAdapter extends ArrayAdapter<Entry> {

    private List<Entry> entries;

    public SpinnerAdapter(Context context, int textViewResourceId, List<Entry> entries) {
        super(context, textViewResourceId, entries);
        this.entries = entries;
    }

    @Override
    public int getCount() {
        return entries.size();
    }

    @Override
    public Entry getItem(int position) {
        return entries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return entries.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setText(entries.get(position).getTitle());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);
        tv.setText(entries.get(position).getTitle());
        return tv;
    }
}
