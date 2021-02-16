package com.example.roverprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.Set;
import java.util.ArrayList;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import android.content.Intent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;


public class DeviceList extends AppCompatActivity {

    public static final String EXTRA_ADDRESS = "98:D3:11:FC:6E:70";

    private Button btnPaired;
    private ListView devicelist;

    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_list);

        btnPaired = (Button)findViewById(R.id.button_Search);
        devicelist = (ListView)findViewById(R.id.listview_bluetoothDevices);

        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null)
        {
            //Show a mensag. that thedevice has no bluetooth adapter
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();

            Log.e("DeviceList","onCreate() : Bluetooth Device Not Available");
            //finish apk
            finish();
        }
        else
        {
            if (myBluetooth.isEnabled())
            {
                Log.e("DeviceList","onCreate() : Bluetooth is Enabled");
            }
            else
            {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }

        btnPaired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Log.e("DeviceList","onCreate() : Am apasat pe Search");

                pairedDevicesList(); //method that will be called
            }
        });

    }


    private void pairedDevicesList()
    {
        pairedDevices = myBluetooth.getBondedDevices();

        Log.e("DeviceList","pairedDevicesList() : getBondedDevices");

        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            Log.e("DeviceList","pairedDevicesList() : Bluetooth Devices Found.");
            for(BluetoothDevice bt: pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address

                Log.e("DeviceList","pairedDevicesList() : Device :" + bt.getName() + "\n" + bt.getAddress());

            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
            Log.e("DeviceList","pairedDevicesList() : No Paired Bluetooth Devices Found.");
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devicelist.setAdapter(adapter);
        devicelist.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }

    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View

            Log.e("DeviceList","myListClickListener - onItemClick() : Get the device MAC address, the last 17 chars in the View");

            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);
            // Make an intent to start next activity.
            Intent i = new Intent(DeviceList.this, ledControl.class);
            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            startActivity(i);
        }
    };



}