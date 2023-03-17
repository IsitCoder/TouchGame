package com.huawei.touchgame;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the GridLayout


        int numViews=16;
        createViews(numViews);
        highlightNewView(numViews);
        TextView scoreTextView = findViewById(R.id.score_text_view);
        scoreTextView.setText("Score: " + score);


    }


    ArrayList<View> falseViews = new ArrayList<View>();


    private void createViews(int numberView){

        for (int i = 0; i < numberView; i++) {
            // Create a new View
            View view = new View(this);
            Drawable drawable = getDrawable(R.drawable.border);
            view.setBackground(drawable);

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(i / 2, 1f);
            params.rowSpec = GridLayout.spec(i % 2, 1f);
            view.setLayoutParams(params);
            falseViews.add(view);
            // Add the View to the GridLayout
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    // Check if the touched View is the highlighted View
                    if (v == highlightedView) {
                        // Increment the score
                        v.setTag(true);
                        v.setBackgroundColor(Color.BLUE);
                        falseViews.remove(v);



                        score++;
                        TextView scoreTextView = findViewById(R.id.score_text_view);
                        scoreTextView.setText("Score: " + score);
                        // Highlight a new View
                        highlightNewView(numberView);
                    }
                    return true;
                }
            });

            GridLayout gridLayout = findViewById(R.id.grid_layout);
            gridLayout.addView(view);
        }
    }




    View highlightedView;
    private void highlightNewView(int numViews) {

        highlightedView = falseViews.get(new Random().nextInt(falseViews.size()));
        highlightedView.setBackgroundColor(Color.YELLOW);
    }

    int count=0;



}