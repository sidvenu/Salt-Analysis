package io.github.siddharthvenu.saltanalysis;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        setContentView(R.layout.activity_radical_list);
        final ListView listView = (ListView) findViewById(R.id.list_display_radical);
        final List<Radicals.Radical> radicals = Radicals.getRadicalDetails();
        List<String> names = new ArrayList<>(radicals.size());
        boolean isAcidic = getIntent().getBooleanExtra("isAcidic", true);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle(isAcidic?"Acid Radicals":"Basic Radicals");
        }
        for(Radicals.Radical r:radicals){
            if(r.isAcidic==isAcidic)
                names.add(r.name);
        }



        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(itemsAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) listView.getItemAtPosition(i);
                Log.v("HAHA",name);
                startActivity(new Intent(RadicalListActivity.this, DisplayRadicalActivity.class).putExtra("radical",name));
            }
        });
    }
}
