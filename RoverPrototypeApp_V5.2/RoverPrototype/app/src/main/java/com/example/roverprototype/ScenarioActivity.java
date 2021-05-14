package com.example.roverprototype;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class ScenarioActivity extends AppCompatActivity {

    //Bluetooth
    private static final String TAG = "ScenarioActivity";


    ImageView scenario1_OFF;
    ImageView scenario2_OFF;
    ImageView scenario3_OFF;
    ImageView scenario4_OFF;

    ImageView scenario1_ON;
    ImageView scenario2_ON;
    ImageView scenario3_ON;
    ImageView scenario4_ON;

    TextView textView_Scenario1;
    TextView textView_Scenario2;
    TextView textView_Scenario3;
    TextView textView_Scenario4;

    ImageView deleteScenario1;
    ImageView deleteScenario2;
    ImageView deleteScenario3;
    ImageView deleteScenario4;

    ImageView btn_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario);

        btn_back = findViewById(R.id.imageView_back);

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

        textView_Scenario1 = findViewById(R.id.textView_Scenario1);
        textView_Scenario2 = findViewById(R.id.textView_Scenario2);
        textView_Scenario3 = findViewById(R.id.textView_Scenario3);
        textView_Scenario4 = findViewById(R.id.textView_Scenario4);

        deleteScenario1 = findViewById(R.id.btn_deleteScenario1);
        deleteScenario2 = findViewById(R.id.btn_deleteScenario2);
        deleteScenario3 = findViewById(R.id.btn_deleteScenario3);
        deleteScenario4 = findViewById(R.id.btn_deleteScenario4);

        deleteScenario1.setVisibility(View.INVISIBLE);
        deleteScenario2.setVisibility(View.INVISIBLE);
        deleteScenario3.setVisibility(View.INVISIBLE);
        deleteScenario4.setVisibility(View.INVISIBLE);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        // Aici verific daca exista sau nu fisierele cu scenarii
        checkScenarios();

        scenario1_OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeScenarioActivity.class);
                intent.putExtra("FileName","Scenariu_1");
                startActivity(intent);
                finish();
            }
        });

        scenario2_OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeScenarioActivity.class);
                intent.putExtra("FileName","Scenariu_2");
                startActivity(intent);
                finish();
            }
        });

        scenario3_OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeScenarioActivity.class);
                intent.putExtra("FileName","Scenariu_3");
                startActivity(intent);
                finish();
            }
        });

        scenario4_OFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MakeScenarioActivity.class);
                intent.putExtra("FileName","Scenariu_4");
                startActivity(intent);
                finish();
            }
        });

        scenario1_ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FILE_NAME = "Scenariu_1";

                // Comanda de LOAD ; citire din fisier
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine()) != null)
                    {
                        sb.append(text).append("\n");

                        int time = setAwaitTime(text);

                        if (BluetoothConnection.getInstance().getmBTSocket() != null && time != 0)
                        {
                            try
                            {
                                BluetoothConnection.getInstance().getmBTSocket().getOutputStream().write(text.getBytes());
                                Log.e(TAG,"Citeste din fisierul " + FILE_NAME + "   Send to Arduino : " + text);
                                Thread.sleep(time);
                            }
                            catch (IOException e)
                            {
                                msg("Error");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    Log.e(TAG,sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if(fis!= null)
                    {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        scenario2_ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FILE_NAME = "Scenariu_1";

                // Comanda de LOAD ; citire din fisier
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine()) != null)
                    {
                        sb.append(text).append("\n");

                        int time = setAwaitTime(text);

                        if (BluetoothConnection.getInstance().getmBTSocket() != null && time != 0)
                        {
                            try
                            {
                                BluetoothConnection.getInstance().getmBTSocket().getOutputStream().write(text.getBytes());
                                Log.e(TAG,"Citeste din fisierul " + FILE_NAME + "   Send to Arduino : " + text);
                                Thread.sleep(time);
                            }
                            catch (IOException e)
                            {
                                msg("Error");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    Log.e(TAG,sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if(fis!= null)
                    {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        scenario3_ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FILE_NAME = "Scenariu_1";

                // Comanda de LOAD ; citire din fisier
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine()) != null)
                    {
                        sb.append(text).append("\n");

                        int time = setAwaitTime(text);

                        if (BluetoothConnection.getInstance().getmBTSocket() != null && time != 0)
                        {
                            try
                            {
                                BluetoothConnection.getInstance().getmBTSocket().getOutputStream().write(text.getBytes());
                                Log.e(TAG,"Citeste din fisierul " + FILE_NAME + "   Send to Arduino : " + text);
                                Thread.sleep(time);
                            }
                            catch (IOException e)
                            {
                                msg("Error");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    Log.e(TAG,sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if(fis!= null)
                    {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });

        scenario4_ON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FILE_NAME = "Scenariu_1";

                // Comanda de LOAD ; citire din fisier
                FileInputStream fis = null;

                try {
                    fis = openFileInput(FILE_NAME);
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuilder sb = new StringBuilder();
                    String text;

                    while((text = br.readLine()) != null)
                    {
                        sb.append(text).append("\n");

                        int time = setAwaitTime(text);

                        if (BluetoothConnection.getInstance().getmBTSocket() != null && time != 0)
                        {
                            try
                            {
                                BluetoothConnection.getInstance().getmBTSocket().getOutputStream().write(text.getBytes());
                                Log.e(TAG,"Citeste din fisierul " + FILE_NAME + "   Send to Arduino : " + text);
                                Thread.sleep(time);
                            }
                            catch (IOException e)
                            {
                                msg("Error");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    Log.e(TAG,sb.toString());

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {

                    if(fis!= null)
                    {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        });


        deleteScenario1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String File_Name = "Scenariu_1";

                File dir = getFilesDir();
                File file = new File(dir, File_Name);
                file.delete();
                deleteScenario1.setVisibility(View.INVISIBLE);
                scenario1_ON.setVisibility(View.INVISIBLE);
                scenario1_OFF.setVisibility(View.VISIBLE);
            }
        });

        deleteScenario2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String File_Name = "Scenariu_2";

                File dir = getFilesDir();
                File file = new File(dir, File_Name);
                file.delete();
                deleteScenario2.setVisibility(View.INVISIBLE);
                scenario2_ON.setVisibility(View.INVISIBLE);
                scenario2_OFF.setVisibility(View.VISIBLE);
            }
        });

        deleteScenario3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String File_Name = "Scenariu_3";

                File dir = getFilesDir();
                File file = new File(dir, File_Name);
                file.delete();
                deleteScenario3.setVisibility(View.INVISIBLE);
                scenario3_ON.setVisibility(View.INVISIBLE);
                scenario3_OFF.setVisibility(View.VISIBLE);
            }
        });

        deleteScenario4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String File_Name = "Scenariu_4";

                File dir = getFilesDir();
                File file = new File(dir, File_Name);
                file.delete();
                deleteScenario4.setVisibility(View.INVISIBLE);
                scenario4_ON.setVisibility(View.INVISIBLE);
                scenario4_OFF.setVisibility(View.VISIBLE);
            }
        });


    }

    public void checkScenarios()
    {
        String File_Name = "Scenariu_1";

        File dir = getFilesDir();
        File file = new File(dir, File_Name);
        if(file.exists())
        {
            scenario1_ON.setVisibility(View.VISIBLE);
            scenario1_OFF.setVisibility(View.INVISIBLE);
            deleteScenario1.setVisibility(View.VISIBLE);
            textView_Scenario1.setText(File_Name);
        }

        File_Name = "Scenariu_2";

        dir = getFilesDir();
        file = new File(dir, File_Name);
        if(file.exists())
        {

            scenario2_ON.setVisibility(View.VISIBLE);
            scenario2_OFF.setVisibility(View.INVISIBLE);
            deleteScenario2.setVisibility(View.VISIBLE);
            textView_Scenario2.setText(File_Name);
        }

        File_Name = "Scenariu_3";

        dir = getFilesDir();
        file = new File(dir, File_Name);
        if(file.exists())
        {
            scenario3_ON.setVisibility(View.VISIBLE);
            scenario3_OFF.setVisibility(View.INVISIBLE);
            deleteScenario3.setVisibility(View.VISIBLE);
            textView_Scenario3.setText(File_Name);
        }

        File_Name = "Scenariu_4";

        dir = getFilesDir();
        file = new File(dir, File_Name);
        if(file.exists())
        {
            scenario4_ON.setVisibility(View.VISIBLE);
            scenario4_OFF.setVisibility(View.INVISIBLE);
            deleteScenario4.setVisibility(View.VISIBLE);
            textView_Scenario4.setText(File_Name);
        }


    }




    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    private int setAwaitTime(String text)
    {
        // BRAT S*1
        if (text.equals("bUP1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bDOWN1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bFRONT1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bBACK1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bRIGHT1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bLEFT1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bCLAW"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bENon"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("bENoff"))
        {
            return 1000; // se seteaza 1s
        }


        // BRAT S*16
        if (text.equals("bUP16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("bDOWN16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("bFRONT16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("bBACK16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("bRIGHT16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("bLEFT16"))
        {
            return 2500; // se seteaza 2,5s
        }


        // BRAT S*32
        if (text.equals("bUP32"))
        {
            return 4200; // se seteaza 4,2s
        }
        else if (text.equals("bDOWN32"))
        {
            return 4200; // se seteaza 4,2s
        }
        else if (text.equals("bFRONT32"))
        {
            return 4200; // se seteaza 4,2s
        }
        else if (text.equals("bBACK32"))
        {
            return 4200; // se seteaza 4,2s
        }
        else if (text.equals("bRIGHT32"))
        {
            return 4500; // se seteaza 4,5s
        }
        else if (text.equals("bLEFT32"))
        {
            return 4500; // se seteaza 4,5s
        }

        // ROTI S*1
        if (text.equals("rFRONT1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("rBACK1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("rRIGHT1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("rLEFT1"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("rENon"))
        {
            return 1000; // se seteaza 1s
        }
        else if (text.equals("rENoff"))
        {
            return 1000; // se seteaza 1s
        }

        // ROTI S*16
        if (text.equals("rFRONT16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("rBACK16"))
        {
            return 2500; // se seteaza 2,5s
        }
        else if (text.equals("rRIGHT16"))
        {
            return 4500; // se seteaza 4,5s
        }
        else if (text.equals("rLEFT16"))
        {
            return 4500; // se seteaza 4,5s
        }

        // ROTI S*32
        if (text.equals("rFRONT32"))
        {
            return 5000; // se seteaza 5s
        }
        else if (text.equals("rBACK32"))
        {
            return 5000; // se seteaza 5s
        }
        else if (text.equals("rRIGHT32"))
        {
            return 8500; // se seteaza 8,5s
        }
        else if (text.equals("rLEFT32"))
        {
            return 8500; // se seteaza 8,5s
        }


        return 0;

    }

//    @Override
//    protected void onPause() {
//        if (BluetoothConnection.getInstance().getmBTSocket() != null && BluetoothConnection.getInstance().getmIsBluetoothConnected()) {
//            new DisConnectBT().execute();
//        }
//        Log.d(TAG, "Paused");
//        super.onPause();
//    }

//    @Override
//    protected void onResume() {
//        if (BluetoothConnection.getInstance().getmBTSocket() == null || !BluetoothConnection.getInstance().getmIsBluetoothConnected()) {
//            new ConnectBT().execute();
//        }
//        Log.d(TAG, "Resumed");
//        super.onResume();
//    }

    @Override
    protected void onStop() {
        Log.d(TAG, "Stopped");
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
// TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }




}