package com.example.authtry2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClassRepAactivity extends AppCompatActivity {

    Button DoreamonBtn;
    Button ShinchanBtn;

    TextView DoreamonText;
    TextView ShinchanText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_rep_aactivity);

        DoreamonText = findViewById(R.id.Doreamon);//boy

        ShinchanText = findViewById(R.id.Shinchan);//girl


        DoreamonBtn = findViewById(R.id.btn_Doremon_vote);//boy
        ShinchanBtn= findViewById(R.id.btn_Shinchan_vote);//girl
    }

    public void onboyClicked(View view) {
        String DoreamonCount = DoreamonText.getText().toString().trim();
        int count = Integer.parseInt(DoreamonCount);
        count++;
        DoreamonText.setText(String.valueOf(count));
        Intent intent = new Intent(ClassRepAactivity.this, RepvoteActivity.class);
        startActivity(intent);
    }

    public void ongirlClicked(View view) {
        String ShinchanCount = ShinchanText.getText().toString().trim();
        int count = Integer.parseInt(ShinchanCount);
        count++;
        ShinchanText.setText(String.valueOf(count));
        Intent intent = new Intent(ClassRepAactivity.this, RepvoteActivity.class);
        startActivity(intent);
    }
}