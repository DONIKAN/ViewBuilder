package com.github.donikan.viewbuidler.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.donikan.viewbuilder.ViewBuidler;
import com.github.donikan.viewbuilder.builders.AutoCompleteBuilder;
import com.github.donikan.viewbuilder.builders.CheckboxBuilder;
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

    private CheckboxBuilder checkboxBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getEntries();

        setUpTag();
        setUpRadio();
        setUpCheckbox();
        setUpSpinner();
        setUpAutoCompleteTextView();
    }

    private void getEntries() {
        // fill entries
        categories = DataFixture.getCategoies();
        entries = new ArrayList<>();

        for (Category category : categories) {
            entries.add(new Entry<Category>(category.getId(), category.getTitle(), category));
        }

        // select default first item
        entries.get(3).setSelected(true);
    }

    private void setUpTag() {
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
        RadioBuilder radioBuilder = new RadioBuilder(MainActivity.this)
                .setRecyclerView((RecyclerView) findViewById(R.id.rvRadio))
                .setLayoutManager(ViewBuidler.LayoutManager.STAGGERED)
                .setOrientation(LinearLayout.HORIZONTAL)
                .setSpanCount(3)
                .setCustomView(R.layout.custom_item_radio)
                .setEntries(entries)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, "Radio: " + entry.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create();
        radioBuilder.add(new Entry<Category>(6L, "Life", new Category(6L, "Life")));
    }

    private void setUpCheckbox() {
        List<Entry> checkboxEntries = entries;
        for (int i = 0; i < checkboxEntries.size(); i++) {
            if (i % 3 == 0) {
                checkboxEntries.get(i).setSelected(true);
            } else {
                checkboxEntries.get(i).setSelected(false);
            }
        }
        checkboxBuilder = new CheckboxBuilder(MainActivity.this)
                .setRecyclerView((RecyclerView) findViewById(R.id.rvCheckbox))
                .setLayoutManager(ViewBuidler.LayoutManager.LINEAR)
                .setOrientation(LinearLayout.HORIZONTAL)
//                .setSpanCount(3)
//                .setCustomView(R.layout.custom_item_radio)
                .setEntries(checkboxEntries)
                /*.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, "Checkbox: " + entry.toString(), Toast.LENGTH_LONG).show();
                    }
                })*/
                .create();

        Button btnCheckbox = (Button) findViewById(R.id.btnCheckbox);

        btnCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Entry> _entries = checkboxBuilder.getSelectedEntries();

                String entriesValue = "Checkbox entries";

                if (_entries.isEmpty()) entriesValue += "\nEmpty";
                else
                    for (Entry _entry : _entries) {
                        entriesValue += "\n" + _entry.getTitle();
                    }

                Toast.makeText(MainActivity.this, entriesValue, Toast.LENGTH_LONG).show();
            }
        });
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
                })
                .create();

//        spinnerBuilder.removePlaceholder();
//        spinnerBuilder.add(new Entry<Category>(7L, "School", new Category(7L, "School")));
    }

    private void setUpAutoCompleteTextView() {
        AutoCompleteBuilder autoCompleteBuilder = new AutoCompleteBuilder(MainActivity.this)
                .setAutoCompleteTextView((AutoCompleteTextView) findViewById(R.id.actv))
//                .setCustomView(R.layout.custom_item_spinner)
                .setEntries(entries)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, "AutoCompleteTextView: " + entry.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create();
//        spinnerBuilder.add(new Entry<Category>(7L, "School", new Category(7L, "School")));
    }
}
