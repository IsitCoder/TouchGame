package com.huawei.touchgame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        startNewGame();
    }




    ArrayList<View> falseViews = new ArrayList<View>();
    private boolean isTouched = false;
    private CountDownTimer timer = null;
    private int level = 1;
    private int successfulTouches = 0;
    private int[] numViews = {4,9,16,25,36};


    private void startNewGame(){
        level = 1;
        score = 0;
        createViews();
    }

    private void startNextLevel() {
        Intent HighScoreScreen = new Intent(GameActivity.this, HigherScorerActivity.class);
        HighScoreScreen.putExtra("score", score);

        Intent levelScreenIntent = new Intent(GameActivity.this, NextLevelScreen.class);
        levelScreenIntent.putExtra("level", level);
        levelScreenIntent.putExtra("score", score);
        startActivity(HighScoreScreen);
        level++;
        createViews();
    }


    private void endGameCheck(){
        if(timer!=null)
            timer.cancel();
        isTouched=false;
        if(falseViews.isEmpty()){
            if(level<5){
                startNextLevel();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                builder.setTitle("congratulation");
                builder.setMessage("you have win the all level");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                //showScoreTable();
            }

        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
            builder.setTitle("congratulation");
            builder.setMessage("your score are " + score);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("No", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }


    }






    public void createViews(){
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        gridLayout.removeAllViews();
        int numberView = numViews[level-1];
        TextView timer_view = findViewById(R.id.timer_view);
        timer_view.setText("Time remaining: 5 seconds");

        TextView level_view = findViewById(R.id.level_view);
        level_view.setText("Level "+level);

        for (int i = 0; i < numberView; i++) {
            // Create a new View
            View view = new View(this);
            Drawable drawable = getDrawable(R.drawable.border);
            view.setBackground(drawable);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(i / (level+1), 1f);
            params.rowSpec = GridLayout.spec(i % (level+1), 1f);
            view.setLayoutParams(params);
            falseViews.add(view);
            // Add the View to the GridLayout
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Check if the touched View is the highlighted View


                    if(!isTouched) {
                        isTouched=true;
                        startTimer();
                    }

                    if (v == highlightedView) {
                        v.setBackgroundColor(Color.BLUE);
                        falseViews.remove(v);
                        score++;
                        TextView scoreTextView = findViewById(R.id.score_text_view);
                        scoreTextView.setText("Score: " + score);
                        if(falseViews.isEmpty()) {
                            endGameCheck();
                        }else{
                            // Highlight a new View
                            highlightNewView();}
                    }
                }


            });


            gridLayout.addView(view);
        }
        highlightNewView();
    }




    View highlightedView;
    private void highlightNewView() {
        highlightedView = falseViews.get(new Random().nextInt(falseViews.size()));
        highlightedView.setBackgroundColor(Color.YELLOW);

    }

    private void startTimer(){
        TextView timer_view = findViewById(R.id.timer_view);
        if (timer != null)
            timer.cancel();

        timer = new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {

                long secondsLeft = millisUntilFinished / 1000;
                timer_view.setText("Time remaining:" + secondsLeft +"seconds");
            }

            public void onFinish() {
                timer_view.setText("Time's up!");
                endGameCheck();

            }
        };
        timer.start();
    }




}