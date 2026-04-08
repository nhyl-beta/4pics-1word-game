package com.example.a4pics1word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

        final int[] currentIndex = {0};


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex[0] < emptyButtons.length) {
                    Button clicked = (Button) v;
                    emptyButtons[currentIndex[0]].setText(clicked.getText().toString());
                    clicked.setBackgroundColor(Color.parseColor("#FF0000"));
                    clicked.setEnabled(false);
                    currentIndex[0]++;
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

                    if (currentIndex[0] > 0) currentIndex[0]--;

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
    public void playActivity () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void updateAnswerDisplay () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}