package com.example.roverprototype;


// Singleton class
public class RoverCommands {


    // brat
    private String command_BRAT_arrow_Z_LEFT;
    private String command_BRAT_arrow_Z_RIGHT;
    private String command_BRAT_arrow_X_FRONT;
    private String command_BRAT_arrow_X_BACK;
    private String command_BRAT_arrow_Y_UP;
    private String command_BRAT_arrow_Y_DOWN;

    // roti
    private String command_ROTI_arrow_FRONT;
    private String command_ROTI_arrow_BACK;
    private String command_ROTI_arrow_LEFT;
    private String command_ROTI_arrow_RIGHT;


    // private instance, so that it can be
    // accessed by only by getInstance() method
    private static RoverCommands instance;

    RoverCommands()
    {
        // brat
        command_BRAT_arrow_Z_LEFT ="bLEFT16";
        command_BRAT_arrow_Z_RIGHT ="bRIGHT16";
        command_BRAT_arrow_X_FRONT ="bFRONT16";
        command_BRAT_arrow_X_BACK ="bBACK16";
        command_BRAT_arrow_Y_UP ="bUP16";
        command_BRAT_arrow_Y_DOWN ="bDOWN16";

        // roti
        command_ROTI_arrow_FRONT ="rFRONT32";
        command_ROTI_arrow_BACK ="rBACK32";
        command_ROTI_arrow_LEFT ="rLEFT32";
        command_ROTI_arrow_RIGHT ="rRIGHT32";
    }

    //synchronized method to control simultaneous access
    synchronized public static RoverCommands getInstance()
    {
        if (instance == null)
        {
            // if instance is null, initialize
            instance = new RoverCommands();
        }
        return instance;
    }


    public String getCommand_BRAT_arrow_Z_LEFT() {
        return command_BRAT_arrow_Z_LEFT;
    }

    public void setCommand_BRAT_arrow_Z_LEFT(String command_BRAT_arrow_Z_LEFT) {
        this.command_BRAT_arrow_Z_LEFT = command_BRAT_arrow_Z_LEFT;
    }

    public String getCommand_BRAT_arrow_Z_RIGHT() {
        return command_BRAT_arrow_Z_RIGHT;
    }

    public void setCommand_BRAT_arrow_Z_RIGHT(String command_BRAT_arrow_Z_RIGHT) {
        this.command_BRAT_arrow_Z_RIGHT = command_BRAT_arrow_Z_RIGHT;
    }

    public String getCommand_BRAT_arrow_X_FRONT() {
        return command_BRAT_arrow_X_FRONT;
    }

    public void setCommand_BRAT_arrow_X_FRONT(String command_BRAT_arrow_X_FRONT) {
        this.command_BRAT_arrow_X_FRONT = command_BRAT_arrow_X_FRONT;
    }

    public String getCommand_BRAT_arrow_X_BACK() {
        return command_BRAT_arrow_X_BACK;
    }

    public void setCommand_BRAT_arrow_X_BACK(String command_BRAT_arrow_X_BACK) {
        this.command_BRAT_arrow_X_BACK = command_BRAT_arrow_X_BACK;
    }

    public String getCommand_BRAT_arrow_Y_UP() {
        return command_BRAT_arrow_Y_UP;
    }

    public void setCommand_BRAT_arrow_Y_UP(String command_BRAT_arrow_Y_UP) {
        this.command_BRAT_arrow_Y_UP = command_BRAT_arrow_Y_UP;
    }

    public String getCommand_BRAT_arrow_Y_DOWN() {
        return command_BRAT_arrow_Y_DOWN;
    }

    public void setCommand_BRAT_arrow_Y_DOWN(String command_BRAT_arrow_Y_DOWN) {
        this.command_BRAT_arrow_Y_DOWN = command_BRAT_arrow_Y_DOWN;
    }

    public String getCommand_ROTI_arrow_FRONT() {
        return command_ROTI_arrow_FRONT;
    }

    public void setCommand_ROTI_arrow_FRONT(String command_ROTI_arrow_FRONT) {
        this.command_ROTI_arrow_FRONT = command_ROTI_arrow_FRONT;
    }

    public String getCommand_ROTI_arrow_BACK() {
        return command_ROTI_arrow_BACK;
    }

    public void setCommand_ROTI_arrow_BACK(String command_ROTI_arrow_BACK) {
        this.command_ROTI_arrow_BACK = command_ROTI_arrow_BACK;
    }

    public String getCommand_ROTI_arrow_LEFT() {
        return command_ROTI_arrow_LEFT;
    }

    public void setCommand_ROTI_arrow_LEFT(String command_ROTI_arrow_LEFT) {
        this.command_ROTI_arrow_LEFT = command_ROTI_arrow_LEFT;
    }

    public String getCommand_ROTI_arrow_RIGHT() {
        return command_ROTI_arrow_RIGHT;
    }

    public void setCommand_ROTI_arrow_RIGHT(String command_ROTI_arrow_RIGHT) {
        this.command_ROTI_arrow_RIGHT = command_ROTI_arrow_RIGHT;
    }
}
