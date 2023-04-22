package com.example.authtry2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity
{
    EditText Name,userPassword,userEmail, UserName;
    Button register;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        Name = (EditText) findViewById(R.id.name);
        UserName = (EditText) findViewById(R.id.username);
        userEmail = (EditText) findViewById(R.id.user_email);
        userPassword = (EditText) findViewById(R.id.user_password);
        register = (Button) findViewById(R.id.Sign_Up_btn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount()
    {
        String name = Name.getText().toString().trim();
        String usname = UserName.getText().toString().trim();
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        if(TextUtils.isEmpty(name))
        {
            Name.requestFocus();
            Name.setError("enter your name");
        }
        else if(TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            userEmail.requestFocus();
            userEmail.setError("enter your email");
        }
        else if(TextUtils.isEmpty(password))
        {
            userPassword.requestFocus();
            userPassword.setError("enter your password");
        }
        else if(TextUtils.isEmpty(usname))
        {
            UserName.requestFocus();
            UserName.setError("enter username");
        }
        else
        {
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if(task.isSuccessful())//If account creation successful print message and send user to Login Activity
                            {
                                SaveUserData();
                                Toast.makeText(SignUpActivity.this,"WELCOME TO ANDROID VOTING SYSTEM",Toast.LENGTH_SHORT).show();
                                PhoneVerificationActivity();
                            }

                            else//Print the error message incase of failure
                            {
                                Toast.makeText(SignUpActivity.this,"Error: ",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

    private void SaveUserData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Dataset");
        String nameD = Name.getText().toString().trim();
        String usernameD = UserName.getText().toString().trim();
        String emailD = userEmail.getText().toString().trim();
        String passwordD = userPassword.getText().toString().trim();
        UserInformation userinfo = new UserInformation(nameD,emailD,passwordD,usernameD);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                databaseReference.child("usernameD").setValue(userinfo);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(SignUpActivity.this, "Fail to add data " + error, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void PhoneVerificationActivity()
    {
        Intent loginIntent = new Intent(SignUpActivity.this,VerificationActivity.class);
        startActivity(loginIntent);
    }
}










