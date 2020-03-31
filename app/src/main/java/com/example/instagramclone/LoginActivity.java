package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtEmail, edtUsername, edtPassword;
    private Button btnSignup, btnLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log In");

        edtEmail = findViewById(R.id.edtLoginEmail);
        //edtUsername = findViewById(R.id.edtLoginUsername);
        edtPassword = findViewById(R.id.edtLoginPwd);
        btnLogIn = findViewById(R.id.btnLoginActivity);
        btnSignup = findViewById(R.id.btnSignupActivity);

        btnSignup.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        if(ParseUser.getCurrentUser() != null) {
            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("signin in process ");
        progressDialog.show();
        switch (view.getId()){
            case R.id.btnLoginActivity:
                if(edtEmail.getText().toString().equals("") ||  edtPassword.getText().toString().equals("")){
                    progressDialog.dismiss();
                    FancyToast.makeText(LoginActivity.this,"Enter username and password.", FancyToast.LENGTH_LONG,FancyToast.INFO,true).show();
                    transitionToSocialMediaActivity();
                }
                else {
                    ParseUser.logInInBackground(edtEmail.getText().toString(), edtPassword.getText().toString(),
                            new LogInCallback() {
                                @Override
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null && e == null) {
                                        FancyToast.makeText(LoginActivity.this, "Login Successful", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true);
                                        transitionToSocialMediaActivity();
                                    }
                                    else {
                                        FancyToast.makeText(LoginActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                                    }
                                    progressDialog.dismiss();
                                }
                            });
                }
                break;
            case R.id.btnSignupActivity:
                Intent intent = new Intent(LoginActivity.this,LoginActivity.class);
                startActivity(intent);
                progressDialog.dismiss();
                break;
        }
    }

    public void rootLayoutTapped(View view){
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void transitionToSocialMediaActivity(){
        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);
    }
}
