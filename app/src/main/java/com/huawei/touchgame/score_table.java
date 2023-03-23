package com.huawei.touchgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class score_table extends AppCompatActivity {


    ArrayList<scoremodel> contentRead = new ArrayList<>();
    private SQLiteAdapter mySQLiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);

        RecyclerView recyclerView = findViewById(R.id.ScoreRecycleView);


        mySQLiteAdapter = new SQLiteAdapter(this);
//        mySQLiteAdapter.openToWrite();
//        mySQLiteAdapter.deleteAll();
//        mySQLiteAdapter.insert("Alexadence Attada",23);
//        mySQLiteAdapter.insert("Mr Woohoo",1);
//        mySQLiteAdapter.insert("Tan Chiang Kang",4);
//        mySQLiteAdapter.close();


        mySQLiteAdapter.openToRead();
        contentRead = mySQLiteAdapter.queryByScore();
        mySQLiteAdapter.close();

        Scoreboard_RecycleViewAdapter adapter = new Scoreboard_RecycleViewAdapter(this,contentRead);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Button BackMenu = findViewById(R.id.BackMenu);
        BackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homepage = new Intent(score_table.this,MainActivity.class);
                startActivity(homepage);
                Toast.makeText(getBaseContext(),"Back to Menu",Toast.LENGTH_SHORT).show();
            }
        });


    }
}