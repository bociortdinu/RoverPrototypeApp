package com.example.roverprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;


public class ledControl extends AppCompatActivity {

    Button btnOn, btnOff, btnDis;
    SeekBar brightness;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    TextView inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led_control);

        //receive the address of the bluetooth device
        Intent newint = getIntent();
        address = newint.getStringExtra(DeviceList.EXTRA_ADDRESS);
        Log.e("ledControl","onCreate() : DeviceList.EXTRA_ADDRESS = " + DeviceList.EXTRA_ADDRESS);

//view of the ledControl layout
        setContentView(R.layout.activity_led_control);
//call the widgtes
        btnOn = (Button)findViewById(R.id.button_LightOn);
        btnOff = (Button)findViewById(R.id.button_LightOff);
        btnDis = (Button)findViewById(R.id.button_Disconnect);


        inputText = findViewById(R.id.InputText);

        new ConnectBT().execute(); //Call the class to connect

        btnOn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnLed();      //method to turn on
            }
        });

        btnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffLed();   //method to turn off
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect(); //close connection
            }
        });


//        if (myBluetooth == null) {
//            Toast.makeText(getApplicationContext(),"Device doesnt Support Bluetooth",Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            ByteArrayInputStream inputStream = null;
//
//            int byteCount = inputStream.available();
//            if(byteCount > 0)
//            {
//                byte[] rawBytes = new byte[byteCount];
//                try {
//                    inputStream.read(rawBytes);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                try {
//
//                    final String string=new String(rawBytes,"UTF-8");
//                    inputText.setText(string);
//                    Log.e("ledControl","inputText : " + string);
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }



    }

    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
                Log.e("turnOffLed","close connection");
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout
    }

    private void turnOffLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("led off".toString().getBytes());
                Log.e("turnOffLed","write 0");
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }


    }

    private void turnOnLed()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("led on".toString().getBytes());
                Log.e("turnOnLed","write 1");
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }



    }


    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(ledControl.this, "Connecting...", "Please wait!!!");  //show a progress dialog
            Log.e("ledControl","onPreExecute() : Connecting... Please wait!!!");
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                Log.e("ledControl","doInBackground() : Acum se face conexiuna");

                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    Log.e("ledControl","doInBackground() : get the mobile bluetooth device");
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    Log.e("ledControl","doInBackground() : connects to the device's address and checks if it's available");
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    Log.e("ledControl","doInBackground() : create a RFCOMM (SPP) connection");
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    Log.e("ledControl","doInBackground() : start connection");
                    btSocket.connect();//start connection

                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                Log.e("ledControl","onPostExecute() : Connected");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

}



