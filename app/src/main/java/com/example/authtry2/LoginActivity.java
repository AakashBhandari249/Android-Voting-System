package com.example.authtry2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    Button regbutton;
    EditText useremail,userpassword,UserName;
    Button userlogin;
    TextView forgotPassword;
    FirebaseUser currentUser;//used to store current user of account
    FirebaseAuth mAuth;//Used for firebase authentication

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regbutton=findViewById(R.id.reg_btn);

        regbutton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });

        useremail =findViewById(R.id.user_email);
        userpassword =findViewById(R.id.user_password);
        UserName=findViewById(R.id.username);
        userlogin=findViewById(R.id.Login_btn);
        forgotPassword=findViewById(R.id.forget_password);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        userlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                AllowUserToLogin();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent forgetIntent = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(forgetIntent);
            }
        });
    }

    private void AllowUserToLogin()
    {
        String email = useremail.getText().toString().trim();
        String usname = UserName.getText().toString().trim();
        String pwd = userpassword.getText().toString();

        if(TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches() && TextUtils.isEmpty(pwd) )
        {
            Toast.makeText(LoginActivity.this,"Please enter   email  and password",Toast.LENGTH_SHORT).show();
        }

        else if(!(TextUtils.isEmpty(email)  && TextUtils.isEmpty(pwd) && TextUtils.isEmpty(usname)))
        {
            mAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful()){
                                sendToHomeActivity();
                                Toast.makeText(LoginActivity.this,"WELCOME TO ANDROID VOTING SYSTEM",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(LoginActivity.this,"Error:",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }

        else
        {
            Toast.makeText(LoginActivity.this,"enter valid detail",Toast.LENGTH_SHORT).show();
        }
    }

    private void sendToHomeActivity()
    {
        Intent  Homeintent = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(Homeintent);
    }
}