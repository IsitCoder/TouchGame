package com.huawei.touchgame;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the GridLayout
        GridLayout gridLayout = findViewById(R.id.grid_layout);
        int level =1;

        for (int i = 0; i < 4; i++) {
            // Create a new View
            View view = new View(this);
            // Set the background color to gray

            Drawable drawable = getDrawable(R.drawable.border);
            view.setBackground(drawable);
            // Set the layout parameters for the View
            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 0;
            params.height = 0;
            params.columnSpec = GridLayout.spec(i/2,1f);
            params.rowSpec = GridLayout.spec(i%2,1f);
            // Add the View to the GridLayout
            gridLayout.addView(view, params);
        }

        View highlightedView;
        highlightedView = gridLayout.getChildAt(new Random().nextInt(4));
        highlightedView.setBackgroundColor(Color.YELLOW);

    }

}