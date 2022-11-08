package com.projectmanager.teamup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Registration_Screen extends AppCompatActivity {
    EditText username, email, password, c_password;
    Button SignUp;
    TextView sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_screen);
        username = findViewById(R.id.usernameEdit);
        email = findViewById(R.id.emailEdit_r);
        password = findViewById(R.id.passwordEdit_r);
        c_password = findViewById(R.id.passwordEdit_r_c);
        SignUp = findViewById(R.id.sigup);
        sign_in = findViewById(R.id.sign);
        DatabaseHelper databaseHelper = DatabaseHelper.getDB(this);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Username = username.getText().toString();
                String Email = email.getText().toString().toLowerCase();
                String Password = password.getText().toString();
                String Cpassword = c_password.getText().toString();
                if ((Username.isEmpty())||Username=="") {
                    showMassage(" UserName Edit must Not Empty");
                }
                else if(Email.isEmpty() || Username==""){
                    showMassage(" EmailName Edit must Not Empty");
                }
                else if(Password.isEmpty()||Password==""){
                    showMassage(" Password Edit must Not Empty");
                }
                else if (Cpassword.isEmpty()|Cpassword==""){
                    showMassage(" Confirm Password Edit must Not Empty");
                }
                else if(!Password.equals(Cpassword)){
                    showMassage("Confirm is Not Same");
                }
                else {
                    databaseHelper.teamUpModelDao().UserData(new TeamUpModel(Username, Email, Password, Cpassword));
                    startActivity(new Intent(Registration_Screen.this, Login_Screen.class));
                    showMassage("Registration Successfully!!");
                    username.setText("");
                    email.setText("");
                    password.setText("");
                    c_password.setText("");
                }


            }
        });
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registration_Screen.this,Login_Screen.class));
                finish();
            }
        });
    }

    void showMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}