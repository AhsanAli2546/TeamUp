package com.projectmanager.teamup.Activity_Screen;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.projectmanager.teamup.R;

public class Login_Screen extends AppCompatActivity {

    // creating variable for edit text, textview,
    // button, progress bar and firebase auth.
    private EditText userNameEdt, passwordEdt;
    private Button loginBtn;
    private TextView newUserTV, ForgetPasswordTV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        // initializing all our variables.
        userNameEdt = findViewById(R.id.idEdtUserName);
        passwordEdt = findViewById(R.id.idEdtPassword);
        loginBtn = findViewById(R.id.idBtnLogin);
        newUserTV = findViewById(R.id.idTVNewUser);
        ForgetPasswordTV = findViewById(R.id.ForgetPassword);
        mAuth = FirebaseAuth.getInstance();
        loadingPB = findViewById(R.id.idProgressBar);
        // adding click listener for our new user tv.
        newUserTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line opening a login activity.
                Intent i = new Intent(Login_Screen.this, Register_Screen.class);
                startActivity(i);
            }
        });

        // adding on click listener for our login button.
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hiding our progress bar.
                loadingPB.setVisibility(View.VISIBLE);
                // getting data from our edit text on below line.
                String email = userNameEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                // on below line validating the text input.
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_Screen.this, "Please enter your credentials..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // on below line we are calling a sign in method and passing email and password to it.
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // on below line we are checking if the task is success or not.
                        if (task.isSuccessful()) {
                            // on below line we are hiding our progress bar.
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(Login_Screen.this, "Login Successful..", Toast.LENGTH_SHORT).show();
                            // on below line we are opening our mainactivity.
                            Intent i = new Intent(Login_Screen.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        } else {
                            // hiding our progress bar and displaying a toast message.
                            loadingPB.setVisibility(View.GONE);
                            Toast.makeText(Login_Screen.this, "Please enter valid user credentials..", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        ForgetPasswordTV.setOnClickListener(view -> {

            showRecoverPasswordDialog();
            loadingPB.setVisibility(View.VISIBLE);
        });
    }

    private void showRecoverPasswordDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        ConstraintLayout linearLayout=new ConstraintLayout(this);
        final EditText emailet= new EditText(this);
        emailet.setText("Email");
        emailet.setMinEms(16);
        emailet.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        linearLayout.addView(emailet);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);
        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String email=emailet.getText().toString().trim();
                beginRecovery(email);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void beginRecovery(String email) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful())
                {
                    // if isSuccessful then done message will be shown
                    // and you can change the password

                    loadingPB.setVisibility(View.GONE);
                    Toast.makeText(Login_Screen.this,"Done sent",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(Login_Screen.this,"Error Occurred",Toast.LENGTH_LONG).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                loadingPB.setVisibility(View.GONE);
                Toast.makeText(Login_Screen.this,"Error Failed: "+e.getMessage() , Toast.LENGTH_LONG).show();
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        // in on start method checking if
//        // the user is already sign in.
//        FirebaseUser user = mAuth.getCurrentUser();
//        if (user != null) {
//            // if the user is not null then we are
//            // opening a main activity on below line.
//            Intent i = new Intent(Login_Screen.this, MainActivity.class);
//            startActivity(i);
//            this.finish();
//        }
//    }
}