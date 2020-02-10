package com.example.mylogin;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private static final String EMAIL = "email";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if(isLoggedIn){
            Toast.makeText(this, "your are already logged in", Toast.LENGTH_SHORT).show();
        }

//        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));


        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
        // If you are using in a fragment, call loginButton.setFragment(this);
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Toast.makeText(MainActivity.this, "on success" + loginResult.getAccessToken().getToken(), Toast.LENGTH_SHORT).show();
                // App code
            }
            @Override
            public void onCancel() {

                // App code
            }
            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(MainActivity.this, "on error" + exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.d("onError", "onError: " + exception.getLocalizedMessage());
                // App code
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


}
