package com.example.authtry2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CharacterCandidateActivity extends AppCompatActivity {

    Button DoreamonBtn;
    Button ShinchanBtn;

    TextView DoreamonText;
    TextView ShinchanText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_candidate);

        DoreamonText = findViewById(R.id.Doreamon);

        ShinchanText = findViewById(R.id.Shinchan);


        DoreamonBtn = findViewById(R.id.btn_Doremon_vote);
        ShinchanBtn= findViewById(R.id.btn_Shinchan_vote);
    }

    public void onDoreamonClicked(View view) {
        String DoreamonCount = DoreamonText.getText().toString().trim();
        int count = Integer.parseInt(DoreamonCount);
        count++;
        DoreamonText.setText(String.valueOf(count));
        Intent intent = new Intent(CharacterCandidateActivity.this, CharVoteActivity.class);
        startActivity(intent);


    }

    public void onShinchanClicked(View view) {
        String ShinchanCount = ShinchanText.getText().toString().trim();
        int count = Integer.parseInt(ShinchanCount);
        count++;
        ShinchanText.setText(String.valueOf(count));
        Intent intent = new Intent(CharacterCandidateActivity.this, CharVoteActivity.class);
        startActivity(intent);

    }
}