package com.example.roverprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class ReadSensorsActivity extends AppCompatActivity {

    private static String TAG = "ReadSensorsActivity";
    public static Button button_ClearInput;
    public static CheckBox checkBox_Scroll;
    public static CheckBox checkBox_Read;
    public static TextView textView_MessageRead;
    public static ScrollView viewScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sensors);

        if (BluetoothConnection.getInstance().getmBTSocket()!=null)
        {
            try
            {
                BluetoothConnection.getInstance().getmBTSocket().getOutputStream().write("readON".getBytes());
                Log.e(TAG,"write : " +  "readON");

            }
            catch (IOException e)
            {
                msg("Error");
            }
        }


        button_ClearInput = findViewById(R.id.button_ClearInput);
        checkBox_Scroll = findViewById(R.id.checkBox_Scroll);
        checkBox_Read = findViewById(R.id.checkBox_Read);
        textView_MessageRead = findViewById(R.id.textView_MessageRead);
        viewScroll = findViewById(R.id.viewScroll);

        button_ClearInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_MessageRead.setText("");
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG,"onStop");

        if (BluetoothConnection.getInstance().getmBTSocket()!=null)
        {
            try
            {
                BluetoothConnection.getInstance().getmBTSocket().getOutputStream().write("readOFF".getBytes());
                Log.e(TAG,"write : " +  "readOFF");

            }
            catch (IOException e)
            {
                msg("Error");
            }
        }

    }

    public void msg(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}