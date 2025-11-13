package com.example.week1demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//R.Java

    EditText num1Text;
    EditText num2Text;
    Button add ;
    Button sub;
    Button times ;
    Button div;
    TextView resultText;
    int result;
    int num1, num2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
         num1Text = findViewById(R.id.number1);
         num2Text = findViewById(R.id.number2);
         add= findViewById(R.id.add);
         sub = findViewById(R.id.sub);
         times = findViewById(R.id.times);
         div = findViewById(R.id.div);
         resultText = findViewById(R.id.resulttext);
            // create the model class object
            // call the functions from the model class

         add.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 //
                if ( validateInput(num1Text.getText().toString(),num2Text.getText().toString())) {
                    result = num1 + num2;
                    resultText.setText(result);
                }
                    else {
                        // Toast
                }
             }
         });
         sub.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if ( validateInput(num1Text.getText().toString(),num2Text.getText().toString())) {
                     result = num1 - num2;
                     resultText.setText(result);
                 }
                 else {
                     // Toast
                 }
             }
         });
         times.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if ( validateInput(num1Text.getText().toString(),num2Text.getText().toString())) {
                     result = num1 * num2;
                     resultText.setText(result);
                 }
                 else {
                     // Toast
                 }
             }
         });
         div.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if ( validateInput(num1Text.getText().toString(),num2Text.getText().toString())) {
                     result = num1 / num2;
                     resultText.setText(result);
                 }
                 else {
                     // Toast
                 }
             }
         });


    }

    Boolean validateInput (String s1, String s2) {
        if (!s1.isEmpty() && !s2.isEmpty()) {
            num1 = Integer.parseInt(num1Text.getText().toString());// "55"
            num2 = Integer.parseInt(num2Text.getText().toString());// "55"
            return true;
        }
        return false;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



}