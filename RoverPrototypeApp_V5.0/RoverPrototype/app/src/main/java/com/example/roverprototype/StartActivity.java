package com.example.roverprototype;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class StartActivity extends AppCompatActivity {
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        progressBar=findViewById(R.id.id_progressBar);

        progressBar.setMax(100);
        progressBar.setScaleY(3f);
        progressAnimation();

        RoverCommands.getInstance();
    }

    public void progressAnimation() {
        ProgressBarAnimation amin = new ProgressBarAnimation(this, progressBar, 0f, 100f,this);
        amin.setDuration(3000);
        progressBar.setAnimation(amin);
    }

}