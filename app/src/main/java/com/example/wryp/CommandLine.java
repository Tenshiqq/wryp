package com.example.wryp;

import android.util.Log;
import android.widget.ArrayAdapter;

public class CommandLine {

    String[] commandsConst = {"/np", "/ep", "/sp", "/dp", "yestday", "today", "nday", "nmon", "ntue", "nwed", "nthu", "nfri", "nsat", "nsun", "nweek", "nmonth"};

    public static void CMStart(String s){
        String[] commands = s.split(" ");
        if (commands[0].equals("/np")){
            NewPlan np = new NewPlan(commands[1], commands[2], commands[3]);
            Log.d("TENSHI", "CMStart: " + np);
        }
    }

}
