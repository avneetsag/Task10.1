package com.example.task101final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class UserSIgnUpActivity extends AppCompatActivity {

    EditText namename, newemail, newpassword, contact,address;
    Button newregister;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //getSupportActionBar().setTitle("Sign Up");

        mFirebaseAuth = FirebaseAuth.getInstance();
        namename = findViewById(R.id.name);
        newpassword = findViewById(R.id.email);
        address = findViewById(R.id.address);
        newregister = findViewById(R.id.button);
        newemail = findViewById(R.id.email);
        contact= findViewById(R.id.phone);
        newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailid = newemail.getText().toString();
                String pwd = newpassword.getText().toString();
                String fullName = namename.getText().toString();
                String contactno= contact.getText().toString();
                String addr = address.getText().toString();
                if(fullName.isEmpty()){
                    namename.setError("Please enter the Name");
                    namename.requestFocus();
                }
                else if (mailid.isEmpty()){
                    newemail.setError("Please enter the email");
                    newemail.requestFocus();
                }
                else if (mailid.isEmpty()){
                    address.setError("Please enter the address");
                    address.requestFocus();
                }
                else if (contactno.isEmpty()){
                    newpassword.setError("Please enter the contact no.");
                    newpassword.requestFocus();
                }
                else if (pwd.isEmpty()){
                    newpassword.setError("Please Enter the Password.");
                    newpassword.requestFocus();
                }
                else {
                    mFirebaseAuth.createUserWithEmailAndPassword(mailid, pwd).addOnCompleteListener(UserSIgnUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(UserSIgnUpActivity.this, "Registration Unsuccessful, Please Try again!", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(UserSIgnUpActivity.this, MainActivity.class));
                            }
                        }
                    });
                }
            }
        });
    }
}