package com.huawei.touchgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NextLevelScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_level_screen);

        Intent in = getIntent();
        Bundle info = in.getExtras();

        if(info!=null)
        {
            int currentLevel = (int)info.get("level");
            int currentScore = (int)info.get("score");

            TextView showLevel = findViewById(R.id.level_completed);
            TextView showScore = findViewById(R.id.score_text);

            showLevel.setText("Level "+currentLevel+" Completed!");
            showScore.setText("Your score: "+currentScore);
        }

        Button nextLevel = findViewById(R.id.next_level_button);

        nextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent BackMainMenu = new Intent(NextLevelScreen.this, MainActivity.class);
        Button exitButton = findViewById(R.id.exit_button);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(BackMainMenu);
                Toast.makeText(getBaseContext(),"Back to Menu",Toast.LENGTH_SHORT).show();
            }
        });

        
    }
}