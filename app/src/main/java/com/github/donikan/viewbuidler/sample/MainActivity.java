package com.github.donikan.viewbuidler.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.donikan.viewbuilder.ViewBuidler;
import com.github.donikan.viewbuilder.builders.RadioBuilder;
import com.github.donikan.viewbuilder.builders.SpinnerBuilder;
import com.github.donikan.viewbuilder.builders.TagBuilder;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Category> categories;
    private List<Entry> entries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // fill entries
        categories = DataFixture.getCategoies();
        entries = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            entries.add(new Entry<Category>(categories.get(i).getId(), categories.get(i).getTitle(), categories.get(i)));
            entries.add(new Entry<Category>(categories.get(i).getId(), categories.get(i).getTitle(), categories.get(i)));
        }

        entries.get(0).setSelected(true);

        setUpTag();
        setUpRadio();
        setUpSpinner();
    }

    private void setUpTag() {
        // Tag
        new TagBuilder(MainActivity.this)
                .setRecyclerView((RecyclerView) findViewById(R.id.rvTag1))
                .setLayoutManager(ViewBuidler.LayoutManager.STAGGERED)
                .setOrientation(LinearLayout.HORIZONTAL)
                .setSpanCount(3)
                .setCustomSelectedStyle(R.style.CustomTagSelectedStyle, R.drawable.custom_bg_tag_selected)
                .setCustomUnselectedStyle(R.style.CustomTagUnselectedStyle, R.drawable.custom_bg_tag_unselected)
                .setEntries(entries)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, "Tag 1: " + entry.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create();
    }

    private void setUpRadio() {
        // Radio
        RadioBuilder radioBuilder = new RadioBuilder(MainActivity.this)
                .setRecyclerView((RecyclerView) findViewById(R.id.rvRadio))
                .setLayoutManager(ViewBuidler.LayoutManager.STAGGERED)
                .setOrientation(LinearLayout.HORIZONTAL)
                .setSpanCount(3)
//                .setCustomView(R.layout.custom_item_radio)
                .setEntries(entries)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, "Radio: " + entry.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        radioBuilder.create();
        radioBuilder.add(new Entry<Category>(6L, "Life", new Category(6L, "Life")));
    }

    private void setUpSpinner() {
        SpinnerBuilder spinnerBuilder = new SpinnerBuilder(MainActivity.this)
                .setSpinner((Spinner) findViewById(R.id.spinner))
//                .setCustomView(R.layout.custom_item_spinner)
                .addPlaceholder("Select item ...")
                .setEntries(entries)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        String _entry = "None item selected";
                        if (entry != null) _entry = entry.toString();
                        Toast.makeText(MainActivity.this, "Spinner: " + _entry, Toast.LENGTH_LONG).show();
                    }
                });
        spinnerBuilder.create();
//        spinnerBuilder.removePlaceholder();
//        spinnerBuilder.add(new Entry<Category>(7L, "School", new Category(7L, "School")));
    }
}
