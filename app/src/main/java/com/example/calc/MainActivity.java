package com.example.calc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calc.btns_handles.ButtonsHandle;

public class MainActivity extends AppCompatActivity {

    public static final String DISP_STRING = "com.example.calc.RES_STRING";
    public static final String EQ_BOOL = "com.example.calc.EQ_PRESS";
    private boolean isPressedEqual = false;
    private String disp = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toScientific(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(DISP_STRING, disp);
        intent.putExtra(EQ_BOOL, isPressedEqual);
        startActivity(intent);
    }

    public void onNumberClick(View view){
        TextView txt = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandle btnsHnd = new ButtonsHandle();
        disp = btnsHnd.numberHandle(btn.getText().toString(), disp, isPressedEqual);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onDotClick(View view){
        TextView txt = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandle btnsHnd = new ButtonsHandle();
        disp = btnsHnd.dotHandle(btn.getText().toString(), disp, isPressedEqual);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onOpClick(View view){
        TextView txt = (TextView) findViewById(R.id.textView);
        Button btn = (Button) findViewById(view.getId());
        ButtonsHandle btnsHnd = new ButtonsHandle();
        disp = btnsHnd.opHandle(btn.getText().toString(), disp, isPressedEqual);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onEqualClick(View view){
        TextView txt = (TextView)findViewById(R.id.textView);
        ButtonsHandle btnsHnd = new ButtonsHandle();
        disp = btnsHnd.equalHandle(disp);
        isPressedEqual = true;
        txt.setText(disp);
    }

    public void onClearClick(View view){
        TextView txt = (TextView)findViewById(R.id.textView);
        ButtonsHandle btnsHnd = new ButtonsHandle();
        disp = btnsHnd.clearHandle(disp);
        isPressedEqual = false;
        txt.setText(disp);
    }

    public void onClearAllClick(View view){
        TextView txt = (TextView)findViewById(R.id.textView);
        ButtonsHandle btnsHnd = new ButtonsHandle();
        disp = btnsHnd.clearAllHandle();
        isPressedEqual = false;
        txt.setText(disp);
    }
}
