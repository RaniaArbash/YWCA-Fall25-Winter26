package com.example.week1demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity
        extends AppCompatActivity
        implements View.OnClickListener {

    EditText nameText;
    EditText yearText;
    TextView msgText;
    Button addButton;
    Switch stdSwitch;
    RadioButton redradioBut;
    RadioButton greenradioBut;
    RadioButton blueradioBut;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentinfo);
        nameText = findViewById(R.id.nametext);
        yearText = findViewById(R.id.yeartext);
        msgText = findViewById(R.id.welcomeText);
        addButton = findViewById(R.id.addBut);
        stdSwitch = findViewById(R.id.stdSwitch);
        redradioBut = findViewById(R.id.red);
        greenradioBut = findViewById(R.id.green);
        blueradioBut = findViewById(R.id.blue);

        addButton.setOnClickListener(this);
        redradioBut.setOnClickListener(this);
        stdSwitch.setOnClickListener(this);

        Log.d("std activity","in OnCreate function");
        Log.d("std activity",R.id.red+ "" );
        Log.d("std activity",R.id.green+ "" );

//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("std activity","add but clicked");
//                if (!nameText.getText().toString().isEmpty()){
//                Toast.makeText(StudentActivity.this, "Welcome " + nameText.getText().toString() ,Toast.LENGTH_LONG ).show();
//                }
//            }
//        });
//
//        stdSwitch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Log.d("std activity","Switch toggled ");
//
//            }
//        });

//        redradioBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getWindow().getDecorView().setBackgroundColor(getColor(R.color.PaleVioletRed));
//            }
//        });
//
//        blueradioBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getWindow().getDecorView().setBackgroundColor(getColor(R.color.CornflowerBlue));
//            }
//        });

//        greenradioBut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getWindow().getDecorView().setBackgroundColor(getColor(R.color.DarkSeaGreen));
//            }
//        });

    }


//    public void addClicked(View view) {
//        Log.d("std activity","add Button Function Is Running ");
//
//    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addBut){
            Log.d("std activity","add Button Clicked ");
        }
        else if (view.getId() == R.id.stdSwitch){
            Log.d("std activity","Switch Toggled ");
        }
        else if (view.getId() == R.id.red){
           Log.d("std activity","Red Button Clicked ");

        }
//        switch (view.getId()){
//            case R.id.addBut:
//                Log.d("std activity","add Button Clicked ");
//                break;
//            case R.id.stdSwitch:
//                Log.d("std activity","Switch Toggled ");
//                break;
//
//            case R.id.red:
//                Log.d("std activity","Red Button Clicked ");
//
//                break;
//
//            default:
//                Log.d("std activity","No thing clicked ");

        //}



    }
}
