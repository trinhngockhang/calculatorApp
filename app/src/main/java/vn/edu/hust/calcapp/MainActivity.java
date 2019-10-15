package vn.edu.hust.calcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    TextView tvCaculating;

    double op1, op2;   // gia tri 2 toan hang
    int op;         // 1: add, 2: sub, 3: mul, 4: div
    int state;      // 1: nhap op1, 2: nhap op2
    boolean doneCaculate;
    boolean doting;
    String caculating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.text_result);
        tvCaculating = findViewById((R.id.text_caculating));
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);

        findViewById(R.id.btn_equal).setOnClickListener(this);

        findViewById(R.id.btn_rev).setOnClickListener(this);
        findViewById(R.id.btn_bs).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);

        // Khoi tao cac gia tri
        op1 = 0;
        op2 = 0;
        state = 1;
        op = 0;
        doting = false;
        tvResult.setText(convertString((op1)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                addValue(0);
                break;
            case R.id.btn_1:
                addValue(1);
                break;
            case R.id.btn_2:
                addValue(2);
                break;
            case R.id.btn_3:
                addValue(3);
                break;
            case R.id.btn_4:
                addValue(4);
                break;
            case R.id.btn_5:
                addValue(5);
                break;
            case R.id.btn_6:
                addValue(6);
                break;
            case R.id.btn_7:
                addValue(7);
                break;
            case R.id.btn_8:
                addValue(8);
                break;
            case R.id.btn_9:
                addValue(9);
                break;
            case R.id.btn_add:
                selectOperator(1);
                break;
            case R.id.btn_sub:
                selectOperator(2);
                break;
            case R.id.btn_mul:
                selectOperator(3);
                break;
            case R.id.btn_div:
                selectOperator(4);
                break;
            case R.id.btn_equal:
                calcResult();
                break;
            case R.id.btn_rev:
                reverseOperand();
                break;
            case R.id.btn_bs:
                removeDigit();
                break;
            case R.id.btn_ce:
                clearOperand();
                break;
            case R.id.btn_c:
                clearOperator();
                break;
            case R.id.btn_dot:
                addDot();
                break;
        }
    }

    private void clearOperator() {
        // Khoi tao cac gia tri
        op1 = 0;
        op2 = 0;
        state = 1;
        op = 0;
        doneCaculate = false;
        doting = false;
        tvCaculating.setText("0");
        tvResult.setText(convertString((op1)));
    }
    private void addDot(){
        if(!doting){
            doting = true;
            if(state == 1){
               tvResult.setText(convertString(op1)+ ".");
            }  else {
               tvResult.setText(convertString(op2) + ".");
            }
        }
    }
    private void clearOperand() {
        doneCaculate = false;
        doting = false;
        if (state == 1) {
            op1 = 0;
            tvResult.setText(convertString(op1));
        } else {
            op2 = 0;
            tvResult.setText(convertString(op2));
        }
    }

    private void removeDigit() {
        if (!doting) {
            if (state == 1) {
                op1 = op1 / 10;
                tvResult.setText(convertString(op1));
            } else {
                op2 = op2 / 10;
                tvResult.setText(convertString(op2));
            }
        }else{
             if(state == 1){
               String temp = op1 + "";
               temp = temp.substring(0, temp.length() - 1);
               op1 = Double.valueOf(temp);
               tvResult.setText(convertString(op1));
             } else{
               String temp = op2 + "";
               temp = temp.substring(0, temp.length() - 1);
               op2 = Double.valueOf(temp);
               tvResult.setText(convertString(op2));
             }
        }
    }
    private void reverseOperand() {
        if (state == 1) {
            op1 = -op1;
            tvResult.setText(convertString(op1));
        } else {
            op2 = -op2;
            tvResult.setText(convertString(op2));
        }
    }

    private void calcResult() {
        double result = 0;
        doting = false;
        // neu ko an phep tinh
        if(state == 1){
            tvResult.setText(convertString(op1));
            return;
        }
        // neu phep chia cho 0
        if(op == 4 && op2 == 0){
            tvResult.setText("Loi");
            state = 1;
            return;
        }
        switch (op) {
            case 1: result = op1 + op2; break;
            case 2: result = op1 - op2; break;
            case 3: result = op1 * op2; break;
            case 4: result = op1 / op2; break;
        }
        if(!doneCaculate) tvCaculating.setText((tvCaculating.getText().toString()+convertString(op2)));
        tvResult.setText(convertString(result));
        state = 1;
        op2 = 0;
        op1 = result;
        op = 0;
        doneCaculate = true;
    }

    private void selectOperator(int p) {
        if(state == 2){
            calcResult();
        }
        op = p;
        switch (p) {
            case 1: tvCaculating.setText((convertString(op1) +"+")); break;
            case 2: tvCaculating.setText((convertString(op1) +"-")); break;
            case 3: tvCaculating.setText((convertString(op1) +"x")); break;
            case 4: tvCaculating.setText((convertString(op1) +"/")); break;
        }
        state = 2;
        op2 = 0;
        doting = false;
        tvResult.setText(convertString(op2));
    }

    private void addValue(int c) {
        doneCaculate = false;
        if (state == 1) {
            if(doting){
                Log.v("check","da nhay vao day");
                String temp = covertStringDouble(op1) + c;
                op1 = Double.valueOf(temp);
            }else {
                op1 = op1 >= 0 ? op1 * 10 + c : op1 * 10 - c;
            }
            tvResult.setText(convertString(op1));
            tvCaculating.setText("0");
        } else {
            if(doting){
                String temp = covertStringDouble(op2) + c;
                op2 = Double.parseDouble(temp);
            }else {
              op2 = op2 >= 0 ? op2 * 10 + c : op2 * 10 - c;
            }
             tvResult.setText(convertString(op2));
        }
    }
    private String convertString(double a){
        int b = (int) a;
        double c = a - b;
        if(c == 0){
            return b + "";
        }
        return a + "";
    }
    private String covertStringDouble(double a){
        int b = (int) a;
        double c = a - b;
        if(c == 0){
            return b + ".";
        }
        return a + "";
    }
}
