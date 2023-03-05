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


        String[] commands = {"/np", "/ep", "/sp", "/dp", "nday", "nmonday", "ntuesday", "nwednesday", "nthursday", "nfriday", "nsaturday", "nsunday", "nweek", "nmonth"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, commands);
        inputText.setAdapter(adapter);
        inputText.setTokenizer(new SpaceTokenizer());

        inpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sInput;
                sInput = inputText.getText().toString();
                String[] commands;
                commands = sInput.split(" ");
                if (commands[0].equals("/np")){
                    textOutput.setText("create new plan");
                }
            }
        });

    }

}
class SpaceTokenizer implements MultiAutoCompleteTextView.Tokenizer {
    private int i;

    // Returns the start of the token that ends at offset cursor within text.
    public int findTokenStart(CharSequence inputText, int cursor) {
        int idx = cursor;

        while (idx > 0 && inputText.charAt(idx - 1) != ' ') {
            idx--;
        }
        while (idx < cursor && inputText.charAt(idx) == ' ') {
            idx++;
        }
        return idx;
    }

    // Returns the end of the token (minus trailing punctuation) that
    // begins at offset cursor within text.
    public int findTokenEnd(CharSequence inputText, int cursor) {
        int idx = cursor;
        int length = inputText.length();

        while (idx < length) {
            if (inputText.charAt(i) == ' ') {
                return idx;
            } else {
                idx++;
            }
        }
        return length;
    }

    // Returns text, modified, if necessary, to ensure that it ends with a token terminator
    // (for example a space or comma).
    public CharSequence terminateToken(CharSequence inputText) {
        int idx = inputText.length();

        while (idx > 0 && inputText.charAt(idx - 1) == ' ') {
            idx--;
        }

        if (idx > 0 && inputText.charAt(idx - 1) == ' ') {
            return inputText;
        } else {
            if (inputText instanceof Spanned) {
                SpannableString sp = new SpannableString(inputText + " ");
                TextUtils.copySpansFrom((Spanned) inputText, 0, inputText.length(),
                        Object.class, sp, 0);
                return sp;
            } else {
                return inputText + " ";
            }
        }
    }
}
