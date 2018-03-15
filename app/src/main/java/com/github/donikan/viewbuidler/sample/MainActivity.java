package com.github.donikan.viewbuidler.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.github.donikan.viewbuilder.adapters.TagAdapter;
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
        LinearLayoutManager tagLayoutManager = new LinearLayoutManager(MainActivity.this);
        tagLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        RecyclerView rvTags = (RecyclerView) findViewById(R.id.rvTag);
        rvTags.setHasFixedSize(true);
        rvTags.setLayoutManager(tagLayoutManager);


        // fill tag entries
        List<Category> categories = DataFixture.getCategoies();
        List<Entry> tagCategories = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            tagCategories.add(new Entry<Category>(categories.get(i).getId(), categories.get(i).getTitle(), categories.get(i)));
        }

        tagCategories.get(0).setSelected(true);

        TagAdapter tagAdapter = new TagAdapter()
//                .setCustomView(R.layout.custom_item_tag)
//                .setDefaultStyle()
                .setCustomSelectedStyle(R.style.CustomTagSelectedStyle, R.drawable.custom_bg_tag_selected)
                .setCustomUnselectedStyle(R.style.CustomTagUnselectedStyle, R.drawable.custom_bg_tag_unselected)
                .setEntries(tagCategories)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void OnItemClick(Entry entry, int position) {
                        Toast.makeText(MainActivity.this, entry.toString(), Toast.LENGTH_LONG).show();
                    }
                });
        rvTags.setAdapter(tagAdapter);
    }
}
