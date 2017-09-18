package io.github.sidvenu.saltanalysis;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/helvetica.ttf");
        TextView tv = findViewById(R.id.home_header);
        tv.setTypeface(tf);
        tv = findViewById(R.id.acid_radical_text_view);
        tv.setTypeface(tf);
        tv = findViewById(R.id.basic_radical_text_view);
        tv.setTypeface(tf);
        tv = findViewById(R.id.long_procedure_text_view);
        tv.setTypeface(tf);
        tv = findViewById(R.id.group_separation_text_view);
        tv.setTypeface(tf);

        CardView basicRadical = findViewById(R.id.basic_card_home),
                acidicRadical = findViewById(R.id.acid_card_home),
                grpSeparation = findViewById(R.id.grp_separation_card_home),
                longProcedure = findViewById(R.id.long_procedure_card_home);

        basicRadical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(launchNextScreen(false));
            }
        });
        acidicRadical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(launchNextScreen(true));
            }
        });
        grpSeparation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, GroupSeparationActivity.class));
            }
        });
        longProcedure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, LongProcedureActivity.class));
            }
        });
    }

    private Intent launchNextScreen(boolean isAcidic) {
        return new Intent(this, RadicalListActivity.class).putExtra("isAcidic", isAcidic);
    }
}
