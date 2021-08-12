package com.example.sos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

public class number_activity extends AppCompatActivity {

    EditText num1,num2,num3;
    Button button;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);
//        num1 = findViewById(R.id.num1);
//        num2 = findViewById(R.id.num2);
//        num3 = findViewById(R.id.num3);
//        button = findViewById(R.id.button);
//
//        sharedPreferences = getSharedPreferences("NumberStorage", Context.MODE_PRIVATE);
//        num1.setText(String.valueOf(sharedPreferences.getInt("Number1",0)));
//        num2.setText(String.valueOf(sharedPreferences.getInt("Number2",0)));
//        num3.setText(String.valueOf(sharedPreferences.getInt("Number3",0)));
//        button.setOnClickListener(v -> {
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("Number1",Integer.parseInt(num1.getText().toString()));
//            editor.putInt("Number2",Integer.parseInt(num2.getText().toString()));
//            editor.putInt("Number3",Integer.parseInt(num3.getText().toString()));
//            editor.apply();
//        });

    }
}