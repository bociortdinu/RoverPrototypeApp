package com.example.roverprototype;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private float from;
    private float to;
    private Activity a;
    private int contor=0;
    public ProgressBarAnimation(Context context, ProgressBar progressBar, float from, float to, Activity a)
    {
        this.context=context;
        this.progressBar=progressBar;
        this.from=from;
        this.to=to;
        this.a=a;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value= from + (to-from)*interpolatedTime;
        progressBar.setProgress((int)value);

        if(value==to)
        {
            contor++;
            if(contor==1) {
                startLogin();
                a.finish();
            }
        }
    }

    private void startLogin()
    {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
