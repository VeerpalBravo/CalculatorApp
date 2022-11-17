package com.example.assignment1;

import android.util.Log;

import java.util.ArrayList;

public class Calculator {
    ArrayList<String> enterValues = new ArrayList<String>();
    ArrayList<String> historyList = new ArrayList<String>();
    int calculatedResult = 0;
    Boolean wrongResult =false;
    String error ="";
    void push(String value)
    {
        enterValues.add(value);
        Log.d("Cal App", "push: enterValues");
        if(value.equalsIgnoreCase("="))
        {
            calculatedResult = calculate();
            if(wrongResult==false) {
                enterValues.add(String.valueOf(calculatedResult));
            }
        }
        if(value.equalsIgnoreCase("C"))
            enterValues.clear();

    }
    void historyStore(){
        String historyValues = "";
        Object[] valuesArray = enterValues.toArray();
        for (int i = 0; i < valuesArray.length; i++) {
            historyValues = historyValues + String.valueOf(valuesArray[i]);
        }
        historyList.add(historyValues);
    }

    int calculate() {
        Object[] valuesArray = enterValues.toArray();
        int result = 0;
        String num=" ";
        for (int i = 0; i < (valuesArray.length - 1); i++) {
            //int j = i + 1;
            if (i == 0 || i%2==0) {
                num=(String) valuesArray[i];
                isNumeric(num);
                if(wrongResult==false){
                    if(i==0)
                        result=isNumeric((String) valuesArray[i]);
                }

            }
            if (i % 2 != 0) {
                String op = String.valueOf(valuesArray[i]);
                if(op.equalsIgnoreCase("+" )||
                op.equalsIgnoreCase("-")||
                op.equalsIgnoreCase("/")||
                op.equalsIgnoreCase("*")){
                num=(String) valuesArray[i+1];
                switch (op) {
                    case "+":
                        if(wrongResult==false)
                        result = result + isNumeric(num);
                        break;
                    case "-":
                        if(wrongResult==false)
                            result = result - isNumeric(num);
                        break;
                    case "*":
                        if(wrongResult==false)
                            result = result * isNumeric(num);
                        break;
                    case "/":
                      if(wrongResult==false)
                            try{
                        result = result / isNumeric(num);
                            }
                        catch (ArithmeticException e){
                            error="Number is divided by wrong value.";
                            wrongResult=true;
                        }
                        break;
                    default:
                        error = "operator is not correct";
                        wrongResult=true;
                }

                }
                else {
                    error = "At position: " + (i + 1) + " there should be an operator.";
                    wrongResult=true;
                }

            }

        }return result;
    }
    int isNumeric(String num){
        int result=0;
        if (!num.matches("[0-9]")) {
            error = " there should operators be followed by number.";
            wrongResult=true;

        } else {
            wrongResult=false;
            result = Integer.parseInt(num);
        }
        return result;
    }
}
