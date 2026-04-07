package com.example.a4pics1word;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



//test push
public class MainActivity extends AppCompatActivity {

    Button playBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playBtn = findViewById(R.id.PlayButton);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playActivity();
            }
        });
    }

    public void playActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}