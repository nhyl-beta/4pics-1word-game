package com.example.a4pics1word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PlayActivity2 extends PlayActivity {

    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play2);

        backBtn = findViewById(R.id.BackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playActivity();
            }
        });

        correctAnswer = "TACKLE";
        level = 2;

        Button[] emptyButtons = new Button[] {
                findViewById(R.id.buttonAnswer1),
                findViewById(R.id.buttonAnswer2),
                findViewById(R.id.buttonAnswer3),
                findViewById(R.id.buttonAnswer4),
                findViewById(R.id.buttonAnswer5),
                findViewById(R.id.buttonAnswer6)
        };

        Button[] letterButtons = new Button[]{
                findViewById(R.id.buttonE),
                findViewById(R.id.buttonA),
                findViewById(R.id.buttonO),
                findViewById(R.id.buttonK),
                findViewById(R.id.buttonT),
                findViewById(R.id.buttonL),
                findViewById(R.id.buttonM),
                findViewById(R.id.buttonC)
        };
        inputChecker(emptyButtons, letterButtons);
        inputReducer(emptyButtons, letterButtons);
    }

    public void playActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}