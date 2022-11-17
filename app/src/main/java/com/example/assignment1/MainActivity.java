package com.example.assignment1;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int mode = 0;
    Calculator c = new Calculator();
    TextView result_text ;
    TextView historyOfResults ;
    Button add_but ;
    Button sub_but;
    Button times_but ;
    Button div_but;
    Button clear;
    Button history;
    Button one ;
    Button two;
    Button three ;
    Button four;
    Button five;
    Button six;
    Button seven ;
    Button eight;
    Button nine ;
    Button zero;
    Button equal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clear = findViewById(R.id.Cbut);
        add_but = findViewById(R.id.addbut);
        sub_but = findViewById(R.id.minusbut);
        times_but = findViewById(R.id.multibut);
        div_but = findViewById(R.id.divbut);
        history = findViewById(R.id.historybut);
        result_text = findViewById(R.id.resultTextView);
        one = findViewById(R.id.onebut);
        two = findViewById(R.id.twobut);
        three = findViewById(R.id.threebut);
        four = findViewById(R.id.fourbut);
        five = findViewById(R.id.fivebut);
        six = findViewById(R.id.sixbut);
        seven = findViewById(R.id.sevenbut);
        eight = findViewById(R.id.eightbut);
        nine = findViewById(R.id.ninebut);
        zero = findViewById(R.id.zerobut);
        add_but.setOnClickListener(this);
        sub_but.setOnClickListener(this);
        times_but.setOnClickListener(this);
        div_but.setOnClickListener(this);
        history.setOnClickListener(this);
        clear.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        equal = findViewById(R.id.equalbut);
        equal.setOnClickListener(this);
        historyOfResults = findViewById(R.id.historyTextView);
        historyOfResults.setMovementMethod(new ScrollingMovementMethod());

    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {
        String value = ((Button)view).getText().toString();
        if(value.contains("HISTORY"))
        {
            if(value.contains("ADVANCE")) {
                mode = 1;
                history.setText(R.string.historyStandard);
                history.setBackgroundColor(0xFFFF0000);

            }
            else
            {
                mode = 0;
                history.setText(R.string.history);
                history.setBackgroundColor(0xEE82EE);
                c.historyList.clear();
                historyOfResults.setText((" "));
                Toast.makeText(this, "entered wrong",Toast.LENGTH_LONG).show();
            }
        }
        else
        {
            c.push(value);
            Object[] valuesArray = c.enterValues.toArray();
            result_text.setText(" ");
            for (int i = 0; i < valuesArray.length; i++) {
                result_text.append(valuesArray[i] + " ");
            }
            if(value.equalsIgnoreCase("="))
            {
                if(c.wrongResult==true) {

                    Toast.makeText(MainActivity.this,c.error,Toast.LENGTH_LONG).show();
                }
                else{
                    //c.enterValues.add(String.valueOf(c.calculate()));
                    if(mode==1){
                        c.historyStore();
                        String historyListValues = c.historyList.toString()
                                .replace("[", " ").replace(",", "\n")
                                .replace("]", " ");
                        historyOfResults.setText(historyListValues);
                    }
                }

            }

        }


    }
}