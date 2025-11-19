package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
    implements ToDosBaseAdapter.UpdateTaskListener,
        ToDosBaseAdapter.DeleteTaskListener
{
    private ActivityResultLauncher<Intent> myLauncher;

    ListView tasksTable;
    FloatingActionButton addNewTask;
    ArrayList<ToDo> mainActivityList;
    ToDosBaseAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,"Welcome to your list",Toast.LENGTH_LONG).show();

        mainActivityList =  ((MyApp)getApplication()).tasks;
        tasksTable = findViewById(R.id.taskstable);
        addNewTask = findViewById(R.id.addnewtask);
        adapter = new ToDosBaseAdapter(mainActivityList,this);
        adapter.updateListener = this;
        adapter.deleteListener = this;

        tasksTable.setAdapter(adapter);
        tasksTable.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,mainActivityList.get(i).task,Toast.LENGTH_LONG).show();
            }
        });


        myLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                Intent data = result.getData();
                ToDo d = (ToDo) data.getSerializableExtra("newtodo");
                ((MyApp)getApplication()).tasks.add(d);
                adapter.notifyDataSetChanged();
            }
        });

        addNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddNewToDo.class);
                myLauncher.launch(i);

            }
        });
    }

    @Override
    public void deleteTask(int i) {
        ((MyApp)getApplication()).tasks.remove(i);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void updateTask(int i) {
        ((MyApp)getApplication()).tasks.get(i).isUrgent = !((MyApp)getApplication()).tasks.get(i).isUrgent;
        adapter.notifyDataSetChanged();
    }
}