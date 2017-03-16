package io.github.siddharthvenu.saltanalysis;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
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

        final AdView adView = (AdView)findViewById(R.id.adView);
        adView.setVisibility(View.GONE);
        /*
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.ad_unit_radicalinfo_bottom));*/
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9DE01AF95F35E03D3796E01505E5FFD4")
                .addTestDevice("FE818379EBC237735090013093B3F9A2")
                .build();
        adView.loadAd(request);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                Log.v("HAHA", "ad failed to load");
                adView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }
        });

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
                actionBar.setTitle(Html.fromHtml(stringChangeHtml(radicalName+" - "+radical.formula)));
            ArrayList<Experiment> experiments = radical.experiment;
            ListView listView = (ListView) findViewById(R.id.radical_experiments_list);
            listView.setAdapter(new ExperimentAdapter(experiments, this));
        }
    }
    String stringChangeHtml(String s){
        return s.replace("\n", "<br />")
                .replace("{{", "<sup><small><small>")
                .replace("}}", "</small></small></sup>")
                .replace("{", "<sub><small><small>")
                .replace("}", "</small></small></sub>");
    }
}
