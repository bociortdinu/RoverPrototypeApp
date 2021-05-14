package com.example.roverprototype;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;


public class ConfigurationSettings extends AppCompatActivity {

    // Setari BRAT
    TextView textView_BRAT;

    TextView textView_AXA_X;
    TextView textView_AXA_Y;
    TextView textView_AXA_Z;

    RadioGroup radioGroup_BRAT_Axa_X;
    RadioButton radioButton_BRAT_Axa_X;
    RadioGroup radioGroup_BRAT_Axa_Y;
    RadioButton radioButton_BRAT_Axa_Y;
    RadioGroup radioGroup_BRAT_Axa_Z;
    RadioButton radioButton_BRAT_Axa_Z;

    // Setari ROTI
    TextView textView_ROTI;

    TextView textView_FRONT;
    TextView textView_BACK;
    TextView textView_LeftAndRight;

    RadioGroup radioGroup_ROTI_Front;
    RadioButton radioButton_ROTI_Front;
    RadioGroup radioGroup_ROTI_Back;
    RadioButton radioButton_ROTI_Back;
    RadioGroup radioGroup_ROTI_LeftAndRight;
    RadioButton radioButton_ROTI_LeftAndRight;


    // Switch intre Roti si Brat
    Switch switch_roti_brat_Settings;

    // Button Apply All Settings
    Button buttonApplySettings;

    // Button Close settings
    ImageView closeSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration_settings);

        buttonApplySettings = findViewById(R.id.buttonApplySettings);
        closeSettings = findViewById(R.id.close_settings);

        // Setari BRAT
        textView_BRAT = findViewById(R.id.textView_BRAT);

        textView_AXA_X = findViewById(R.id.textView_AXA_X);
        textView_AXA_Y = findViewById(R.id.textView_AXA_Y);
        textView_AXA_Z = findViewById(R.id.textView_AXA_Z);

        radioGroup_BRAT_Axa_X = findViewById(R.id.radioGroup_Axa_X);
        radioGroup_BRAT_Axa_Y = findViewById(R.id.radioGroup_Axa_Y);
        radioGroup_BRAT_Axa_Z = findViewById(R.id.radioGroup_Axa_Z);

        // Setari ROTI
        textView_ROTI = findViewById(R.id.textView_ROTI);

        textView_FRONT = findViewById(R.id.textView_FRONT);
        textView_BACK = findViewById(R.id.textView_BACK);
        textView_LeftAndRight = findViewById(R.id.textView_LeftAndRight);

        radioGroup_ROTI_Front = findViewById(R.id.radioGroup_Front);
        radioGroup_ROTI_Back = findViewById(R.id.radioGroup_Back);
        radioGroup_ROTI_LeftAndRight = findViewById(R.id.radioGroup_LeftAndRight);

        // Switch intre Roti si Brat
        switch_roti_brat_Settings = findViewById(R.id.switch_roti_brat_Settings);


        // Initializare pentru a incepe in modul Roti
        textView_BRAT.setVisibility(View.INVISIBLE);

        textView_AXA_X.setVisibility(View.INVISIBLE);
        textView_AXA_Y.setVisibility(View.INVISIBLE);
        textView_AXA_Z.setVisibility(View.INVISIBLE);

        radioGroup_BRAT_Axa_X.setVisibility(View.INVISIBLE);
        radioGroup_BRAT_Axa_Y.setVisibility(View.INVISIBLE);
        radioGroup_BRAT_Axa_Z.setVisibility(View.INVISIBLE);


        // brat  -  comenzi din clasa RoverCommands care sunt atribuite in construcotr, adica sunt prestabilite la pornire
//        command_BRAT_arrow_Z_LEFT ="bLEFT32";
//        command_BRAT_arrow_Z_RIGHT ="bRIGHT32";
//        command_BRAT_arrow_X_FRONT ="bFRONT32";
//        command_BRAT_arrow_X_BACK ="bBACK32";
//        command_BRAT_arrow_Y_UP ="bUP32";
//        command_BRAT_arrow_Y_DOWN ="bDOWN32";

        // Setari initiale - prestabilite      -  aici practic vad ce comenzi am in clasa RoverComands si fac check pe radioButton-ul corespunzator
            // BRAT X
        if(RoverCommands.getInstance().getCommand_BRAT_arrow_X_FRONT().equals("bFRONT32") && RoverCommands.getInstance().getCommand_BRAT_arrow_X_BACK().equals("bBACK32"))
        {
            radioGroup_BRAT_Axa_X.check(R.id.radioButton_Fullstep_X);

        } else if(RoverCommands.getInstance().getCommand_BRAT_arrow_X_FRONT().equals("bFRONT16") && RoverCommands.getInstance().getCommand_BRAT_arrow_X_BACK().equals("bBACK16"))
        {
            radioGroup_BRAT_Axa_X.check(R.id.radioButton_Microstep_X);

        }else if(RoverCommands.getInstance().getCommand_BRAT_arrow_X_FRONT().equals("bFRONT1") && RoverCommands.getInstance().getCommand_BRAT_arrow_X_BACK().equals("bBACK1"))
        {
            radioGroup_BRAT_Axa_X.check(R.id.radioButton_Picostep_X);
        }

            // BRAT Y
        if(RoverCommands.getInstance().getCommand_BRAT_arrow_Y_UP().equals("bUP32") && RoverCommands.getInstance().getCommand_BRAT_arrow_Y_DOWN().equals("bDOWN32"))
        {
            radioGroup_BRAT_Axa_Y.check(R.id.radioButton_Fullstep_Y);

        } else if(RoverCommands.getInstance().getCommand_BRAT_arrow_Y_UP().equals("bUP16") && RoverCommands.getInstance().getCommand_BRAT_arrow_Y_DOWN().equals("bDOWN16"))
        {
            radioGroup_BRAT_Axa_Y.check(R.id.radioButton_Microstep_Y);

        }else if(RoverCommands.getInstance().getCommand_BRAT_arrow_Y_UP().equals("bUP1") && RoverCommands.getInstance().getCommand_BRAT_arrow_Y_DOWN().equals("bDOWN1"))
        {
            radioGroup_BRAT_Axa_Y.check(R.id.radioButton_Picostep_Y);
        }

        // BRAT Z
        if(RoverCommands.getInstance().getCommand_BRAT_arrow_Z_LEFT().equals("bLEFT32") && RoverCommands.getInstance().getCommand_BRAT_arrow_Z_RIGHT().equals("bRIGHT32"))
        {
            radioGroup_BRAT_Axa_Z.check(R.id.radioButton_Fullstep_Z);

        } else if(RoverCommands.getInstance().getCommand_BRAT_arrow_Z_LEFT().equals("bLEFT16") && RoverCommands.getInstance().getCommand_BRAT_arrow_Z_RIGHT().equals("bRIGHT16"))
        {
            radioGroup_BRAT_Axa_Z.check(R.id.radioButton_Microstep_Z);

        }else if(RoverCommands.getInstance().getCommand_BRAT_arrow_Z_LEFT().equals("bLEFT1") && RoverCommands.getInstance().getCommand_BRAT_arrow_Z_RIGHT().equals("bRIGHT1"))
        {
            radioGroup_BRAT_Axa_Z.check(R.id.radioButton_Picostep_Z);
        }


        // roti  -  comenzi din clasa RoverCommands care sunt atribuite in construcotr, adica sunt prestabilite la pornire
//        command_ROTI_arrow_FRONT ="rFRONT32";
//        command_ROTI_arrow_BACK ="rBACK32";
//        command_ROTI_arrow_LEFT ="rLEFT32";
//        command_ROTI_arrow_RIGHT ="rRIGHT32";

        // ROTI Front
        if(RoverCommands.getInstance().getCommand_ROTI_arrow_FRONT().equals("rFRONT32"))
        {
            radioGroup_ROTI_Front.check(R.id.radioButton_Fullstep_FRONT);

        } else if(RoverCommands.getInstance().getCommand_ROTI_arrow_FRONT().equals("rFRONT16"))
        {
            radioGroup_ROTI_Front.check(R.id.radioButton_Microstep_FRONT);

        }else if(RoverCommands.getInstance().getCommand_ROTI_arrow_FRONT().equals("rFRONT1"))
        {
            radioGroup_ROTI_Front.check(R.id.radioButton_Picostep_FRONT);
        }

        // ROTI Back
        if(RoverCommands.getInstance().getCommand_ROTI_arrow_BACK().equals("rBACK32"))
        {
            radioGroup_ROTI_Back.check(R.id.radioButton_Fullstep_BACK);

        } else if(RoverCommands.getInstance().getCommand_ROTI_arrow_BACK().equals("rBACK16"))
        {
            radioGroup_ROTI_Back.check(R.id.radioButton_Microstep_BACK);

        }else if(RoverCommands.getInstance().getCommand_ROTI_arrow_BACK().equals("rBACK1"))
        {
            radioGroup_ROTI_Back.check(R.id.radioButton_Picostep_BACK);
        }

        // ROTI LeftAndRight
        if(RoverCommands.getInstance().getCommand_ROTI_arrow_LEFT().equals("rLEFT32") && RoverCommands.getInstance().getCommand_ROTI_arrow_RIGHT().equals("rRIGHT32"))
        {
            radioGroup_ROTI_LeftAndRight.check(R.id.radioButton_Fullstep_LeftAndRight);

        } else if(RoverCommands.getInstance().getCommand_ROTI_arrow_LEFT().equals("rLEFT16") && RoverCommands.getInstance().getCommand_ROTI_arrow_RIGHT().equals("rRIGHT16"))
        {
            radioGroup_ROTI_LeftAndRight.check(R.id.radioButton_Microstep_LeftAndRight);

        }else if(RoverCommands.getInstance().getCommand_ROTI_arrow_LEFT().equals("rLEFT1") && RoverCommands.getInstance().getCommand_ROTI_arrow_RIGHT().equals("rRIGHT1"))
        {
            radioGroup_ROTI_LeftAndRight.check(R.id.radioButton_Picostep_LeftAndRight);
        }



        switch_roti_brat_Settings.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) // BRAT ON
                {
                    textView_ROTI.setVisibility(View.INVISIBLE);
                    textView_FRONT.setVisibility(View.INVISIBLE);
                    textView_BACK.setVisibility(View.INVISIBLE);
                    textView_LeftAndRight.setVisibility(View.INVISIBLE);
                    radioGroup_ROTI_Front.setVisibility(View.INVISIBLE);
                    radioGroup_ROTI_Back.setVisibility(View.INVISIBLE);
                    radioGroup_ROTI_LeftAndRight.setVisibility(View.INVISIBLE);

                    textView_BRAT.setVisibility(View.VISIBLE);
                    textView_AXA_X.setVisibility(View.VISIBLE);
                    textView_AXA_Y.setVisibility(View.VISIBLE);
                    textView_AXA_Z.setVisibility(View.VISIBLE);
                    radioGroup_BRAT_Axa_X.setVisibility(View.VISIBLE);
                    radioGroup_BRAT_Axa_Y.setVisibility(View.VISIBLE);
                    radioGroup_BRAT_Axa_Z.setVisibility(View.VISIBLE);



                } else { // ROTI ON

                    textView_BRAT.setVisibility(View.INVISIBLE);
                    textView_AXA_X.setVisibility(View.INVISIBLE);
                    textView_AXA_Y.setVisibility(View.INVISIBLE);
                    textView_AXA_Z.setVisibility(View.INVISIBLE);
                    radioGroup_BRAT_Axa_X.setVisibility(View.INVISIBLE);
                    radioGroup_BRAT_Axa_Y.setVisibility(View.INVISIBLE);
                    radioGroup_BRAT_Axa_Z.setVisibility(View.INVISIBLE);

                    textView_ROTI.setVisibility(View.VISIBLE);
                    textView_FRONT.setVisibility(View.VISIBLE);
                    textView_BACK.setVisibility(View.VISIBLE);
                    textView_LeftAndRight.setVisibility(View.VISIBLE);
                    radioGroup_ROTI_Front.setVisibility(View.VISIBLE);
                    radioGroup_ROTI_Back.setVisibility(View.VISIBLE);
                    radioGroup_ROTI_LeftAndRight.setVisibility(View.VISIBLE);

                }
            }
        });


        buttonApplySettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("ConfigurationSettings","buttonApplySettings : onClick START");
                int radio_X_ID = radioGroup_BRAT_Axa_X.getCheckedRadioButtonId();
                radioButton_BRAT_Axa_X = findViewById(radio_X_ID);
                int radio_Y_ID = radioGroup_BRAT_Axa_Y.getCheckedRadioButtonId();
                radioButton_BRAT_Axa_Y = findViewById(radio_Y_ID);
                int radio_Z_ID = radioGroup_BRAT_Axa_Z.getCheckedRadioButtonId();
                radioButton_BRAT_Axa_Z = findViewById(radio_Z_ID);

                int radio_FRONT_ID = radioGroup_ROTI_Front.getCheckedRadioButtonId();
                radioButton_ROTI_Front = findViewById(radio_FRONT_ID);

                int radio_BACK_ID = radioGroup_ROTI_Back.getCheckedRadioButtonId();
                radioButton_ROTI_Back = findViewById(radio_BACK_ID);

                int radio_LeftAndRight_ID = radioGroup_ROTI_LeftAndRight.getCheckedRadioButtonId();
                radioButton_ROTI_LeftAndRight = findViewById(radio_LeftAndRight_ID);



//                radioButton_BRAT_Axa_X.getText();

//                // brat
//                command_BRAT_arrow_Z_LEFT ="bLEFT32";
//                command_BRAT_arrow_Z_RIGHT ="bRIGHT32";
//                command_BRAT_arrow_X_FRONT ="bFRONT32";
//                command_BRAT_arrow_X_BACK ="bBACK32";
//                command_BRAT_arrow_Y_UP ="bUP32";
//                command_BRAT_arrow_Y_DOWN ="bDOWN32";

                // Setam modul de lucru prin tipul de comanda pe care o dam
                    // BRAT X

                if(radioButton_BRAT_Axa_X.getText().equals("Fullstep s*32"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_X  - Fullstep s*32 TEXT = " + radioButton_BRAT_Axa_X.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_X_FRONT("bFRONT32");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_X_BACK("bBACK32");

                }else if(radioButton_BRAT_Axa_X.getText().equals("Microstep s*16"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_X  - Microstep s*16 TEXT = " + radioButton_BRAT_Axa_X.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_X_FRONT("bFRONT16");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_X_BACK("bBACK16");

                }else if(radioButton_BRAT_Axa_X.getText().equals("Picostep s*1"))
                {

                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_X  - Picostep s*1 TEXT = " + radioButton_BRAT_Axa_X.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_X_FRONT("bFRONT1");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_X_BACK("bBACK1");

                }

                // BRAT Y

                if(radioButton_BRAT_Axa_Y.getText().equals("Fullstep s*32"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_Y  - Fullstep s*32 TEXT = " + radioButton_BRAT_Axa_Y.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_Y_UP("bUP32");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_Y_DOWN("bDOWN32");

                }else if(radioButton_BRAT_Axa_Y.getText().equals("Microstep s*16"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_Y  - Microstep s*16 TEXT = " + radioButton_BRAT_Axa_Y.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_Y_UP("bUP16");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_Y_DOWN("bDOWN16");

                }else if(radioButton_BRAT_Axa_Y.getText().equals("Picostep s*1"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_Y  - Picostep s*1 TEXT = " + radioButton_BRAT_Axa_Y.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_Y_UP("bUP1");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_Y_DOWN("bDOWN1");

                }

                // BRAT Z
                if(radioButton_BRAT_Axa_Z.getText().equals("Fullstep s*32"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_Z  - Fullstep s*32 TEXT = " + radioButton_BRAT_Axa_Z.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_Z_LEFT("bLEFT32");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_Z_RIGHT("bRIGHT32");

                }else if(radioButton_BRAT_Axa_Z.getText().equals("Microstep s*16"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_Z  - Microstep s*16 TEXT = " + radioButton_BRAT_Axa_Z.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_Z_LEFT("bLEFT16");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_Z_RIGHT("bRIGHT16");

                }else if(radioButton_BRAT_Axa_Z.getText().equals("Picostep s*1"))
                {
                    Log.e("buttonApplySettings","radioButton_BRAT_Axa_Z  - Picostep s*1 TEXT = " + radioButton_BRAT_Axa_Z.getText());

                    RoverCommands.getInstance().setCommand_BRAT_arrow_Z_LEFT("bLEFT1");
                    RoverCommands.getInstance().setCommand_BRAT_arrow_Z_RIGHT("bRIGHT1");

                }



                // roti
//                command_ROTI_arrow_FRONT ="rFRONT32";
//                command_ROTI_arrow_BACK ="rBACK32";
//                command_ROTI_arrow_LEFT ="rLEFT32";
//                command_ROTI_arrow_RIGHT ="rRIGHT32";

                // ROTI Front
                if(radioButton_ROTI_Front.getText().equals("Fullstep s*32"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_Front  - Fullstep s*32 TEXT = " + radioButton_ROTI_Front.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_FRONT("rFRONT32");

                }else if(radioButton_ROTI_Front.getText().equals("Microstep s*16"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_Front  - Microstep s*16 TEXT = " + radioButton_ROTI_Front.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_FRONT("rFRONT16");

                }else if(radioButton_ROTI_Front.getText().equals("Picostep s*1"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_Front  - Picostep s*1 TEXT = " + radioButton_ROTI_Front.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_FRONT("rFRONT1");

                }

                // ROTI Back
                if(radioButton_ROTI_Back.getText().equals("Fullstep s*32"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_Back  - Fullstep s*32 TEXT = " + radioButton_ROTI_Back.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_BACK("rBACK32");

                }else if(radioButton_ROTI_Back.getText().equals("Microstep s*16"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_Back  - Microstep s*16 TEXT = " + radioButton_ROTI_Back.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_BACK("rBACK16");

                }else if(radioButton_ROTI_Back.getText().equals("Picostep s*1"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_Back  - Picostep s*1 TEXT = " + radioButton_ROTI_Back.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_BACK("rBACK1");

                }

                // ROTI LeftAndRight
                if(radioButton_ROTI_LeftAndRight.getText().equals("Fullstep s*32"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_LeftAndRight  - Fullstep s*32 TEXT = " + radioButton_ROTI_LeftAndRight.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_LEFT("rLEFT32");
                    RoverCommands.getInstance().setCommand_ROTI_arrow_RIGHT("rRIGHT32");

                }else if(radioButton_ROTI_LeftAndRight.getText().equals("Microstep s*16"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_LeftAndRight  - Microstep s*16 TEXT = " + radioButton_ROTI_LeftAndRight.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_LEFT("rLEFT16");
                    RoverCommands.getInstance().setCommand_ROTI_arrow_RIGHT("rRIGHT16");

                }else if(radioButton_ROTI_LeftAndRight.getText().equals("Picostep s*1"))
                {
                    Log.e("buttonApplySettings","radioButton_ROTI_LeftAndRight  - Picostep s*1 TEXT = " + radioButton_ROTI_LeftAndRight.getText());

                    RoverCommands.getInstance().setCommand_ROTI_arrow_LEFT("rLEFT1");
                    RoverCommands.getInstance().setCommand_ROTI_arrow_RIGHT("rRIGHT1");

                }

                Log.e("ConfigurationSettings","buttonApplySettings : onClick FINISH");


            }
        });

        closeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}