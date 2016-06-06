package com.apeer001.app.icalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Double left = 0.0;
    Double right = 0.0;
    EditText resText;
    private String lastInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resText = (EditText) findViewById(R.id.ResultText);

    }


    public void onClick(View view) {
        Button b = (Button) view;
        String val = b.getText().toString();
        StringBuilder res = new StringBuilder(resText.getText().toString());
        if (!val.equals("+") && !val.equals("-") && !val.equals("/") && !val.equals("x")) {
            if (val.equals(".") && !res.toString().contains(".")) {
                val = val.concat("0");
                res = res.append(val);
                lastInput = val;
            } else if (res.toString().equals("0") && !val.equals(".")) {
                res = new StringBuilder(val);
                lastInput = val;
            } else {
                if (lastInput.equals(".")) {
                    res.setCharAt(res.length()-1, val.charAt(0));
                    lastInput = val;
                } else {
                    res = res.append(val);
                    lastInput = val;
                }
            }
        }
        // Update text in text field
        resText.setText(res.toString());
    }
}
