package com.codedimension.dialogfragmenttask1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    public TextView textUserName;
    public static SharedPreferences sharedPreferences;
    private Boolean SignInStatus;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences("filename", Context.MODE_PRIVATE);
        toolbar = (Toolbar) findViewById(R.id.mainToolbsr);
        setSupportActionBar(toolbar);
        textUserName = (TextView) findViewById(R.id.textUserName);
        SignInStatus = sharedPreferences.getString("stay", "ON_DATA1").equals("yes");
        userName=sharedPreferences.getString("userName", "ON_DATA3");

        if (SignInStatus) {
            textUserName.setText(userName);
        } else {
            showLogInDialog();
        }
    }

    void showLogInDialog() {
        LoginDialogFragment loginDialogFragment = new LoginDialogFragment();
        loginDialogFragment.show(getSupportFragmentManager(), null);
    }
}
