package com.github.donikan.viewbuilder.adapters;

import android.content.Context;
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

    public TagAdapter(List<Entry> entries, OnItemClickListener onItemClickListener) {
        this.entries = entries;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tag, parent, false);
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

            if(entry.isSelected()) {
                itemSelected();
            } else {
                itemUnselected();
            }
        }

        private void itemSelected() {
            tvTag.setTextAppearance(context, R.style.TagSelectedStyle);
            tvTag.setBackgroundResource(R.drawable.bg_tag_selected);
        }

        private void itemUnselected() {
            tvTag.setTextAppearance(context, R.style.TagUnselectedStyle);
            tvTag.setBackgroundResource(R.drawable.bg_tag_unselected);
        }

    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
        notifyDataSetChanged();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        notifyDataSetChanged();
    }
}
