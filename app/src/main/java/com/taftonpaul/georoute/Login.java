package com.taftonpaul.georoute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    /* /created button and edit texts below */
    Button Login_btn,Signup_btn;
    EditText Email_edtx, Password_edtx;
    private FirebaseAuth mAuth;         //pasted code from tutorial
    private FirebaseUser currentUser;   //added this user = keep private
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();     //pasted code from tutorial
        currentUser = mAuth.getCurrentUser();  //pasted code from tutorial
        //added the code below for user input
        Login_btn = findViewById(R.id.loginBtn);
        Signup_btn = findViewById(R.id.signUpBtn);
        Email_edtx = findViewById(R.id.email);
        Password_edtx = findViewById(R.id.password);
        checkbox = findViewById(R.id.checkBoxing);

        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    // show password
                    Password_edtx.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    // hide password
                    Password_edtx.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        Login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
                //code to get user input for username and password below
                String email  = Email_edtx.getText().toString();
                String password = Password_edtx.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                    currentUser = mAuth.getCurrentUser();
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    //i.putExtra("Email",username); //code for carrying over email variable stored to next activity
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    currentUser = null;
                                }

                                // ...
                            }
                        });
            }
        });

        //Sign up code below
        Signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code to get user input for username and password below
                String email = Email_edtx.getText().toString();
                String password = Password_edtx.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Signup Successful", Toast.LENGTH_SHORT).show();
                                    currentUser = mAuth.getCurrentUser();
                                    Intent i = new Intent(Login.this, MainActivity.class);
                                    startActivity(i);
                                    //i.putExtra("Email", username); //code for carrying over email variable stored to next activity
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                                    currentUser = null;
                                }

                                // ...
                            }
                        });
            }
        });

    }
}