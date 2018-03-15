package com.github.donikan.viewbuidler.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.github.donikan.viewbuilder.builders.TagBuilder;
import com.github.donikan.viewbuilder.entries.Entry;
import com.github.donikan.viewbuilder.listeners.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpTag();
    }

    private void setUpTag() {
        // fill tag entries
        List<Category> categories = DataFixture.getCategoies();
        List<Entry> tagCategories = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            tagCategories.add(new Entry<Category>(categories.get(i).getId(), categories.get(i).getTitle(), categories.get(i)));
        }

        tagCategories.get(0).setSelected(true);

        new TagBuilder(MainActivity.this)
                .setRecyclerView((RecyclerView) findViewById(R.id.rvTag1))
//                .setOrientation(LinearLayout.VERTICAL)
                .setCustomSelectedStyle(R.style.CustomTagSelectedStyle, R.drawable.custom_bg_tag_selected)
                .setCustomUnselectedStyle(R.style.CustomTagUnselectedStyle, R.drawable.custom_bg_tag_unselected)
                .setEntries(tagCategories)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, entry.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create();


        GridLayoutManager tagLayoutManager = new GridLayoutManager(MainActivity.this, 3);
        tagLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        new TagBuilder(MainActivity.this)
                .setRecyclerView((RecyclerView) findViewById(R.id.rvTag2))
                .setLayoutManager(tagLayoutManager)
                .setDefaultStyle()
                .setSelectable(false)
                .setEntries(tagCategories)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, entry.toString(), Toast.LENGTH_LONG).show();
                    }
                })
                .create();
    }
}
