package io.github.siddharthvenu.saltanalysis;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class RadicalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //GlassActionBar.verbose=true;
        int actionBarBgResID;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            actionBarBgResID = getColor(R.color.colorPrimary);
        } else {
            //noinspection deprecation
            actionBarBgResID = getResources().getColor(R.color.colorPrimary);
        }
        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(new ColorDrawable(actionBarBgResID))
                .headerLayout(R.layout.header_list_item)
                .contentLayout(R.layout.activity_radical_list);

        setContentView(helper.createView(this));
        helper.initActionBar(this);
        final List<Radicals.Radical> radicals = Radicals.getRadicalDetails();
        List<String> names = new ArrayList<>(radicals.size());
        boolean isAcidic = getIntent().getBooleanExtra("isAcidic", true);
        for (Radicals.Radical r : radicals) {
            if (r.isAcidic == isAcidic)
                names.add(r.name);
        }

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);

        //GlassActionBarHelper helper = new GlassActionBarHelper().contentLayout(R.layout.activity_radical_list);
        //setContentView(helper.createView(this));

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(isAcidic ? "Acid Radicals" : "Basic Radicals");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        final ListView listView = findViewById(android.R.id.list);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) listView.getItemAtPosition(i);
                Log.v("HAHA", name);
                startActivity(new Intent(RadicalListActivity.this, DisplayRadicalActivity.class).putExtra("radical", name));
            }
        });

        /* inflate custom header and attach it to the list
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_list_item, listView, false);
        listView.addHeaderView(header, null, false);

         we take the background image and button reference from the header
        backgroundImage = (ImageView) header.findViewById(R.id.listHeaderImage);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Rect rect = new Rect();
                backgroundImage.getLocalVisibleRect(rect);
                Log.v("HAHA", "Top value:" + String.valueOf(rect.top));
                if (lastTopValue != rect.top) {
                    lastTopValue = rect.top;

                    backgroundImage.setY((float) (rect.top / 2.0));
                }
            }
        });
        */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
