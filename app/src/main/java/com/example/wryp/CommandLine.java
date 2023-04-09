package com.example.wryp;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.wryp.db.Constants;
import com.example.wryp.db.DBManager;

import java.util.Arrays;

public class CommandLine {
    String[] commandsConst = {"/np", "/ep", "/sp", "/dp", "yestday", "today", "nday", "nmon", "ntue", "nwed", "nthu", "nfri", "nsat", "nsun", "nweek", "nmonth"};
    private Context context;
    private String[] commandsArray;
    private String command;
    private String date;
    private String textTag;
    private String desc;
    private int intTag;


    public CommandLine(Context context, String input) {
        DBManager dbManager = new DBManager(context, Constants.TABLE_NAME_TAGS);
        this.context = context;
        this.commandsArray = input.split(" ");
        Log.d(Constants.LOG_TAG, "CommandLine: " + Arrays.toString(commandsArray));
        this.command = commandsArray[0];
        this.date = commandsArray[1];
        this.textTag = commandsArray[2];
        this.desc = commandsArray[3];
        this.intTag = dbManager.convertTag(textTag);
        Log.d(Constants.LOG_TAG, "CommandLine: " + intTag);
        Log.d(Constants.LOG_TAG, Arrays.toString(commandsArray));

        if (command.equals("/np")) {
            dbManager.insertToDB(date, intTag, desc);
        }
    }

}
