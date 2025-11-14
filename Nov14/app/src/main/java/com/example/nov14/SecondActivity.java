package com.example.nov14;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    Button logout;
    TextView welcomeText;
    Switch colorSwitch;
    Button searchBUtton;
    EditText queryText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.d("lifecycle","Second Activity - on Create");

        queryText = findViewById(R.id.query);
        searchBUtton = findViewById(R.id.searchBUtton);

        Student stdFromMainActivity = getIntent().getExtras().getParcelable("currentUser");
        welcomeText = findViewById(R.id.welcomeText);
        logout = findViewById(R.id.logout);
        colorSwitch = findViewById(R.id.turnOnColorsSwitch);
        welcomeText.setText("Welcome "+stdFromMainActivity.name +" To the app");
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent backIntent = new Intent();
                Log.d("switch value",colorSwitch.isChecked() +"" );
                Boolean v = colorSwitch.isChecked();
                backIntent.putExtra("switch", v);
                setResult(RESULT_OK,backIntent);
                finish();
            }
        });


        searchBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!queryText.getText().toString().isEmpty()){
                   String query = queryText.getText().toString();
                    Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
                    intent.putExtra(SearchManager.QUERY, query);
                        startActivity(intent);
                }
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle","Second Activity - on Resume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle","Second Activity - on Destroy");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle","Second Activity - on Pause");

    }

}