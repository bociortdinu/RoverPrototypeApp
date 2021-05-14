package com.example.roverprototype;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ScenarioActivity extends AppCompatActivity {


    ImageView scenario1_OFF;
    ImageView scenario2_OFF;
    ImageView scenario3_OFF;
    ImageView scenario4_OFF;

    ImageView scenario1_ON;
    ImageView scenario2_ON;
    ImageView scenario3_ON;
    ImageView scenario4_ON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        scenario1_OFF = findViewById(R.id.image_Scenario1_OFF);
        scenario2_OFF = findViewById(R.id.image_Scenario2_OFF);
        scenario3_OFF = findViewById(R.id.image_Scenario3_OFF);
        scenario4_OFF = findViewById(R.id.image_Scenario4_OFF);

        scenario1_ON = findViewById(R.id.image_Scenario1_ON);
        scenario2_ON = findViewById(R.id.image_Scenario2_ON);
        scenario3_ON = findViewById(R.id.image_Scenario3_ON);
        scenario4_ON = findViewById(R.id.image_Scenario4_ON);

        scenario1_ON.setVisibility(View.INVISIBLE);
        scenario2_ON.setVisibility(View.INVISIBLE);
        scenario3_ON.setVisibility(View.INVISIBLE);
        scenario4_ON.setVisibility(View.INVISIBLE);

        scenario1_OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeScenarioActivity.class);
                startActivity(intent);
            }
        });

    }
}