package com.github.donikan.viewbuilder.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.StyleRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.donikan.viewbuilder.R;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.List;

/**
 * Created by DONIKAN on 15/03/2018.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ViewHolder> {

    private Context context;
    private List<Entry> entries;
    private OnItemClickListener onItemClickListener;

    private int mView;
    private int mTagSelectedStyle;
    private int mTagSelectedBackground;
    private int mTagUnselectedStyle;
    private int mTagUnselectedBackground;

    public TagAdapter() {
        mView = R.layout.item_tag;
        mTagSelectedStyle = -1;
        mTagSelectedBackground = -1;
        mTagUnselectedStyle = -1;
        mTagUnselectedBackground = -1;
    }

    public TagAdapter(List<Entry> entries, OnItemClickListener onItemClickListener) {
        this();
        this.entries = entries;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(mView, parent, false);
        this.context = parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TagAdapter.ViewHolder holder, int position) {
        final Entry entry = entries.get(position);
        final int pos = position;

        holder.bind(entry);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < getItemCount(); i++) entries.get(i).setSelected(false);
                notifyDataSetChanged();
                entries.get(pos).setSelected(true);
                notifyDataSetChanged();
                onItemClickListener.OnItemClick(entries.get(pos), pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTag;

        ViewHolder(View itemView) {
            super(itemView);

            tvTag = (TextView) itemView.findViewById(R.id.tvTag);
        }

        void bind(final Entry entry) {
            tvTag.setText(entry.getTitle());

            if (entry.isSelected()) {
                itemSelected();
            } else {
                itemUnselected();
            }
        }

        private void itemSelected() {
            if (mTagSelectedStyle != -1) tvTag.setTextAppearance(context, mTagSelectedStyle);
            if (mTagSelectedBackground != -1) tvTag.setBackgroundResource(mTagSelectedBackground);
        }

        private void itemUnselected() {
            if (mTagUnselectedStyle != -1) tvTag.setTextAppearance(context, mTagUnselectedStyle);
            if (mTagUnselectedBackground != -1) tvTag.setBackgroundResource(mTagUnselectedBackground);
        }

    }

    public TagAdapter setDefaultStyle() {
        mView = R.layout.item_tag;
        mTagSelectedStyle = R.style.TagSelectedStyle;
        mTagSelectedBackground = R.drawable.bg_tag_selected;
        mTagUnselectedStyle = R.style.TagUnselectedStyle;
        mTagUnselectedBackground = R.drawable.bg_tag_unselected;
        notifyDataSetChanged();

        return this;
    }

    public TagAdapter setCustomView(@LayoutRes int customView) {
        mView = customView;
        return this;
    }

    public TagAdapter setCustomSelectedStyle(@StyleRes int customSelectedStyle, @DrawableRes int customSelectedBackground) {
        mTagSelectedStyle = customSelectedStyle;
        mTagSelectedBackground = customSelectedBackground;
        return this;
    }

    public TagAdapter setCustomUnselectedStyle(@StyleRes int customUnselectedStyle, @DrawableRes int customUnselectedBackground) {
        mTagUnselectedStyle = customUnselectedStyle;
        mTagUnselectedBackground = customUnselectedBackground;
        return this;
    }

    public TagAdapter setEntries(List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
        return this;
    }

    public TagAdapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
        return this;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }
}
