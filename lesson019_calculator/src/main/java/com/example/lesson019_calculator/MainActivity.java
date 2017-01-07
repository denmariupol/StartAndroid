package com.example.lesson019_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnPls, btnMns, btnMlt, btnDvd, btnDt, btnEq;
    public TextView scrn;

    private StringBuilder sb;
    private int currentOperation, lastOperation;
    private static final String TAG = "Main Activity";
    private BigDecimal result;


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


    private void SetListener() {
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
        currentOperation = 0;
        lastOperation = 0;

        scrn = (TextView) findViewById(R.id.screen);

        btnPls = (Button) findViewById(R.id.buttonPlus);
        btnMns = (Button) findViewById(R.id.buttonMinus);
        btnDvd = (Button) findViewById(R.id.buttonDivision);
        btnMlt = (Button) findViewById(R.id.buttonMult);
        btnDt = (Button) findViewById(R.id.buttonDot);
        btnEq = (Button) findViewById(R.id.buttonEquals);

        btn0 = (Button) findViewById(R.id.button0);
        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                sb.append("0");
                scrn.setText(sb.toString());
                break;
            case R.id.button1:
                sb.append("1");
                scrn.setText(sb.toString());
                break;
            case R.id.button2:
                sb.append("2");
                scrn.setText(sb.toString());
                break;
            case R.id.button3:
                sb.append("3");
                scrn.setText(sb.toString());
                break;
            case R.id.button4:
                sb.append("4");
                scrn.setText(sb.toString());
                break;
            case R.id.button5:
                sb.append("5");
                scrn.setText(sb.toString());
                break;
            case R.id.button6:
                sb.append("6");
                scrn.setText(sb.toString());
                break;
            case R.id.button7:
                sb.append("7");
                scrn.setText(sb.toString());
                break;
            case R.id.button8:
                sb.append("8");
                scrn.setText(sb.toString());
                break;
            case R.id.button9:
                sb.append("9");
                scrn.setText(sb.toString());
                break;
            case R.id.buttonDot:
                CheckDoubleDot(sb.toString());
                scrn.setText(sb.toString());
                break;
            case R.id.buttonEquals:
                AddNumbers(sb.toString());
                if (Numbers.numbers.size() == 2) {
                    switch (currentOperation) {
                        case 1:
                            scrn.setText(Result(Operation.Division()));
                            break;
                        case 2:
                            scrn.setText(Result(Operation.Subtraction()));
                            break;
                        case 3:
                            scrn.setText(Result(Operation.Addition()));
                            break;
                        case 4:
                            scrn.setText(Result(Operation.Multiply()));
                            break;
                    }

                } else {
                    DeleteNumbers();
                    ClrScrn();
                }
                break;
            case R.id.buttonDivision:
                CheckOperation(1);
                PerformOperatiom(lastOperation);
                break;
            case R.id.buttonMinus:
                CheckOperation(2);
                PerformOperatiom(lastOperation);
                break;
            case R.id.buttonPlus:
                CheckOperation(3);
                PerformOperatiom(lastOperation);
                break;
            case R.id.buttonMult:
                CheckOperation(4);
                PerformOperatiom(lastOperation);
                break;
        }
    }

    // history of last 1 operation
    void CheckOperation(int curOp) {
        if (currentOperation == 0 && lastOperation == 0) {
            currentOperation = curOp;
            lastOperation = curOp;
            return;
        }

        lastOperation = currentOperation;
        currentOperation = curOp;
    }


    void PerformOperatiom(int op) {
        if (Float.valueOf(scrn.getText().toString()) == 0){
            DeleteNumbers();
            return;
        }
        AddNumbers(scrn.getText().toString());
        if (Numbers.numbers.size() == 2) {
            sb = new StringBuilder();
            switch (lastOperation) {
                case 1:
                    result = Operation.Division();
                    break;
                case 2:
                    result = Operation.Subtraction();
                    break;
                case 3:
                    result = Operation.Addition();
                    break;
                case 4:
                    result = Operation.Multiply();
                    break;
            }
            String s = Result(result);
            scrn.setText(s);
            AddNumbers(scrn.getText().toString());
        } else
            sb = new StringBuilder();
    }


    void DeleteNumbers() {
        Numbers.numbers.clear();
    }


    void ClrScrn() {
        sb = new StringBuilder();
        scrn.setText("0");
    }

    // add numbers to a list
    void AddNumbers(String s) {
        if (Numbers.numbers.size() <= 1 && s.length() > 0 && Float.valueOf(s) != 0)
            Numbers.numbers.add(new BigDecimal(Double.valueOf(s)));
        else if (Numbers.numbers.size() == 2 && s.length() > 0 && Float.valueOf(s) != 0) {
            DeleteNumbers();
            Numbers.numbers.add(new BigDecimal(Double.valueOf(scrn.getText().toString())));
        }
    }

    //if decimal part == 0,remove it
    String Result(BigDecimal number) {
        String s = String.valueOf(number);
        String[] parts = s.split("\\.");
        switch (parts.length){
            case 1:
                return s;

            case 2:
                return parts[0].toString();

            default:
                break;
        }
        return "";
    }

    // check for double dot in number
    void CheckDoubleDot(String s){
        if (!s.contains("."))
            sb.append(".");
    }
}
