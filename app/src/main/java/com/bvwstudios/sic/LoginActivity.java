package com.bvwstudios.sic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by bradley on 4/7/15.
 */
public class LoginActivity extends Activity {

    private Button mSignUp;
    private Button mLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Set up sign up & log in buttons
        mSignUp = (Button) findViewById(R.id.sign_up);
        mLogIn = (Button) findViewById(R.id.log_in);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignUpDialog signUpDialog = new SignUpDialog();
                signUpDialog.show(getFragmentManager(), "SignUp");
            }
        });

        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogInDialog logInDialog = new LogInDialog();
                logInDialog.show(getFragmentManager(), "LogIn");
            }
        });
    }

    public void loggedIn() { //helper function for dialogs
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
