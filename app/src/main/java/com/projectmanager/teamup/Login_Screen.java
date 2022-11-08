package com.projectmanager.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Screen extends AppCompatActivity {
EditText email,password;
Button Sign_in;
TextView Sign_Up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        email = findViewById(R.id.emailEdit);
        password= findViewById(R.id.passwordEdit);
        Sign_in = findViewById(R.id.Login);
        Sign_Up = findViewById(R.id.sigup_l);
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);
        Sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if(databaseHelper.teamUpModelDao().checkDetail(Email,Password)){
                    showMassage("Login Successfully!!");
                }
                else if(Email.isEmpty()||Email==""){
                    showMassage("Email Field not be Empty");
                }
                else if(Password.isEmpty()||Password==""){
                    showMassage("Password Field not be Empty");
                }
                else {
                    showMassage("Email and Password is Not Match from DATABASE");
                }
            }
        });

        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_Screen.this,Registration_Screen.class));
                finish();
            }
        });

    }
    void showMassage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}