package com.example.roverprototype;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;



public class BluetoothConnection {


    public BluetoothDevice mDevice;
    private Context activtyContext;

    private BluetoothAdapter mBTAdapter;
    private static final int BT_ENABLE_REQUEST = 10; // This is the code we use for BT Enable
    private int SETTINGS = 20;
    private int mBufferSize = 50000; //Default

    private String DEVICE_EXTRA = "SOCKET";
    private String DEVICE_UUID = "uuid";
    private String DEVICE_LIST = "devicelist";
    private String DEVICE_LIST_SELECTED = "devicelistselected";
    private String BUFFER_SIZE = "buffersize";


    private int mMaxChars = 50000;//Default
    public UUID mDeviceUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket mBTSocket;
    private ReadInput mReadThread = null;

    private boolean mIsUserInitiatedDisconnect = false;

    private boolean mIsBluetoothConnected = false;



    private ProgressDialog progressDialog;


    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static BluetoothConnection instance;

    BluetoothConnection()
    {

    }

    //synchronized method to control simultaneous access
    synchronized public static BluetoothConnection getInstance()
    {
        if (instance == null)
        {
            // if instance is null, initialize
            instance = new BluetoothConnection();
        }
        return instance;
    }


    public Context getActivtyContext()
    {
        return activtyContext;
    }
    public void setActivtyContext(Context activtyContext)
    {
        this.activtyContext = activtyContext;
    }


    public int getmMaxChars()
    {
        return mMaxChars;
    }
    public void setmMaxChars(int mMaxChars)
    {
        this.mMaxChars = mMaxChars;
    }

    public BluetoothSocket getmBTSocket()
    {
        return mBTSocket;
    }
    public void setmBTSocket(BluetoothSocket mBTSocket)
    {
        this.mBTSocket = mBTSocket;
    }

    public boolean getmIsUserInitiatedDisconnect()
    {
        return mIsUserInitiatedDisconnect;
    }
    public void setmIsUserInitiatedDisconnect(boolean mIsUserInitiatedDisconnect)
    {
        this.mIsUserInitiatedDisconnect = mIsUserInitiatedDisconnect;
    }

    public boolean getmIsBluetoothConnected()
    {
        return mIsBluetoothConnected;
    }
    public void setmIsBluetoothConnected(boolean mIsBluetoothConnected)
    {
        this.mIsBluetoothConnected = mIsBluetoothConnected;
    }

    public ProgressDialog getprogressDialog()
    {
        return progressDialog;
    }
    public void setprogressDialog(ProgressDialog progressDialog)
    {
        this.progressDialog = progressDialog;
    }

    public BluetoothAdapter getmBTAdapter() {
        return mBTAdapter;
    }
    public void setmBTAdapter(BluetoothAdapter mBTAdapter) {
        this.mBTAdapter = mBTAdapter;
    }

    public BluetoothDevice getmDevice() {
        return mDevice;
    }
    public void setDevice(BluetoothDevice mDevice) {
        this.mDevice = mDevice;
    }

    public int getBT_ENABLE_REQUEST(){
        return BT_ENABLE_REQUEST;
    }
//    public void setBT_ENABLE_REQUEST(int BT_ENABLE_REQUEST) {
//        this.BT_ENABLE_REQUEST = BT_ENABLE_REQUEST;
//    }

    public int getSETTINGS(){
        return SETTINGS;
    }
    public void setSETTINGS(int SETTINGS) {
        this.SETTINGS = SETTINGS;
    }

    public UUID getmDeviceUUID(){
        return mDeviceUUID;
    }
    public void setmDeviceUUID(UUID mDeviceUUID) {
        this.mDeviceUUID = mDeviceUUID;
    }

    public int getmBufferSize(){
        return mBufferSize;
    }
    public void setmBufferSize(int mBufferSize) {
        this.mBufferSize = mBufferSize;
    }

    public String getDEVICE_EXTRA(){
        return DEVICE_EXTRA;
    }
    public void setDEVICE_EXTRA(String DEVICE_EXTRA) {
        this.DEVICE_EXTRA = DEVICE_EXTRA;
    }

    public String getDEVICE_UUID(){
        return DEVICE_UUID;
    }
    public void setDEVICE_UUID(String DEVICE_UUID) {
        this.DEVICE_UUID = DEVICE_UUID;
    }

    public String getDEVICE_LIST(){
        return DEVICE_LIST;
    }
    public void setDEVICE_LIST(String DEVICE_LIST) {
        this.DEVICE_LIST = DEVICE_LIST;
    }

    public String getDEVICE_LIST_SELECTED(){
        return DEVICE_LIST_SELECTED;
    }
    public void setDEVICE_LIST_SELECTED (String DEVICE_LIST_SELECTED) {
        this.DEVICE_LIST_SELECTED = DEVICE_LIST_SELECTED;
    }

    public String getBUFFER_SIZE(){
        return BUFFER_SIZE;
    }
    public void setBUFFER_SIZE (String BUFFER_SIZE) {
        this.BUFFER_SIZE = BUFFER_SIZE;
    }


    public ReadInput getmReadThread()
    {
        return mReadThread;
    }
    public void setmReadThread(ReadInput mReadThread)
    {
        this.mReadThread = mReadThread;
    }




    public void msg(String s) {
        Toast.makeText(activtyContext, s, Toast.LENGTH_SHORT).show();
    }


}

class ConnectBT extends AsyncTask<Void, Void, Void> {
    private boolean mConnectSuccessful = true;
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(BluetoothConnection.getInstance().getActivtyContext(), "Hold on", "Connecting");// http://stackoverflow.com/a/11130220/1287554
    }

    @Override
    protected Void doInBackground(Void... devices) {

        try {
            if (BluetoothConnection.getInstance().getmBTSocket() == null || !BluetoothConnection.getInstance().getmIsBluetoothConnected()) {
                BluetoothConnection.getInstance().setmBTSocket(BluetoothConnection.getInstance().getmDevice().createInsecureRfcommSocketToServiceRecord(BluetoothConnection.getInstance().getmDeviceUUID()));
                BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                BluetoothConnection.getInstance().getmBTSocket().connect();
            }
        } catch (IOException e) {
// Unable to connect to device
            e.printStackTrace();
            mConnectSuccessful = false;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (!mConnectSuccessful) {
            Toast.makeText(BluetoothConnection.getInstance().getActivtyContext(), "Could not connect to device. Is it a Serial device? Also check if the UUID is correct in the settings", Toast.LENGTH_LONG).show();
            ((Activity)BluetoothConnection.getInstance().getActivtyContext()).finish();

        } else {
            BluetoothConnection.getInstance().msg("Connected to device");
            BluetoothConnection.getInstance().setmIsBluetoothConnected(true);
            BluetoothConnection.getInstance().setmReadThread(new ReadInput()); // Kick off input reader

        }

        progressDialog.dismiss();
    }

}

class DisConnectBT extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... params) {

        if (BluetoothConnection.getInstance().getmReadThread() != null) {
            BluetoothConnection.getInstance().getmReadThread().stop();
            while (BluetoothConnection.getInstance().getmReadThread().isRunning())
                ; // Wait until it stops
            BluetoothConnection.getInstance().setmReadThread(null);

        }

        try {
            BluetoothConnection.getInstance().getmBTSocket().close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        BluetoothConnection.getInstance().setmIsBluetoothConnected(false);
        if (BluetoothConnection.getInstance().getmIsUserInitiatedDisconnect()) {
            ((Activity)BluetoothConnection.getInstance().getActivtyContext()).finish();
        }
    }

}


class ReadInput implements Runnable {

    private boolean bStop = false;
    private Thread t;

    String TAG = "ReadInput";

    public ReadInput() {
        t = new Thread(this, "Input Thread");
        t.start();
    }

    public boolean isRunning() {
        return t.isAlive();
    }

    @Override
    public void run() {
        InputStream inputStream;

        try {
            inputStream = BluetoothConnection.getInstance().getmBTSocket().getInputStream();
            while (!bStop) {
                byte[] buffer = new byte[256];
                if (inputStream.available() > 0) {
                    inputStream.read(buffer);
                    int i = 0;
                    /*
                     * This is needed because new String(buffer) is taking the entire buffer i.e. 256 chars on Android 2.3.4 http://stackoverflow.com/a/8843462/1287554
                     */
                    for (i = 0; i < buffer.length && buffer[i] != 0; i++) {
                    }
                    final String strInput = new String(buffer, 0, i);

//                    Log.e(TAG,"strInput: " + strInput);

                    if(ReadSensorsActivity.checkBox_Read != null)
                    {
                        if(ReadSensorsActivity.checkBox_Read.isChecked())
                        {
                            ReadSensorsActivity.textView_MessageRead.post(new Runnable() {
                                @Override
                                public void run() {

                                    ReadSensorsActivity.textView_MessageRead.setText(strInput);

                                    if(ReadSensorsActivity.checkBox_Scroll.isChecked())
                                    {
                                        ReadSensorsActivity.viewScroll.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                ReadSensorsActivity.viewScroll.fullScroll(View.FOCUS_DOWN);
                                            }
                                        });
                                    }

                                }
                            });
                        }
                    }


                    /*
                     * If checked then receive text, better design would probably be to stop thread if unchecked and free resources, but this is a quick fix
                     */



                }
                Thread.sleep(1000);
            }
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void stop() {
        bStop = true;
    }

}
