package io.github.siddharthvenu.saltanalysis;

import android.annotation.SuppressLint;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import static io.github.siddharthvenu.saltanalysis.StringUtilities.formatString;

public class DisplayRadicalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_radical);

        final AdView adView = (AdView) findViewById(R.id.adViewDisplayRadical);
        adView.setVisibility(View.GONE);
        /*
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(getString(R.string.ad_unit_radicalinfo_bottom));*/

        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        AdRequest.Builder requestBuilder = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("9DE01AF95F35E03D3796E01505E5FFD4")
                .addTestDevice("FE818379EBC237735090013093B3F9A2");
        requestBuilder.addTestDevice(StringUtilities.md5(android_id).toUpperCase());

        AdRequest request = requestBuilder.build();
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
        Log.v("HAHA", radicalName);
        List<Radicals.Radical> radicalList = Radicals.getRadicalDetails();
        Radicals.Radical radical = null;
        for (Radicals.Radical r : radicalList)
            if (radicalName.equals(r.name)) {
                radical = r;
            }
        if (radical != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setTitle(formatString(radicalName + " - " + radical.formula));
            ArrayList<Experiment> experiments = radical.experiment;
            ListView listView = (ListView) findViewById(R.id.radical_experiments_list);
            listView.setAdapter(new ExperimentAdapter(experiments, this));
        }
    }
}
