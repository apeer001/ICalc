package com.apeer001.app.icalc;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Double left = 0.0;
    Double right = 0.0;
    EditText resText;
    private String lastInput = "";
    boolean firstButtonPress = true;

    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionbar = getSupportActionBar();
        try {
            actionbar.hide();
        } catch (NullPointerException n) {
            Log.d(TAG, n.getMessage());
        }
        resText = (EditText) findViewById(R.id.ResultText);

    }


    public void onClick(View view) {
        Button b = (Button) view;
        String val = b.getText().toString();
        StringBuilder res = new StringBuilder(resText.getText().toString());
        if (!val.equals("+") && !val.equals("-") && !val.equals("/") && !val.equals("x")) {
            if (val.equals(".") && !res.toString().contains(".") && !lastInput.equals(".")) {
                lastInput = val;
                val = val.concat("0");
                res = res.append(val);
            } else if (res.toString().equals("0") && !val.equals(".") && firstButtonPress && !lastInput.equals(".")) {
                res = new StringBuilder(val);
                lastInput = val;
            } else {
                if (lastInput.equals(".") && res.toString().contains(".") && !val.equals(".")) {
                    Log.d(TAG, "onClick: " + lastInput + " " + res.toString() + "--");
                    Log.d(TAG, "onClick: length of res" + String.valueOf(res.toString().length()));
                    res.setCharAt(res.toString().length()-1, val.charAt(0));
                    lastInput = val;
                } else if (!val.equals(".")) {
                    if (val.equals("0") && (Character.getNumericValue(res.toString().charAt(0)) > 0 ||
                                            res.toString().contains("."))) {
                        res = res.append(val);
                        lastInput = val;
                    } else if (!val.equals("0")){
                        res = res.append(val);
                        lastInput = val;
                    }
                }
            }
            if (firstButtonPress) {
                firstButtonPress = false;
            }
        }

        // Update text in text field
        resText.setText(res.toString());
    }
}
