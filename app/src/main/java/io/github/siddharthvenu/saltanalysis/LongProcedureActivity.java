package io.github.siddharthvenu.saltanalysis;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static io.github.siddharthvenu.saltanalysis.StringUtilities.formatString;


public class LongProcedureActivity extends AppCompatActivity {

    Radicals.Radical currentAcidRadical, currentBasicRadical;
    List<Radicals.Radical> acidRadicals, basicRadicals;
    String noChange = "No Change";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_procedure);

        final AdView adView = (AdView) findViewById(R.id.adViewDryTests);
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
        updateLongProcedure();

        MaterialSpinner spinner = (MaterialSpinner) findViewById(R.id.acid_choice);
        spinner.setItems(acidNames);
        spinner.setDropdownMaxHeight(Resources.getSystem().getDisplayMetrics().heightPixels / 2);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                currentAcidRadical = acidRadicals.get(position);
                updateLongProcedure();
                //Log.v("HAHA","You selected "+currentAcidRadical.name);
            }
        });
        spinner = (MaterialSpinner) findViewById(R.id.base_choice);
        spinner.setItems(basicNames);
        spinner.setDropdownMaxHeight(Resources.getSystem().getDisplayMetrics().heightPixels / 2);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                currentBasicRadical = basicRadicals.get(position);
                updateLongProcedure();
                //Log.v("HAHA","You selected "+currentBasicRadical.name);
            }
        });
    }

    void setTitleTextView(TextView title, String text) {
        title.setVisibility(View.VISIBLE);
        title.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        title.setTextSize(15);

        title.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? getColor(android.R.color.black)
                : getResources().getColor(android.R.color.black));
        title.setText(formatString(text));
    }

    void updateLongProcedure() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.dry_tests_long_procedure);
        //int curIndex = linearLayout.indexOfChild(findViewById(R.id.adViewDryTests)) + 1;
        int curIndex = 0;
        List<Experiment> currentAcidRadicalExpts = currentAcidRadical.experiment;
        /*for (int i = 0; i < linearLayout.getChildCount(); i++) {
            Object childTag = linearLayout.getChildAt(i).getTag();
            if (childTag != null && childTag.equals("reuse")) {
                linearLayout.removeViewAt(i--);
            }
        }*/
        linearLayout.removeAllViews();
        List<Radicals.Radical> radicalList = Radicals.getRadicalDetails();
        List<String> hcl = new ArrayList<>(), h2so4 = new ArrayList<>(), heat = new ArrayList<>();
        boolean givesHeat, givesHCl, givesH2SO4;
        int heatGivePOS = -1, hclGivePOS = -1, h2so4GivePOS = -1;
        givesH2SO4 = givesHCl = givesHeat = false;

        for (Radicals.Radical r : radicalList) {
            if (r != null) {
                ArrayList<Experiment> expt = r.experiment;

                for (Experiment e : expt) {
                    if (e.getTag() != null)
                        switch (e.getTag()) {
                            case "HCL":
                                if (r.name.equals(currentAcidRadical.name)) {
                                    givesHCl = true;
                                    hclGivePOS = expt.indexOf(e);
                                } else
                                    hcl.add(r.formula);
                                break;
                            case "H2SO4":
                                if (r.name.equals(currentAcidRadical.name)) {
                                    givesH2SO4 = true;
                                    h2so4GivePOS = expt.indexOf(e);
                                } else
                                    h2so4.add(r.formula);
                                break;
                            case "HEAT":
                                if (r.name.equals(currentAcidRadical.name)) {
                                    givesHeat = true;
                                    heatGivePOS = expt.indexOf(e);
                                } else
                                    heat.add(r.formula);
                                break;
                        }
                }
            }
        }
        {

            View layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
            layout.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
            //layout.setTag("reuse");
            setTitleTextView((TextView) layout.findViewById(R.id.general_text_view), "<u>Dry test tube heating</u>");
            linearLayout.addView(layout, curIndex++);

            //HEAT
            StringBuilder conclusion = new StringBuilder();
            if (givesHeat) {
                conclusion.append(currentAcidRadicalExpts.get(heatGivePOS).getConclusion()).append(". ");
            }
            if (heat.size() > 0) {
                conclusion.append("Absence of");
                for (String s : heat) {
                    conclusion.append(' ').append(s).append(',');
                }
                conclusion.deleteCharAt(conclusion.length() - 1);
            }
            layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
            ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString("The salt was taken in a dry test tube and heated strongly"));

            ((TextView) layout.findViewById(R.id.observation_text_view)).setText(givesHeat ? formatString(currentAcidRadicalExpts.get(heatGivePOS).getObservation()) : noChange);

            ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(conclusion.toString()));
            //layout.setTag("reuse");
            linearLayout.addView(layout, curIndex++);
            int temp = heatGivePOS + 1;
            if (givesHeat)
                while (temp < currentAcidRadicalExpts.size() && currentAcidRadicalExpts.get(temp).getTag() != null) {
                    if (currentAcidRadicalExpts.get(temp).getTag().equals("CONTINUE")) {
                        layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                        Experiment currentExpt = currentAcidRadicalExpts.get(temp);
                        ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(currentExpt.getExperiment()));

                        ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(currentExpt.getObservation()));

                        ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(currentExpt.getConclusion()));
                        //layout.setTag("reuse");
                        linearLayout.addView(layout, curIndex++);
                        temp++;
                    } else break;
                }
        }
        {

            View layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
            layout.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
            //layout.setTag("reuse");
            setTitleTextView((TextView) layout.findViewById(R.id.general_text_view), "<u>Action of dil.HCl</u>");
            linearLayout.addView(layout, curIndex++);
            //HCL
            StringBuilder conclusion = new StringBuilder();
            if (givesHCl) {
                conclusion.append(currentAcidRadicalExpts.get(hclGivePOS).getConclusion()).append(". ");
            }
            if (hcl.size() > 0) {
                conclusion.append("Absence of");
                for (String s : hcl) {
                    conclusion.append(' ').append(s).append(',');
                }
                conclusion.deleteCharAt(conclusion.length() - 1);
            }
            layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
            ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString("The salt was taken in a dry test tube and dil.HCl was added"));

            ((TextView) layout.findViewById(R.id.observation_text_view)).setText(givesHCl ? formatString(currentAcidRadicalExpts.get(hclGivePOS).getObservation()) : noChange);

            ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(conclusion.toString()));

            //layout.setTag("reuse");
            linearLayout.addView(layout, curIndex++);
            int temp = hclGivePOS + 1;
            if (givesHCl)
                while (temp < currentAcidRadicalExpts.size() && currentAcidRadicalExpts.get(temp).getTag() != null) {
                    if (currentAcidRadicalExpts.get(temp).getTag().equals("CONTINUE")) {
                        layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                        Experiment currentExpt = currentAcidRadicalExpts.get(temp);
                        ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(currentExpt.getExperiment()));

                        ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(currentExpt.getObservation()));

                        ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(currentExpt.getConclusion()));
                        //layout.setTag("reuse");
                        linearLayout.addView(layout, curIndex++);
                        temp++;
                    } else break;
                }
        }
        {

            View layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
            layout.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
            //layout.setTag("reuse");
            setTitleTextView((TextView) layout.findViewById(R.id.general_text_view), "<u>Action of conc.H{2}SO{4}</u>");
            linearLayout.addView(layout, curIndex++);
            //H2SO4
            StringBuilder conclusion = new StringBuilder();
            if (givesH2SO4) {
                conclusion.append(currentAcidRadicalExpts.get(h2so4GivePOS).getConclusion()).append(". ");
            }
            if (h2so4.size() > 0) {
                conclusion.append("Absence of");
                for (String s : h2so4) {
                    conclusion.append(' ').append(s).append(',');
                }
                conclusion.deleteCharAt(conclusion.length() - 1);
            }
            layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
            ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString("To the salt, conc.H{2}SO{4} was added"));

            ((TextView) layout.findViewById(R.id.observation_text_view)).setText(givesH2SO4 ? formatString(currentAcidRadicalExpts.get(h2so4GivePOS).getObservation()) : noChange);

            ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(conclusion.toString()));

            //layout.setTag("reuse");
            linearLayout.addView(layout, curIndex++);
            int temp = h2so4GivePOS + 1;
            if (givesH2SO4)
                while (temp < currentAcidRadicalExpts.size() && currentAcidRadicalExpts.get(temp).getTag() != null) {
                    if (currentAcidRadicalExpts.get(temp).getTag().equals("CONTINUE")) {
                        layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                        Experiment currentExpt = currentAcidRadicalExpts.get(temp);
                        ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(currentExpt.getExperiment()));

                        ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(currentExpt.getObservation()));

                        ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(currentExpt.getConclusion()));
                        //layout.setTag("reuse");
                        linearLayout.addView(layout, curIndex++);
                        temp++;
                    } else break;
                }
        }
        for (Radicals.Radical r : radicalList) {
            if (r.isDrySplPresent) {
                View layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                layout.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
                //layout.setTag("reuse");
                setTitleTextView((TextView) layout.findViewById(R.id.general_text_view), "<u>Test for " + r.formula + "</u>");
                linearLayout.addView(layout, curIndex++);
                List<Experiment> experimentList = r.experiment;
                boolean sameRadical = false;
                if (r.name.equals(currentAcidRadical.name)) {
                    sameRadical = true;
                }
                for (Experiment e : experimentList) {
                    layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                    //layout.setTag("reuse");
                    if (sameRadical) {
                        if (e.isDryTest()) {
                            ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                            ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(e.getObservation()));

                            ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(e.getConclusion()));

                            linearLayout.addView(layout, curIndex++);
                        }
                    } else {
                        if (e.isDryTest()) {
                            ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                            ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(noChange));

                            ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString("Absence of " + r.formula));
                            linearLayout.addView(layout, curIndex++);
                            break;
                        }
                    }
                }
            }
        }
    }
}
