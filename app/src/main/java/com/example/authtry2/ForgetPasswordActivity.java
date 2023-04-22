package com.example.authtry2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.text.BreakIterator;

public class ForgetPasswordActivity extends AppCompatActivity
{
    EditText etEmail;
    Button sendEmail;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        etEmail = findViewById(R.id.reset_pass);
        sendEmail = findViewById(R.id.reset_btn);
        mAuth = FirebaseAuth.getInstance();
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                if(TextUtils.isEmpty(email))
                {
                    etEmail.setError("enter your email");
                    etEmail.requestFocus();
                    Toast.makeText(ForgetPasswordActivity.this,"Please enter your email",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    etEmail.setError("enter your valid email");
                    etEmail.requestFocus();
                    Toast.makeText(ForgetPasswordActivity.this,"Please enter valid  email",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    mAuth.sendPasswordResetEmail(email ).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgetPasswordActivity.this,"Please check your  email",Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ForgetPasswordActivity.this,"SOMETING WENT WRONG",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });

    }


}