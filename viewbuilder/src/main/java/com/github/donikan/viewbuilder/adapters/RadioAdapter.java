package com.github.donikan.viewbuilder.adapters;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.github.donikan.viewbuilder.R;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class RadioAdapter extends RecyclerView.Adapter<RadioAdapter.ViewHolder> {
    private Entry mSelectedEntry;
    private int mSelectedItem = -1;

    private Context context;
    private List<Entry> entries;
    private OnItemClickListener onItemClickListener;

    private int mView;

    public RadioAdapter() {
        mView = R.layout.item_radio;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mView, parent, false);
        this.context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RadioAdapter.ViewHolder holder, int position) {
        holder.bind(entries.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (entries != null) return entries.size();
        else return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private RadioButton rbTag;

        ViewHolder(View itemView) {
            super(itemView);

            rbTag = (RadioButton) itemView.findViewById(R.id.rbTag);
        }

        void bind(final Entry entry, final int position) {
            rbTag.setText(entry.getTitle());
            if (position == mSelectedItem || entry.isSelected()) rbTag.setChecked(true); else rbTag.setChecked(false);

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mSelectedItem = position;
                    notifyItemRangeChanged(0, getItemCount());
                    for (int i = 0; i < getItemCount(); i++) entries.get(i).setSelected(false);
                    notifyDataSetChanged();
                    mSelectedEntry = entries.get(mSelectedItem);
                    mSelectedEntry.setSelected(true);
                    notifyDataSetChanged();
                    if (onItemClickListener != null)
                        onItemClickListener.OnItemClick(mSelectedEntry, mSelectedItem);
                }
            };

            itemView.setOnClickListener(clickListener);
            rbTag.setOnClickListener(clickListener);
        }

    }

    public RadioAdapter setDefaultStyle() {
        mView = R.layout.item_tag;
        notifyDataSetChanged();

        return this;
    }

    public RadioAdapter setCustomView(@LayoutRes int customView) {
        mView = customView;
        return this;
    }

    public RadioAdapter setEntries(@NonNull List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
        return this;
    }

    public RadioAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
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

    public Entry getSelectedEntry() {
        return mSelectedEntry;
    }
}
