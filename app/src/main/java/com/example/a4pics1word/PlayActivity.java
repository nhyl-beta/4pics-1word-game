package com.example.a4pics1word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.prefs.BackingStoreException;

public class PlayActivity extends AppCompatActivity {

    Button backBtn;
    String correctAnswer= "Mouse";
    String userInput;
    TextView answerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Back Button
        backBtn = findViewById(R.id.BackButton);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playActivity();
            }
        });

        //Display's Answer don sa _ _ _ _ _ hehehe
        answerText = findViewById(R.id.Answer_TV);
        answerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswerDisplay();
            }
        });
    }
    public void playActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void updateAnswerDisplay () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}