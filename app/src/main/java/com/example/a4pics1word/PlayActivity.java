package com.example.a4pics1word;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.prefs.BackingStoreException;

public class PlayActivity extends AppCompatActivity {

    Button backBtn;

    String correctAnswer = "MOUSE";
    StringBuilder userInput;
    String userAnswer;

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

        //
        Button[] emptyButtons = new Button[] {
                findViewById(R.id.buttonAnswer1),
                findViewById(R.id.buttonAnswer2),
                findViewById(R.id.buttonAnswer3),
                findViewById(R.id.buttonAnswer4),
                findViewById(R.id.buttonAnswer5)
        };

        Button[] letterButtons = new Button[]{
                findViewById(R.id.buttonE),
                findViewById(R.id.buttonA),
                findViewById(R.id.buttonO),
                findViewById(R.id.buttonS),
                findViewById(R.id.buttonU),
                findViewById(R.id.buttonL),
                findViewById(R.id.buttonM),
                findViewById(R.id.buttonR)
        };

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button clickedLetter = (Button) v;

                for (int i = 0; i < emptyButtons.length; i++) {
                    if (emptyButtons[i].getText().toString().isEmpty()) {

                        emptyButtons[i].setText(clickedLetter.getText().toString());

                        clickedLetter.setBackgroundColor(Color.parseColor("#FF0000"));
                        clickedLetter.setEnabled(false);

                        checkAnswer(emptyButtons);
                        return;
                    }
                }
            }
        };

        for (Button btn : letterButtons) {
            btn.setOnClickListener(listener);
        }

        View.OnClickListener undoListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button emptyClicked = (Button) v;
                String textToUndo = emptyClicked.getText().toString();

                if (!textToUndo.isEmpty()) {
                    emptyClicked.setText("");


                    for (Button letterBtn : letterButtons) {
                        if (letterBtn.getText().toString().equals(textToUndo) && !letterBtn.isEnabled()) {
                            letterBtn.setEnabled(true);
                            letterBtn.setBackgroundColor(Color.parseColor("#6200EA"));
                            break;
                        }
                    }
                }
            }
        };

        for (Button btn : emptyButtons) {
            btn.setOnClickListener(undoListener);
        }
    }

    private void showCorrectDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Correct!")
                .setMessage("Nice! Proceed to next level?")

                .setPositiveButton("Next Level", (dialog, which) -> {

                })

                .setNegativeButton("Leave", (dialog, which) -> {
                    finish();
                })

                .setCancelable(false)
                .show();
    }

    public void playActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void checkAnswer(Button[] emptyButtons) {
        userInput = new StringBuilder();

        for (Button btn : emptyButtons) {
            String text = btn.getText().toString();
            if (text.isEmpty()) {
                return;
            }
            userInput.append(text);
        }

        String userAnswer = userInput.toString();

        if (userAnswer.equals(correctAnswer)) {
            showCorrectDialog();
        }
    }
}