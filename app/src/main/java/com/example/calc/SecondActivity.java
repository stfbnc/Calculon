package com.example.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.example.calc.btns_handles.ButtonsHandleScientific;

public class SecondActivity extends AppCompatActivity {

    private boolean isPressedEqual = false;
    private String disp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent = getIntent();
        String str = intent.getStringExtra(MainActivity.DISP_STRING);
        boolean eqPress = intent.getBooleanExtra(MainActivity.EQ_BOOL, false);
        TextView txt = findViewById(R.id.textView2);
        txt.setText(str);
        disp = str;
        isPressedEqual = eqPress;
    }

    //public void toNormal(){
        //Intent intent = new Intent(this, MainActivity.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);
     //   finish();
    //}

    public void onNumberClickScientific(View view){
        TextView txt = (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        disp = btnsHnd.numberHandle(btn.getText().toString(), disp, isPressedEqual);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onDotClickScientific(View view){
        TextView txt = (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        disp = btnsHnd.dotHandle(btn.getText().toString(), disp, isPressedEqual);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onOpClickScientific(View view){
        TextView txt = (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        disp = btnsHnd.opHandle(btn.getText().toString(), disp, isPressedEqual);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onSqrtClickScientific(View view){
        TextView txt = (TextView) findViewById(R.id.textView2);
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        try {
            disp = btnsHnd.sqrtHandle(disp);
            isPressedEqual = true;
        }catch (Exception e){
            isPressedEqual = false;
        }
        txt.setText(disp);
    }

    public void onMathfunClickScientific(View view){
        TextView txt = (TextView) findViewById(R.id.textView2);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        try {
            disp = btnsHnd.mathfunHandle(btn.getText().toString(), disp);
            isPressedEqual = true;
        }catch (Exception e){
            isPressedEqual = false;
        }
        txt.setText(disp);
    }

    public void onEqualClickScientific(View view){
        TextView txt = (TextView)findViewById(R.id.textView2);
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        disp = btnsHnd.equalHandle(disp);
        isPressedEqual = true;
        txt.setText(disp);
    }

    public void onClearClickScientific(View view){
        TextView txt = (TextView)findViewById(R.id.textView2);
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        disp = btnsHnd.clearHandle(disp);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onClearAllClickScientific(View view){
        TextView txt = (TextView)findViewById(R.id.textView2);
        ButtonsHandleScientific btnsHnd = new ButtonsHandleScientific();
        disp = btnsHnd.clearAllHandle();
        isPressedEqual = false;
        txt.setText(disp);
    }

}
