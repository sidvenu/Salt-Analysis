package io.github.siddharthvenu.saltanalysis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;


public class LongProcedureActivity extends AppCompatActivity {

    Radicals.Radical currentAcidRadical, currentBasicRadical;
    List<Radicals.Radical> acidRadicals, basicRadicals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_procedure);
        List<Radicals.Radical> radicals = Radicals.getRadicalDetails();
        List<String> acidNames, basicNames;

        acidRadicals = new ArrayList<>();
        basicRadicals = new ArrayList<>();
        acidNames = new ArrayList<>();
        basicNames = new ArrayList<>();
        for (Radicals.Radical r : radicals) {
            if (r.isAcidic) {
                acidRadicals.add(r);
                acidNames.add(r.name);
                continue;
            }
            basicRadicals.add(r);
            basicNames.add(r.name);
        }
        currentAcidRadical = acidRadicals.get(0);
        //Log.v("HAHA","You selected "+currentAcidRadical.name);
        currentBasicRadical = basicRadicals.get(0);
        //Log.v("HAHA","You selected "+currentBasicRadical.name);

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.acid_choice);
        spinner.setItems(acidNames);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                currentAcidRadical = acidRadicals.get(position);
                //Log.v("HAHA","You selected "+currentAcidRadical.name);
            }
        });
        spinner = (MaterialSpinner) findViewById(R.id.base_choice);
        spinner.setItems(basicNames);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                currentBasicRadical = basicRadicals.get(position);
                //Log.v("HAHA","You selected "+currentBasicRadical.name);
            }
        });
    }

    void updateLongProcedure(){

    }
}
