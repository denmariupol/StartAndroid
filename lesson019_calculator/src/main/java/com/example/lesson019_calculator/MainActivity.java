package com.example.lesson019_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnPls,btnMns,btnMlt,btnDvd,btnDt,btnEq;
    public TextView scrn;

    private StringBuilder sb;
    private int currentOperation,operationCounter;
    private static final String TAG = "Main Activity";
    private float result;
    private State state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Numbers.init();
        Init();
        DeleteNumbers();
        ClrScrn();
        SetListener();
    }



    private void SetListener(){
        btn0.setOnClickListener(this);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btnDt.setOnClickListener(this);
        btnDvd.setOnClickListener(this);
        btnPls.setOnClickListener(this);
        btnMlt.setOnClickListener(this);
        btnMns.setOnClickListener(this);
        btnEq.setOnClickListener(this);
    }


    private void Init() {
        state = State.FIRST_NUMBER;
        operationCounter = 0;

        scrn = (TextView)findViewById(R.id.screen);

        btnPls = (Button)findViewById(R.id.buttonPlus);
        btnMns = (Button)findViewById(R.id.buttonMinus);
        btnDvd = (Button)findViewById(R.id.buttonDivision);
        btnMlt = (Button)findViewById(R.id.buttonMult);
        btnDt = (Button)findViewById(R.id.buttonDot);
        btnEq = (Button)findViewById(R.id.buttonEquals);

        btn0 = (Button)findViewById(R.id.button0);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        btn5 = (Button)findViewById(R.id.button5);
        btn6 = (Button)findViewById(R.id.button6);
        btn7 = (Button)findViewById(R.id.button7);
        btn8 = (Button)findViewById(R.id.button8);
        btn9 = (Button)findViewById(R.id.button9);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button0:
                CheckState();
                sb.append("0");
                scrn.setText(sb.toString());
                break;
            case R.id.button1:
                CheckState();
                sb.append("1");
                scrn.setText(sb.toString());
                break;
            case R.id.button2:
                CheckState();
                sb.append("2");
                scrn.setText(sb.toString());
                break;
            case R.id.button3:
                CheckState();
                sb.append("3");
                scrn.setText(sb.toString());
                break;
            case R.id.button4:
                CheckState();
                sb.append("4");
                scrn.setText(sb.toString());
                break;
            case R.id.button5:
                CheckState();
                sb.append("5");
                scrn.setText(sb.toString());
                break;
            case R.id.button6:
                CheckState();
                sb.append("6");
                scrn.setText(sb.toString());
                break;
            case R.id.button7:
                CheckState();
                sb.append("7");
                scrn.setText(sb.toString());
                break;
            case R.id.button8:
                CheckState();
                sb.append("8");
                scrn.setText(sb.toString());
                break;
            case R.id.button9:
                CheckState();
                sb.append("9");
                scrn.setText(sb.toString());
                break;
            case R.id.buttonDot:
                CheckState();
                sb.append(".");
                scrn.setText(sb.toString());
                break;
            case R.id.buttonEquals:
                AddNumbers(sb.toString());
                if (Numbers.numbers.size() == 2){
                    switch (currentOperation){
                        case 1:
                            Operation.Division();
                            break;
                        case 2:
                            Operation.Subtraction();
                            break;
                        case 3:
                            result = Operation.Addition();
                            String s = Result(result);
                            scrn.setText(s);
                            Log.d(TAG,"Addition"+String.valueOf(Operation.Addition()));
                            break;
                        case 4:
                            Operation.Multiply();
                            break;
                    }
                    state = State.EQUALS_PRESSED;
                }else
                    state = State.EQUALS_PRESSED;
                break;
            case R.id.buttonDivision:
                currentOperation = 1;
                CountOperation();
                break;
            case R.id.buttonMinus:
                currentOperation = 2;
                CountOperation();
                break;
            case R.id.buttonPlus:
                currentOperation = 3;
                CountOperation();
                break;
            case R.id.buttonMult:
                currentOperation = 4;
                CountOperation();
                break;
        }
    }


    void DeleteNumbers(){
        Numbers.numbers.clear();
    }


    void ClrScrn(){
        sb = new StringBuilder();
        scrn.setText("0");
    }


    void AddNumbers(String s){
        if(Numbers.numbers.size()<2 && s.length() > 0)
            Numbers.numbers.add(Float.valueOf(s));
    }


    void CountOperation(){
        operationCounter++;
        if(operationCounter <= 1)
            state = State.OPERATION_PRESSED_FIRST;
        else
            state = State.OPERATION_PRESSED_SECOND;
    }


    void CheckState(){
        switch (state){
            case EQUALS_PRESSED:
                DeleteNumbers();
                ClrScrn();
                state = State.FIRST_NUMBER;
                break;
            case OPERATION_PRESSED_SECOND:
                AddNumbers(scrn.getText().toString());
                DeleteNumbers();
                ClrScrn();
                break;
            case OPERATION_PRESSED_FIRST:
                AddNumbers(scrn.getText().toString());
                ClrScrn();
                break;
            case FIRST_NUMBER:

                break;
            case SECOND_NUMBER:

                break;
        }
    }

    String Result(Float number){
        String s = String.valueOf(number);
        String[] parts = s.split("\\.");

        if (parts[1].length() == 1 && Integer.valueOf(parts[1]) == 0)
            return parts[0].toString();
        else
            return s;
    }
}
