package com.hackathon.traventials;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Initialize variable
    EditText items, prior;
    Button btCreate;
    RecyclerView recyclerView;

    List<MainData> dataList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        //Assign Variables
        items = findViewById(R.id.etitem);
        btCreate = findViewById(R.id.create);
        recyclerView = findViewById(R.id.recycler_view);
        prior = findViewById(R.id.etpriority);

        //Initialize Database
        database = RoomDB.getInstance(this);
        //Store database value in data list
        dataList = database.mainDao().getAll();

        //Initialize linear layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        //SET LAYOUT MANAGER
        recyclerView.setLayoutManager(linearLayoutManager);
        //INITIALIZE ADAPTER
        adapter = new MainAdapter(MainActivity.this, dataList);
        //Set Adapter
        recyclerView.setAdapter(adapter);

        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //GET STRING FROM EDIT TEXT
                String sItem = items.getText().toString();
                if(!sItem.equals("")){
                    //WHEN TEXT IS NOT EMPTY
                    //INITIALIZE MAIN DATA
                    MainData data = new MainData();
                    //Set text on main data
                    data.setItem(sItem);
                    //insert text in database
                    database.mainDao().insert(data);
                    items.setText("");
                    prior.setText("");

                    //notify when data is inserted
                    dataList.clear();
                    dataList.addAll(database.mainDao().getAll());
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}