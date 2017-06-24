package io.github.siddharthvenu.saltanalysis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import static io.github.siddharthvenu.saltanalysis.ProjectUtilities.formatString;


public class LongProcedureActivity extends AppCompatActivity {

    Radicals.Radical currentAcidRadical, currentBasicRadical;
    List<Radicals.Radical> acidRadicals, basicRadicals;
    String noChange = "No Change";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_procedure);

        /* set typeface for headers */
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/fifawelcome.ttf");
        ((TextView) findViewById(R.id.dry_tests_header)).setTypeface(tf);
        ((TextView) findViewById(R.id.wet_tests_header)).setTypeface(tf);
        ((TextView) findViewById(R.id.basic_radical_header)).setTypeface(tf);


        CardView groupSeparationTransit = findViewById(R.id.group_separation_transit_button);
        groupSeparationTransit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LongProcedureActivity.this, GroupSeparationActivity.class).putExtra("basic_radical_name", currentBasicRadical.name));
            }
        });
        View whitespace = findViewById(R.id.white_space);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) whitespace.getLayoutParams();
        groupSeparationTransit.measure(0, 0);
        params.height = groupSeparationTransit.getMeasuredHeight();
        whitespace.setLayoutParams(params);

        MaterialSpinner spinner = findViewById(R.id.acid_choice);
        whitespace = findViewById(R.id.top_white_space);
        params = (LinearLayout.LayoutParams) whitespace.getLayoutParams();
        spinner.measure(0, 0);
        params.height = spinner.getMeasuredHeight();
        whitespace.setLayoutParams(params);

        final AdView adView = findViewById(R.id.adViewDryTests);
        adView.setVisibility(View.GONE);

        @SuppressLint("HardwareIds")
        String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        AdRequest request = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice(ProjectUtilities.md5(android_id).toUpperCase())
                .build();
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


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /*if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.N_MR1) {
            Log.v("HAHA", "Reached inside");
            try {
                ((LinearLayout) findViewById(R.id.long_procedure_root))
                        .addView(getNewAdInstance(this, getString(R.string.ad_unit_long_procedure_dry_tests)), 1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
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
        updateLongProcedure(true);
        updateLongProcedure(false);

        spinner.setItems(acidNames);
        spinner.setDropdownMaxHeight(Resources.getSystem().getDisplayMetrics().heightPixels / 2);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                currentAcidRadical = acidRadicals.get(position);
                updateLongProcedure(true);
            }
        });
        spinner = findViewById(R.id.base_choice);
        spinner.setItems(basicNames);
        spinner.setDropdownMaxHeight(Resources.getSystem().getDisplayMetrics().heightPixels / 2);
        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                currentBasicRadical = basicRadicals.get(position);
                updateLongProcedure(false);
            }
        });
    }

    void setTitleTextView(TextView title, String text) {
        title.setVisibility(View.VISIBLE);
        title.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);
        title.setTextSize(15);

        //noinspection deprecation
        title.setTextColor(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? getColor(android.R.color.black)
                : getResources().getColor(android.R.color.black));
        title.setText(formatString(text));
    }

    void updateLongProcedure(boolean isAcidUpdated) {
        if (isAcidUpdated) {

            // DRY TESTS
            LinearLayout linearLayout = findViewById(R.id.dry_tests_long_procedure);
            linearLayout.removeAllViews();
            int curIndex = 0;
            List<Experiment> currentAcidRadicalExpts = currentAcidRadical.experiment;

            List<Radicals.Radical> radicalList = Radicals.getRadicalDetails();
            List<String> hcl = new ArrayList<>(), h2so4 = new ArrayList<>(), heat = new ArrayList<>(), agno3 = new ArrayList<>();
            boolean givesHeat, givesHCl, givesH2SO4, givesAgNO3;
            int heatGivePOS = -1, hclGivePOS = -1, h2so4GivePOS = -1, agno3GivePOS = -1;
            givesH2SO4 = givesHCl = givesHeat = givesAgNO3 = false;

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
                                case "AGNO3":
                                    if(r.name.equals(currentAcidRadical.name)){
                                        givesAgNO3 = true;
                                        agno3GivePOS = expt.indexOf(e);
                                    } else
                                        agno3.add(r.formula);
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

                            layout = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, linearLayout, false);
                            linearLayout.addView(layout, curIndex++);

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

                            layout = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, linearLayout, false);
                            linearLayout.addView(layout, curIndex++);

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

                            layout = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, linearLayout, false);
                            linearLayout.addView(layout, curIndex++);

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
            int curWetIndex = 0;
            LinearLayout wetLinearLayout = findViewById(R.id.wet_tests_long_procedure);
            wetLinearLayout.removeAllViews();

            {
                View layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, wetLinearLayout, false);
                layout.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
                //layout.setTag("reuse");
                setTitleTextView((TextView) layout.findViewById(R.id.general_text_view), "<u>Test for Halides</u>");
                wetLinearLayout.addView(layout, curWetIndex++);
                //AgNO3
                StringBuilder conclusion = new StringBuilder();
                if (givesAgNO3) {
                    conclusion.append(currentAcidRadicalExpts.get(agno3GivePOS).getConclusion()).append(". ");
                }
                if (agno3.size() > 0) {
                    conclusion.append("Absence of");
                    for (String s : agno3) {
                        conclusion.append(' ').append(s).append(',');
                    }
                    conclusion.deleteCharAt(conclusion.length() - 1);
                }
                layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, wetLinearLayout, false);
                ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString("Wet Test:\nTo the salt solution, dil.HNO{3} is added followed by AgNO{3}"));

                ((TextView) layout.findViewById(R.id.observation_text_view)).setText(givesAgNO3 ? formatString(currentAcidRadicalExpts.get(agno3GivePOS).getObservation()) : noChange);

                ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(conclusion.toString()));

                //layout.setTag("reuse");
                wetLinearLayout.addView(layout, curWetIndex++);
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
                    boolean lastTime = false;
                    for (Experiment e : experimentList) {
                        //layout.setTag("reuse");
                        if (e.isDryTest() && e.getTag().equals("SPL")) {
                            if (sameRadical) {
                                if (lastTime) {
                                    layout = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, linearLayout, false);
                                    linearLayout.addView(layout, curIndex++);
                                }
                                layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                                ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                                ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(e.getObservation()));

                                ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(e.getConclusion()));

                                linearLayout.addView(layout, curIndex++);
                                lastTime = true;
                            } else {
                                layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);
                                ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                                ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(noChange));

                                ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString("Absence of " + r.formula));
                                linearLayout.addView(layout, curIndex++);
                                break;
                            }
                        } else lastTime = false;
                    }
                }
                if (r.isWetSplPresent) {
                    View layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, wetLinearLayout, false);
                    layout.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
                    //layout.setTag("reuse");
                    setTitleTextView((TextView) layout.findViewById(R.id.general_text_view), "<u>Test for " + r.formula + "</u>");
                    wetLinearLayout.addView(layout, curWetIndex++);
                    List<Experiment> experimentList = r.experiment;
                    boolean sameRadical = false;
                    if (r.name.equals(currentAcidRadical.name)) {
                        sameRadical = true;
                    }
                    boolean lastTime = false;
                    for (Experiment e : experimentList) {
                        //layout.setTag("reuse");
                        if ((!e.isDryTest()) && (e.getTag().equals("SPL")||e.getTag().equals("CONTINUE"))) {
                            if (sameRadical) {
                                if (lastTime) {
                                    layout = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, wetLinearLayout, false);
                                    wetLinearLayout.addView(layout, curWetIndex++);
                                }
                                layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, wetLinearLayout, false);
                                ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                                ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(e.getObservation()));

                                ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString(e.getConclusion()));

                                wetLinearLayout.addView(layout, curWetIndex++);
                                lastTime = true;
                            } else {
                                layout = LayoutInflater.from(this).inflate(R.layout.experiment_item, wetLinearLayout, false);
                                ((TextView) layout.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                                ((TextView) layout.findViewById(R.id.observation_text_view)).setText(formatString(noChange));

                                ((TextView) layout.findViewById(R.id.conclusion_text_view)).setText(formatString("Absence of " + r.formula));
                                wetLinearLayout.addView(layout, curWetIndex++);
                                break;
                            }
                        } else lastTime = false;
                    }
                }
            }
        } else {
            LinearLayout linearLayout = findViewById(R.id.basic_radical_long_procedure);
            linearLayout.removeAllViews();
            int curIndex = 0;
            List<Experiment> currentBasicRadicalExpts = currentBasicRadical.experiment;
            for (Experiment e : currentBasicRadicalExpts) {
                View v;

                if (curIndex != 0) {
                    v = LayoutInflater.from(this).inflate(R.layout.divider_horizontal, linearLayout, false);
                    linearLayout.addView(v, curIndex++);
                }
                v = LayoutInflater.from(this).inflate(R.layout.experiment_item, linearLayout, false);

                if (ProjectUtilities.isGeneralExpt(e)) {
                    v.findViewById(R.id.expt_linear_layout).setVisibility(View.GONE);
                    TextView generalText = v.findViewById(R.id.general_text_view);
                    generalText.setText(formatString(e.getExperiment()));
                    generalText.setVisibility(View.VISIBLE);
                    linearLayout.addView(v, curIndex++);
                    continue;
                }

                ((TextView) v.findViewById(R.id.experiment_text_view)).setText(formatString(e.getExperiment()));

                ((TextView) v.findViewById(R.id.observation_text_view)).setText(formatString(e.getObservation()));

                ((TextView) v.findViewById(R.id.conclusion_text_view)).setText(formatString(e.getConclusion()));
                linearLayout.addView(v, curIndex++);
            }
        }
    }

    /*void showDialog(long delTime) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delta Time");
        builder.setMessage("Operation completed in " + String.valueOf(delTime) + "ms");
        builder.create().show();
    }*/


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
