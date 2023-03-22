package com.huawei.touchgame;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HigherScorerActivity extends AppCompatActivity {
    private SQLiteAdapter mySQLiteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_higher_scorer);

        mySQLiteAdapter = new SQLiteAdapter(this);
        mySQLiteAdapter.openToWrite();
        int finalScore=0;
        Intent in = getIntent();
        Bundle info = in.getExtras();


        if(info!=null)
        {
            finalScore = (int)info.get("score");
            TextView showScore = findViewById(R.id.final_score_text);
            showScore.setText("Your score: "+finalScore);
        }


        Button saveButton = findViewById(R.id.save_name_button);

        int finalScore1 = finalScore;
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText saveName = findViewById(R.id.save_name_text);
                String name = saveName.getText().toString();

                if(TextUtils.isEmpty(name)){

                    Toast.makeText(HigherScorerActivity.this,"Empty Name field not allowed!",Toast.LENGTH_SHORT).show();
                }
                else{
                    mySQLiteAdapter.insert(name, finalScore1);
                    Toast.makeText(HigherScorerActivity.this,"Your score have been recorded",Toast.LENGTH_SHORT).show();
                    Intent homepage = new Intent(HigherScorerActivity.this,MainActivity.class);
                    startActivity(homepage);
                }
            }
        });


    }
}