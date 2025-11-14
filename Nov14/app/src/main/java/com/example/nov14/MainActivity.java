package com.example.nov14;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText nametext;
    EditText schoolNameText;
    Button login;
    RadioButton redradioBut;
    RadioButton blueradioBut;
    RadioButton greenradioBut;
    int currentBG_color = R.color.White;
    private ActivityResultLauncher<Intent> myLauncher;
    Student currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("lifecycle","Main Activity - on Create");

        schoolNameText = findViewById(R.id.schoolName);
        myLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {

            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                Boolean switchColor =  data.getBooleanExtra("switch",false);
                if (switchColor){
                    getWindow().getDecorView().setBackgroundColor(getColor(R.color.White));
                }
                currentUser = null;
                nametext.setText("");
                schoolNameText.setText("");
            }
        });
        if (savedInstanceState != null){
            currentBG_color = savedInstanceState.getInt("background_color");
            getWindow().getDecorView().setBackgroundColor(getColor(currentBG_color));
        }

        nametext = findViewById(R.id.nameText);
       login = findViewById(R.id.login);
        redradioBut = findViewById(R.id.red);
        greenradioBut = findViewById(R.id.green);
        blueradioBut = findViewById(R.id.blue);
        redradioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBG_color= R.color.PaleVioletRed;
                getWindow().getDecorView().setBackgroundColor(getColor(R.color.PaleVioletRed));
            }
        });

        blueradioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBG_color = R.color.CornflowerBlue;

                getWindow().getDecorView().setBackgroundColor(getColor(R.color.CornflowerBlue));
            }
        });

        greenradioBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentBG_color = R.color.DarkSeaGreen;
                getWindow().getDecorView().setBackgroundColor(getColor(R.color.DarkSeaGreen));
            }
        });


       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // go to second activity
               Intent i = new Intent(MainActivity.this, SecondActivity.class);
               // key-value pairs ===> Extras
               if(!nametext.getText().toString().isEmpty() && !schoolNameText.getText().toString().isEmpty()) {
                   currentUser = new Student(nametext.getText().toString(), schoolNameText.getText().toString());
                 //  i.putExtra("username",nametext.getText().toString() );
                   i.putExtra("currentUser",currentUser);
                   myLauncher.launch(i);
               }

           }
       });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","Main Activity - on Resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","Main Activity - on Destroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","Main Activity - on Pause");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        Log.d("lifecycle","Main Activity - in onSaveInstanceState");
        outState.putInt("background_color",currentBG_color );
    }
}