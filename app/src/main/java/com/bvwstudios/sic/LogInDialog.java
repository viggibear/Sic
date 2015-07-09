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
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

/**
 * Created by bradley on 4/7/15.
 */
public class LogInDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final LoginActivity loginActivity = (LoginActivity) getActivity();
        AlertDialog.Builder builder  = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_log_in, null);
        final AlertDialog dialog = builder.setView(view)
                .setTitle("Log in")
                .setPositiveButton("Log In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LogInDialog.this.getDialog().cancel();
                    }
                }).create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button buttonPositive = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                buttonPositive.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View buttonView) {
                        final EditText tvUsername = (EditText) view.findViewById(R.id.logIn_name);
                        final EditText tvPassword = (EditText) view.findViewById(R.id.logIn_password);

                        final String username = tvUsername.getText().toString();
                        final String password = tvPassword.getText().toString();

                        ParseUser user = new ParseUser();
                        user.setUsername(username);
                        user.setPassword(password);

                        ParseUser.logInInBackground(username, password, new LogInCallback() {
                            public void done(ParseUser user, ParseException e) {
                                if (user != null) { //hooray
                                    loginActivity.loggedIn();
                                } else { //wrong username/password
                                    Toast.makeText(getActivity(), "There was an error - is your password or username correct?", Toast.LENGTH_LONG)
                                            .show();
                                }
                                dialog.dismiss();
                            }
                        });


                    }
                });
            }
        });
        return dialog;
    }
}
