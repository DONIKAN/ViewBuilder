# ViewBuilder

ViewBuilder for Android is a library for easily building useful views like Tags, Spinner, AutoCompleteTextView, ...

## Screenshots
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521130782.png)
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521131999.png)
![Image](https://github.com/DONIKAN/ViewBuilder/blob/master/screenshots/Screenshot_1521132377.png)

## How to use

```java
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
```