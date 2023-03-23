package com.huawei.touchgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent NewGameScreen = new Intent(MainActivity.this, GameActivity.class);
        Intent ViewScoreScreen = new Intent(MainActivity.this, score_table.class);



        Button newgame = findViewById(R.id.newgame_button);

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NewGameScreen);
            }
        });

        Button score_view_button = findViewById(R.id.score_view_button);

        score_view_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ViewScoreScreen);
            }
        });

        Button exited_button = findViewById(R.id.exitgame_button);

        exited_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
                Toast.makeText(getBaseContext(),"Game Exited",Toast.LENGTH_SHORT).show();
            }
        });
    }
}