package io.github.siddharthvenu.saltanalysis;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static io.github.siddharthvenu.saltanalysis.ProjectUtilities.formatString;

public class DisplayRadicalActivity extends AppCompatActivity {

    String youtubeDevKey = "AIzaSyB2ZJX8x0INR8M0HsYIxZlGoLWEZ5haKSI", radicalName;
    Map<String, String> v;
    YouTubePlayerFragment player;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.orientation == ORIENTATION_LANDSCAPE) {
            View wrapper = findViewById(R.id.youtube_player_wrapper);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            //noinspection SuspiciousNameCombination
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(metrics.heightPixels, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER_HORIZONTAL;
            wrapper.setLayoutParams(params);
        }
        super.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new HashMap<>();
        {
            v.put("Chloride", "O3d_NKyhIwg");
            v.put("Nitrate", "fXo39WiDNhg");
            v.put("Sulphate", "-YVH0DXbqmo");
            v.put("Acetate", "Iqn4UdKY1MY");
            v.put("Phosphate", "FdVO1zX6doA");
            v.put("Carbonate", "PXB8McUBNT4");
            v.put("Bromide", "dMK4RoYh01o");
            v.put("Sulphite", "-pdKtHePATQ");
            v.put("Nitrite", "CWSMiXq-7Wc");
            v.put("Oxalate", "0B_Mq5uY9Ng");
            v.put("Iodide", "YVs24Q9yjgI");
            v.put("Sulphide", "3bxac77mzuI");

            v.put("Aluminium", "0HPi7X2yhbw");
            v.put("Ammonium", "GTZBs3iZgg0");
            v.put("Zinc", "v7_glUDP_sk");
            v.put("Barium", "gaamIjai20o");
            v.put("Copper", "czm2pwjiftc");
            v.put("Calcium", "uKy424Vf_44");
            v.put("Magnesium", "j8a7ItqTowc");
            v.put("Strontium", "M8YqOhM2J54");
            v.put("Manganese", "Abcyr8AqwAc");
            v.put("Nickel", "TVNRUMMCg4o");
            v.put("Iron", "PPG9XIp0HhY");
            v.put("Lead", "f7PfeaA8kow");
            v.put("Cobalt", "MRELKbMFF_E");
        }
        setContentView(R.layout.activity_display_radical);
        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(ProjectUtilities.md5(android_id).toUpperCase())
                .build();
        final AdView adView = (AdView) findViewById(R.id.adViewDisplayRadical);
        adView.setVisibility(View.GONE);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                adView.setVisibility(View.GONE);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }
        });
        adView.loadAd(request);

        player = (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_player_fragment);
        player.initialize(youtubeDevKey, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if (v.containsKey(radicalName))
                    youTubePlayer.cueVideo(v.get(radicalName));
                else {
                    getFragmentManager().beginTransaction()
                            .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                            .hide(player)
                            .commit();
                    View rootElement = findViewById(R.id.root_scroll_view);
                    Snackbar.make(rootElement, "Arsenic is extremely poisonous, and thus the experiment video isn't available", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                        .hide(player)
                        .commit();
                youTubeInitializationResult.getErrorDialog(DisplayRadicalActivity.this, 0).show();
            }
        });

        radicalName = getIntent().getStringExtra("radical");
        /*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            Log.v("HAHA", "Reached inside");
            try {
                ((LinearLayout) findViewById(R.id.display_radical_root))
                        .addView(getNewAdInstance(this, getString(R.string.ad_unit_radical_info_bottom)), 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        Log.v("HAHA", radicalName);
        List<Radicals.Radical> radicalList = Radicals.getRadicalDetails();
        Radicals.Radical radical = null;
        for (Radicals.Radical r : radicalList)
            if (radicalName.equals(r.name)) {
                radical = r;
            }
        if (radical != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(formatString(radicalName + " - " + radical.formula));
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            ArrayList<Experiment> experiments = radical.experiment;
            LinearLayout rootLayout = (LinearLayout) findViewById(R.id.radical_experiments_list);
            //ListView listView = (ListView) findViewById(R.id.radical_experiments_list);
            //listView.setAdapter(new ExperimentAdapter(experiments, this));

            for (int position = 0; position < experiments.size(); position++) {
                View convertView = LayoutInflater.from(this).inflate(R.layout.experiment_item, rootLayout, false);

                LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.expt_linear_layout);
                TextView generalText = (TextView) convertView.findViewById((R.id.general_text_view));

                Experiment curExpt = experiments.get(position);

                if (ProjectUtilities.isGeneralExpt(curExpt)) {
                    linearLayout.setVisibility(View.GONE);
                    generalText.setText(formatString(curExpt.getExperiment()));
                    generalText.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.VISIBLE);
                    generalText.setVisibility(View.GONE);

                    TextView textView = (TextView) convertView.findViewById(R.id.experiment_text_view);
                    textView.setText(formatString(curExpt.getExperiment()));

                    textView = (TextView) convertView.findViewById(R.id.observation_text_view);
                    textView.setText(formatString(curExpt.getObservation()));

                    textView = (TextView) convertView.findViewById(R.id.conclusion_text_view);
                    textView.setText(formatString(curExpt.getConclusion()));
                }
                rootLayout.addView(convertView);
                convertView = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, rootLayout, false);
                rootLayout.addView(convertView);
            }
        }

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
