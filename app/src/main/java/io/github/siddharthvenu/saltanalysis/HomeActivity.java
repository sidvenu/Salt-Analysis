package io.github.siddharthvenu.saltanalysis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        CardView basicRadical= (CardView) findViewById(R.id.basic_home),
                acidicRadical= (CardView) findViewById(R.id.acid_home),
                grpSeparation = (CardView) findViewById(R.id.grp_separation_card);

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
    }
    private Intent launchNextScreen(boolean isAcidic){
        return new Intent(this, RadicalListActivity.class).putExtra("isAcidic",isAcidic);
    }
}
