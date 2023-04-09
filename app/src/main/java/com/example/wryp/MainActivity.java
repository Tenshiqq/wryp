package com.example.wryp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.wryp.db.Constants;
import com.example.wryp.db.DBManager;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Context context;
    private DBManager dbManager;

    private MultiAutoCompleteTextView inputText;
    private Button inpButton;
    private Button dropTable;
    private TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        inputText = findViewById(R.id.inputText);
        inpButton = findViewById(R.id.inpButton);
        dropTable = findViewById(R.id.dropTableTags);
        textOutput = findViewById(R.id.textOutput);

        dbManager = new DBManager(this, Constants.TABLE_NAME_TAGS);
        Log.d(Constants.LOG_TAG, "onCreate: dbManager created");

        inpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommandLine cl = new CommandLine(context, inputText.getText().toString());

                textOutput.setText("");
                for (String s : dbManager.getFromDB()) {
                    textOutput.append(s + "\n");
                }
            }
        });

        dropTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbManager.dropTable();
                textOutput.setText("");
            }
        });

        Log.d(Constants.LOG_TAG, "onCreate: zhivem");
    }

    @Override
    protected void onResume() {
        super.onResume();

        dbManager.openDB();
        textOutput.setText("");

        for (String s : dbManager.getFromDB()) {
            textOutput.append(s + "\n");
        }
        Log.d(Constants.LOG_TAG, "onResume: umerli");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbManager.closeDB();
    }
}

