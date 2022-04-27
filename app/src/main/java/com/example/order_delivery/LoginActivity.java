package com.example.order_delivery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (ParseUser.getCurrentUser() != null) {
            goMainActivity(ParseUser.getCurrentUser().getString("type"));
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    //TODO: better error handling
                    Log.e(TAG, "Issues with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show();
                    return;
                }
                //TODO: navigate to the main activity if the user has signed in properly
                String type = user.getString("type");
                System.out.println(type);
                goMainActivity(type);
                Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT);
            }
        });
    }

    private void goMainActivity(String type) {
        //  Log.d(TAG, "Navigating to Main Activity");
        Intent i;
        switch (type) {
            case "customer":
                i = new Intent(this, MenuActivity.class);
                startActivity(i);
                break;
            case "chef":
                i = new Intent(this, ChefActivity.class);
                startActivity(i);
                break;
            case "delivery":
                i = new Intent(this, DeliveryActivity.class);
                startActivity(i);
                break;
            case "manager":
                i = new Intent(this, ManagerActivity.class);
                startActivity(i);
                break;
        }
        finish();
    }
}