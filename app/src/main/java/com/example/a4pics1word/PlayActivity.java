package com.example.a4pics1word;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Array;

public class PlayActivity extends AppCompatActivity {

    Button backBtn;

    protected String correctAnswer = "MOUSE";
    protected StringBuilder userInput;
    protected int level = 1;

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

        inputChecker(emptyButtons, letterButtons);
        inputReducer(emptyButtons, letterButtons);

    }

    public void inputChecker(Button[] emptyButtons, Button[] letterButtons){

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
    }

    public void inputReducer(Button[] emptyButtons, Button[] letterButtons){
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

    public void showCorrectDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Correct!")
                .setMessage("Nice! Proceed to next level?")

                .setPositiveButton("Next Level", (dialog, which) -> {
                    
                    Class<?> nextActivity = null;

                    if (level == 1) nextActivity = PlayActivity2.class;
                    else if (level == 2) nextActivity = PlayActivity3.class;
                    else if (level == 3) nextActivity = PlayActivity4.class;
                    else if (level == 4) nextActivity = PlayActivity5.class;
                    else if (level == 5) nextActivity = PlayActivity.class;
                    Intent intent = new Intent(this, nextActivity);
                    startActivity(intent);
                })

                .setNegativeButton("Leave", (dialog, which) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })

                .setCancelable(false)
                .show();
    }

    public void showLastDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Perfect!")
                .setMessage("Nice! You completed the game")


                .setNegativeButton("Leave", (dialog, which) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })

                .setCancelable(false)
                .show();
    }

    public void playActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void checkAnswer(Button[] emptyButtons) {
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
            if (level < 5){showCorrectDialog();}
            else {showLastDialog();}

        }
    }
}