package io.github.siddharthvenu.saltanalysis;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GroupSeparationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_separation);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();


        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int dpPrecipitateElement = (int) ((displayMetrics.widthPixels - 45 * displayMetrics.density) / 6.0);
        LinearLayout precipitate = (LinearLayout) findViewById(R.id.grp_1_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = (LinearLayout) findViewById(R.id.grp_2_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = (LinearLayout) findViewById(R.id.grp_3_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = (LinearLayout) findViewById(R.id.grp_4_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        precipitate = (LinearLayout) findViewById(R.id.grp_5_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;
        dpPrecipitateElement = (int) ((displayMetrics.widthPixels + 195 * displayMetrics.density) / 6.0);
        precipitate = (LinearLayout) findViewById(R.id.grp_6_ppt);
        precipitate.getLayoutParams().width = dpPrecipitateElement;

        TextView textView = (TextView) findViewById(R.id.grp_1_text);
        textView.setText(formatText(getResources().getString(R.string.grp_1_text)));
        textView = (TextView) findViewById(R.id.grp_2_text);
        textView.setText(formatText(getResources().getString(R.string.grp_2_text)));
        textView = (TextView) findViewById(R.id.grp_3_text);
        textView.setText(formatText(getResources().getString(R.string.grp_3_text)));
        textView = (TextView) findViewById(R.id.grp_4_text);
        textView.setText(formatText(getResources().getString(R.string.grp_4_text)));
        textView = (TextView) findViewById(R.id.grp_5_text);
        textView.setText(formatText(getResources().getString(R.string.grp_5_text)));
        textView = (TextView) findViewById(R.id.grp_6_text);
        textView.setText(formatText(getResources().getString(R.string.grp_6_text)));
    }

    Spanned formatText(String s) {
        return Html.fromHtml(s.replace("{", "<sub><small><small>").replace("}", "</small></small></sub>").replace("\n","<br />"));
    }
}