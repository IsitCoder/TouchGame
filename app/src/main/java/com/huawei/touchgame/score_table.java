package com.huawei.touchgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class score_table extends AppCompatActivity {
    private SQLiteAdapter mySQLiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_table);

        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();
        //mySQLiteAdapter.deleteAll();
        mySQLiteAdapter.insert("Alexadence Attada",24);
        mySQLiteAdapter.insert("Mr Tan Tan",23);
        mySQLiteAdapter.insert("Tan Chiang Kang",23);
        mySQLiteAdapter.close();

        mySQLiteAdapter.openToRead();
        String contentRead = mySQLiteAdapter.queryByScore();
        mySQLiteAdapter.close();
        TextView textView2 = findViewById(R.id.ListViewss);
        textView2.setText(contentRead);
        textView2.setTextSize(20);


    }
}