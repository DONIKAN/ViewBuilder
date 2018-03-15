# ViewBuilder

ViewBuilder for Android is a library for easily building useful views like Tags, Spinner, AutoCompleteTextView, ...

## Screenshots
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521130782.png)
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521131999.png)
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521132377.png)

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

```java
// fill tag entries
List<Category> categories = DataFixture.getCategoies();
List<Entry> tagCategories = new ArrayList<>();

for (int i = 0; i < categories.size(); i++) {
    tagCategories.add(new Entry<Category>(categories.get(i).getId(), categories.get(i).getTitle(), categories.get(i)));
}

tagCategories.get(0).setSelected(true);

// Tag
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
```