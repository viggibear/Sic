package com.bvwstudios.sic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

/**
 * Created by bradley on 4/7/15.
 */
public class SignUpDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LoginActivity loginActivity = (LoginActivity) getActivity();
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_sign_up, null);
        final AlertDialog dialog = builder.setView(view)
                .setTitle("Sign Up")
                .setPositiveButton("Sign Up", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SignUpDialog.this.getDialog().cancel();
                    }
                }).create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button buttonPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View buttonView) {
                        final EditText tvUsername = (EditText) view.findViewById(R.id.signUp_name);
                        final EditText tvPassword = (EditText) view.findViewById(R.id.signUp_password);
                        final EditText tvRepeatPassword = (EditText) view.findViewById(R.id.signUp_repeat);

                        final String username = tvUsername.getText().toString();
                        final String password = tvPassword.getText().toString();

                        if (!password.equals(tvRepeatPassword.getText().toString())) {
                            tvRepeatPassword.setError("passwords are not the same");
                            return;
                        }

                        if (password.trim().length() < 6) {
                            tvPassword.setError("password is too short");
                            return;
                        }

                        if (username.trim().length() < 3 || username.trim().length() > 15) {
                            tvUsername.setError("username must be 3 to 15 characters long");

                        }

                        if (!username.matches("^[a-zA-Z0-9_-]{3,15}$")) {
                            tvUsername.setError("username must comprise of alphanumeric characters, underscores and hyphens");
                            return;
                        }

                        ParseUser user = new ParseUser();
                        user.setUsername(username);
                        user.setPassword(password);

                        user.signUpInBackground(new SignUpCallback() {
                            @Override
                            public void done(ParseException e) {
                                if (e == null) {
                                    ParseUser.logInInBackground(username, password, new LogInCallback() {
                                        public void done(ParseUser user, ParseException e) {
                                            if (user != null) {
                                                loginActivity.loggedIn();
                                            } else {

                                            }
                                        }
                                    });
                                    dialog.dismiss();
                                } else if (e.getCode() == ParseException.USERNAME_TAKEN) {
                                    tvUsername.setError("username taken");
                                }
                            }
                        });

                        dialog.dismiss();
                    }
                });
            }
        });
        return dialog;
    }
}
