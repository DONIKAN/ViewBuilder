package com.github.donikan.viewbuilder.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.github.donikan.viewbuilder.R;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class CheckboxAdapter extends RecyclerView.Adapter<CheckboxAdapter.ViewHolder> {
    private Context context;
    private List<Entry> entries;
    private OnItemClickListener onItemClickListener;

    private int mView;

    public CheckboxAdapter() {
        mView = R.layout.item_checkbox;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mView, parent, false);
        this.context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CheckboxAdapter.ViewHolder holder, int position) {
        holder.bind(entries.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (entries != null) return entries.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private CheckedTextView ctvTag;

        ViewHolder(View itemView) {
            super(itemView);

            ctvTag = (CheckedTextView) itemView.findViewById(R.id.ctvTag);
        }

        void bind(final Entry entry, final int position) {
            ctvTag.setText(entry.getTitle());
            if (entry.isSelected()) ctvTag.setChecked(true); else ctvTag.setChecked(false);

            ctvTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ctvTag.isChecked()) {
                        ctvTag.setChecked(false);
                        entry.setSelected(false);
                    } else {
                        ctvTag.setChecked(true);
                        entry.setSelected(true);
                    }

                    notifyDataSetChanged();

                    if (onItemClickListener != null)
                        onItemClickListener.OnItemClick(entry, position);
                }
            });
        }
    }

    public CheckboxAdapter setDefaultStyle() {
        mView = R.layout.item_checkbox;
        notifyDataSetChanged();

        return this;
    }

    public CheckboxAdapter setCustomView(@LayoutRes int customView) {
        mView = customView;
        return this;
    }

    public CheckboxAdapter setEntries(@NonNull List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
        return this;
    }

    public CheckboxAdapter add(@NonNull Entry entry) {
        this.entries.add(entry);
        notifyDataSetChanged();
        return this;
    }

    public CheckboxAdapter add(@NonNull List<Entry> entries) {
        this.entries.addAll(entries);
        notifyDataSetChanged();
        return this;
    }

    public CheckboxAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
        return this;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public int getCustomView() {
        return mView;
    }

    public List<Entry> getSelectedEntries() {
        List<Entry> selectedEntries = new ArrayList<>();

        for (Entry entry: entries) {
            if (entry.isSelected()) selectedEntries.add(entry);
        }

        return selectedEntries;
    }
}
