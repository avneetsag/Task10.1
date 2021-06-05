package com.example.task101final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    EditText newemail, newpassword;
    Button logging, signing;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Log In");

        mFirebaseAuth= FirebaseAuth.getInstance();
        newemail = findViewById(R.id.user_email);
        newpassword = findViewById(R.id.user_password);
        logging = findViewById(R.id.login_btn);
        signing =findViewById(R.id.signup_btn);


        listener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser!= null){
                    Intent i = new Intent(MainActivity.this,MainActivity2.class);
                    startActivity(i);
                }
                else {
                }
            }
        };
        signing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intologin = new Intent(MainActivity.this, UserSIgnUpActivity.class);
                startActivity(intologin);
            }
        });
        logging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mailid = newemail.getText().toString();
                String pwd = newpassword.getText().toString();

                if (mailid.isEmpty()){
                    newemail.setError("Please enter the email");
                    newemail.requestFocus();
                }
                else if (pwd.isEmpty()){
                    newpassword.setError("Please Enter user Password");
                    newpassword.requestFocus();
                }
                else if (mailid.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fields are Empty!!",Toast.LENGTH_SHORT).show();
                }
                else if (!(mailid.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.signInWithEmailAndPassword(mailid,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, " Error!!!",Toast.LENGTH_SHORT).show();
                            }
                            else{

                                Intent intoHome = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intoHome);
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Error Occurred!!", Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(listener);
    }

}