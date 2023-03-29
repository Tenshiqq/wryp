package com.example.wryp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.example.wryp.db.Constants;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    MultiAutoCompleteTextView inputText;
    Button inpButton;
    TextView textOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.inputText);
        inpButton = findViewById(R.id.inpButton);
        textOutput = findViewById(R.id.textOutput);


        inpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textOutput.append(Constants.CREATE_TABLE_NOTES + "\n");
                textOutput.append(Constants.DROP_TABLE_NOTES + "\n");
                textOutput.append(Constants.CREATE_TABLE_TAGS + "\n");
                textOutput.append(Constants.DROP_TABLE_TAGS + "\n");
            }
        });
    }



}

