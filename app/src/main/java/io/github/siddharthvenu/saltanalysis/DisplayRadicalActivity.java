package io.github.siddharthvenu.saltanalysis;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.formats.NativeAdView;

import java.util.ArrayList;
import java.util.List;

public class DisplayRadicalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_radical);

        AdView adView = (AdView)findViewById(R.id.adView);
        /*
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.ad_unit_radicalinfo_bottom));*/
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(request);

        String radicalName = getIntent().getStringExtra("radical");
        Log.v("HAHA",radicalName);
        List<Radicals.Radical> radicalList = Radicals.getRadicalDetails();
        Radicals.Radical radical = null;
        for(Radicals.Radical r:radicalList)
            if(radicalName.equals(r.name)){
                radical=r;
            }
        if (radical != null) {
            ActionBar actionBar = getSupportActionBar();
            if(actionBar!=null)
                actionBar.setTitle(Html.fromHtml(radicalName+" - "+radical.formula));
            ArrayList<Experiment> experiments = new ArrayList<>();
            for(int i=0;i<radical.experiment.size();i++){
                experiments.add(i, new Experiment(radical.experiment.get(i), radical.observation.get(i), radical.conclusion.get(i)));
            }
            ListView listView = (ListView) findViewById(R.id.radical_experiments_list);
            listView.setAdapter(new ExperimentAdapter(experiments, this));
        }
    }
}
