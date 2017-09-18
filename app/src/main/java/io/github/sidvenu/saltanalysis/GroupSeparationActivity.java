package io.github.sidvenu.saltanalysis;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GroupSeparationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] names = {"Lead", "Copper", "Arsenic", "Aluminium", "Iron", "Manganese", "Cobalt",
                "Nickel", "Zinc", "Barium", "Strontium", "Calcium", "Magnesium"};
        Integer[] id = {R.id.lead_radical, R.id.copper_radical, R.id.arsenic_radical, R.id.aluminium_radical,
                R.id.iron_radical, R.id.manganese_radical, R.id.cobnick_radical, R.id.cobnick_radical,
                R.id.zinc_radical, R.id.ium_radical, R.id.ium_radical, R.id.ium_radical, R.id.magnesium_radical};

        Map<String, radicalInfo> nametoId = new HashMap<>();
        nametoId.put(names[0], new radicalInfo("White ppt of PbCl{2}", id[0]));
        nametoId.put(names[1], new radicalInfo("Black ppt of CuS", id[1]));
        nametoId.put(names[2], new radicalInfo("Yellow ppt of As{2}S{3}", id[2]));
        nametoId.put(names[3], new radicalInfo("Gelatinous white ppt of Al(OH){3}", id[3]));
        nametoId.put(names[4], new radicalInfo("Reddish brown ppt of Fe(OH){3}", id[4]));
        nametoId.put(names[5], new radicalInfo("Flesh coloured ppt of MnS", id[5]));
        nametoId.put(names[6], new radicalInfo("Black ppt of CoS or NiS", id[6]));
        nametoId.put(names[7], new radicalInfo("Black ppt of CoS or NiS", id[7]));
        nametoId.put(names[8], new radicalInfo("White ppt of ZnS", id[8]));
        nametoId.put(names[9], new radicalInfo("White ppt of BaCO{3}, SrCO{3} or CaCO{3}", id[9]));
        nametoId.put(names[10], new radicalInfo("White ppt of BaCO{3}, SrCO{3} or CaCO{3}", id[10]));
        nametoId.put(names[11], new radicalInfo("White ppt of BaCO{3}, SrCO{3} or CaCO{3}", id[11]));
        nametoId.put(names[12], new radicalInfo("White ppt of Mg(NH{4}){2}PO{4}", id[12]));

        setContentView(R.layout.activity_group_separation);

        String basicRadical = getIntent().getStringExtra("basic_radical_name");


        if (!TextUtils.isEmpty(basicRadical)) {
            Set<Integer> idSet = new HashSet<>(Arrays.asList(id));
            int curId = -1;
            if (nametoId.get(basicRadical) != null) {
                curId = nametoId.get(basicRadical).id;
                attachOnClickPrecipitates(findViewById(curId), nametoId.get(basicRadical).msg);
            }
            for (int i : idSet)
                if (i != curId)
                    findViewById(i).setVisibility(View.GONE);
        } else {
            for (int i = 0; i < nametoId.size(); i++)
                attachOnClickPrecipitates(findViewById(nametoId.get(names[i]).id), nametoId.get(names[i]).msg);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dpPrecipitateElement = (int) ((displayMetrics.widthPixels - 45 * displayMetrics.density) / 6.0);
        LinearLayout precipitate = findViewById(R.id.grp_1_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = findViewById(R.id.grp_2_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = findViewById(R.id.grp_3_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = findViewById(R.id.grp_4_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = findViewById(R.id.grp_5_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        dpPrecipitateElement = (int) ((displayMetrics.widthPixels + 195 * displayMetrics.density) / 6.0);
        precipitate = findViewById(R.id.grp_6_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;

        TextView textView = findViewById(R.id.grp_1_text);
        textView.setText(formatText(getResources().getString(R.string.grp_1_text)));
        textView = findViewById(R.id.grp_2_text);
        textView.setText(formatText(getResources().getString(R.string.grp_2_text)));
        textView = findViewById(R.id.grp_3_text);
        textView.setText(formatText(getResources().getString(R.string.grp_3_text)));
        textView = findViewById(R.id.grp_4_text);
        textView.setText(formatText(getResources().getString(R.string.grp_4_text)));
        textView = findViewById(R.id.grp_5_text);
        textView.setText(formatText(getResources().getString(R.string.grp_5_text)));
        textView = findViewById(R.id.grp_6_text);
        textView.setText(formatText(getResources().getString(R.string.grp_6_text)));
    }

    private void attachOnClickPrecipitates(final View precipitate, final String msg) {
        precipitate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("Touch event", "detect");
                final Snackbar sb = Snackbar.make(findViewById(R.id.parent_group_separation), formatText(msg), Snackbar.LENGTH_INDEFINITE);
                sb.show();
                sb.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sb.dismiss();
                    }
                });
            }
        });
    }

    Spanned formatText(String s) {
        //noinspection deprecation
        return Html.fromHtml(s.replace("{", "<sub><small><small>").replace("}", "</small></small></sub>").replace("\n", "<br />"));
    }

    private class radicalInfo {
        String msg;
        int id;

        radicalInfo(String msg, int id) {
            this.msg = msg;
            this.id = id;
        }
    }
}