# ViewBuilder

ViewBuilder for Android is a library for easily building useful views like Tags, Spinner, AutoCompleteTextView, ...

## Screenshots
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521457994.png)

## Setup

Add it to your build.gradle with:
```gradle
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```
and:

```gradle
dependencies {
    compile 'com.github.DONIKAN:ViewBuilder:{latest version}'
}
```

## How to use

1. Initialize entries

```java
List<Category> categories = DataFixture.getCategoies();
List<Entry> entries = new ArrayList<>();

for (Category category : categories) {
    entries.add(new Entry<Category>(category.getId(), category.getTitle(), category));
}

// select default first item
entries.get(0).setSelected(true);
```

2. Build view

```java
new TagBuilder(MainActivity.this)
        .setRecyclerView((RecyclerView) findViewById(R.id.rvTag))
        .setLayoutManager(ViewBuidler.LayoutManager.STAGGERED)
        .setOrientation(LinearLayout.HORIZONTAL)
        .setSpanCount(3)
        .setCustomSelectedStyle(R.style.CustomTagSelectedStyle, R.drawable.custom_bg_tag_selected)
        .setCustomUnselectedStyle(R.style.CustomTagUnselectedStyle, R.drawable.custom_bg_tag_unselected)
        .setEntries(entries)
        .setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(Entry entry, int position) {
                Toast.makeText(MainActivity.this, "Tag : " + entry.toString(), Toast.LENGTH_LONG).show();
            }
        })
        .create();
```