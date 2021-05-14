package com.example.roverprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ConnectionActivity extends AppCompatActivity {

    private Button search;
    private Button connect;
    private ListView listView;

    private static final int BT_ENABLE_REQUEST = 10;  // This is the code we use for BT Enable
    private static final int SETTINGS = 20;
    private static final String TAG = "ConnectionActivity";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        BluetoothConnection.getInstance().setActivtyContext(this);

        search = (Button) findViewById(R.id.search);
        connect = (Button) findViewById(R.id.connect);

        listView = (ListView) findViewById(R.id.listview);

        if (savedInstanceState != null) {
            ArrayList<BluetoothDevice> list = savedInstanceState.getParcelableArrayList(BluetoothConnection.getInstance().getDEVICE_LIST());
            if (list != null) {
                initList(list);
                MyAdapter adapter = (MyAdapter) listView.getAdapter();
                int selectedIndex = savedInstanceState.getInt(BluetoothConnection.getInstance().getDEVICE_LIST_SELECTED());
                if (selectedIndex != -1) {
                    adapter.setSelectedIndex(selectedIndex);
                    connect.setEnabled(true);
                }
            } else {
                initList(new ArrayList<BluetoothDevice>());
            }

        } else {
            initList(new ArrayList<BluetoothDevice>());
        }
        search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BluetoothConnection.getInstance().setmBTAdapter(BluetoothAdapter.getDefaultAdapter());

                if (BluetoothConnection.getInstance().getmBTAdapter() == null) {
                    Toast.makeText(getApplicationContext(), "Bluetooth not found", Toast.LENGTH_SHORT).show();
                } else if (!BluetoothConnection.getInstance().getmBTAdapter().isEnabled()) {
                    Intent enableBT = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBT, BT_ENABLE_REQUEST);
                } else {
                    new SearchDevices().execute();
                }
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                BluetoothDevice device = ((MyAdapter) (listView.getAdapter())).getSelectedItem();
                Intent intent = new Intent(getApplicationContext(), RoverControlActivity.class);
                BluetoothConnection.getInstance().setDevice(device);
                startActivity(intent);
            }
        });

    }

    protected void onPause() {
// TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onStop() {
// TODO Auto-generated method stub
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case BT_ENABLE_REQUEST:
                if (resultCode == RESULT_OK) {
                    msg("Bluetooth Enabled successfully");
                    new SearchDevices().execute();
                } else {
                    msg("Bluetooth couldn't be enabled");
                }

                break;
            case SETTINGS: //If the settings have been updated
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                String uuid = prefs.getString("prefUuid", "Null");
                BluetoothConnection.getInstance().setmDeviceUUID(UUID.fromString(uuid));
                Log.d(TAG, "UUID: " + uuid);
                String bufSize = prefs.getString("prefTextBuffer", "Null");
                BluetoothConnection.getInstance().setmBufferSize(Integer.parseInt(bufSize));

                String orientation = prefs.getString("prefOrientation", "Null");
                Log.d(TAG, "Orientation: " + orientation);
                if (orientation.equals("Landscape")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else if (orientation.equals("Portrait")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                } else if (orientation.equals("Auto")) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Quick way to call the Toast
     * @param str
     */
    private void msg(String str) {
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }

    /**
     * Initialize the List adapter
     * @param objects
     */
    private void initList(List<BluetoothDevice> objects) {
        final MyAdapter adapter = new MyAdapter(getApplicationContext(), R.layout.list_item, R.id.lstContent, objects);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedIndex(position);
                connect.setEnabled(true);
            }
        });
    }

    /**
     * Searches for paired devices. Doesn't do a scan! Only devices which are paired through Settings->Bluetooth
     * will show up with this. I didn't see any need to re-build the wheel over here
     * @author ryder
     *
     */
    private class SearchDevices extends AsyncTask<Void, Void, List<BluetoothDevice>> {

        @Override
        protected List<BluetoothDevice> doInBackground(Void... params) {
            Set<BluetoothDevice> pairedDevices = BluetoothConnection.getInstance().getmBTAdapter().getBondedDevices();
            List<BluetoothDevice> listDevices = new ArrayList<BluetoothDevice>();
            for (BluetoothDevice device : pairedDevices) {
                listDevices.add(device);
            }
            return listDevices;

        }

        @Override
        protected void onPostExecute(List<BluetoothDevice> listDevices) {
            super.onPostExecute(listDevices);
            if (listDevices.size() > 0) {
                MyAdapter adapter = (MyAdapter) listView.getAdapter();
                adapter.replaceItems(listDevices);
            } else {
                msg("No paired devices found, please pair your serial BT device and try again");
            }
        }

    }

    /**
     * Custom adapter to show the current devices in the list. This is a bit of an overkill for this
     * project, but I figured it would be good learning
     * Most of the code is lifted from somewhere but I can't find the link anymore
     * @author ryder
     *
     */
    private class MyAdapter extends ArrayAdapter<BluetoothDevice> {
        private int selectedIndex;
        private Context context;
        private int selectedColor = Color.parseColor("#abcdef");
        private List<BluetoothDevice> myList;

        public MyAdapter(Context ctx, int resource, int textViewResourceId, List<BluetoothDevice> objects) {
            super(ctx, resource, textViewResourceId, objects);
            context = ctx;
            myList = objects;
            selectedIndex = -1;
        }

        public void setSelectedIndex(int position) {
            selectedIndex = position;
            notifyDataSetChanged();
        }

        public BluetoothDevice getSelectedItem() {
            return myList.get(selectedIndex);
        }

        @Override
        public int getCount() {
            return myList.size();
        }

        @Override
        public BluetoothDevice getItem(int position) {
            return myList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        private class ViewHolder {
            TextView tv;
        }

        public void replaceItems(List<BluetoothDevice> list) {
            myList = list;
            notifyDataSetChanged();
        }

        public List<BluetoothDevice> getEntireList() {
            return myList;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View vi = convertView;
            ViewHolder holder;
            if (convertView == null) {
                vi = LayoutInflater.from(context).inflate(R.layout.list_item, null);
                holder = new ViewHolder();

                holder.tv = (TextView) vi.findViewById(R.id.lstContent);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            if (selectedIndex != -1 && position == selectedIndex) {
                holder.tv.setBackgroundColor(selectedColor);
            } else {
                holder.tv.setBackgroundColor(Color.WHITE);
            }
            BluetoothDevice device = myList.get(position);
            holder.tv.setText(device.getName() + "\n " + device.getAddress());

            return vi;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.homescreen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent intent = new Intent(ConnectionActivity.this, PreferencesActivity.class);
                startActivityForResult(intent, SETTINGS);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}