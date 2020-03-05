package com.example.mylogin.kotlin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin.R

import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

import java.util.Arrays

class MainKotlinActivity : AppCompatActivity() {

    private var callbackManager: CallbackManager? = null
//    private var loginButton: LoginButton? = null
//    private val progressBar: ProgressBar? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private val RC_SIGN_IN = 1111


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val accessToken = AccessToken.getCurrentAccessToken()
        val isLoggedIn = accessToken != null && !accessToken.isExpired

        if (isLoggedIn) {
            val intent = Intent(this@MainKotlinActivity, HomeKotlinActivity::class.java)
            startActivity(intent)
            //            Toast.makeText(this, "your are already logged in", Toast.LENGTH_SHORT).show();
        }

        //        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));


        callbackManager = CallbackManager.Factory.create()

//        loginButton = findViewById<View>(R.id.login_button) as LoginButton
        //        progressBar = findViewById(R.id.progressBar);
//        loginButton!!.setReadPermissions(Arrays.asList(EMAIL))
//        // If you are using in a fragment, call loginButton.setFragment(this);
//        // Callback registration
//        loginButton!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
//            override fun onSuccess(loginResult: LoginResult) {
//
//                val intent = Intent(this@MainKotlinActivity, HomeScreenActivity::class.java)
//                startActivity(intent)
//                //                Toast.makeText(MainActivity.this, "on success" + loginResult.getAccessToken().getToken(), Toast.LENGTH_SHORT).show();
//                // App code
//            }
//
//            override fun onCancel() {
//
//                // App code
//            }
//
//            override fun onError(exception: FacebookException) {
//                Toast.makeText(
//                    this@MainKotlinActivity,
//                    "on error" + exception.localizedMessage!!,
//                    Toast.LENGTH_SHORT
//                ).show()
//                Log.d("onError", "onError: " + exception.localizedMessage!!)
//                // App code
//            }
//        })

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val account = GoogleSignIn.getLastSignedInAccount(this)
        updateUI(account)

        val signInButton = findViewById<SignInButton>(R.id.sign_in_button)
        signInButton.setSize(SignInButton.SIZE_STANDARD)

        findViewById<View>(R.id.sign_in_button).setOnClickListener {
            val signInIntent = mGoogleSignInClient!!.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }

    private fun updateUI(account: GoogleSignInAccount?) {
        if (account != null) {
            val intent = Intent(this@MainKotlinActivity, HomeKotlinActivity::class.java)
            startActivity(intent)
            //            Toast.makeText(this, "already sign in", Toast.LENGTH_SHORT).show();
            //already sign in
        } else {
            //            Toast.makeText(this, "not sign in", Toast.LENGTH_SHORT).show();
            //not sign in
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            // Signed in successfully, show authenticated UI.
            updateUI(account)
        } catch (e: ApiException) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("google sign in", "signInResult:failed code=" + e.statusCode)
            updateUI(null)
        }

    }

    companion object {
        private val EMAIL = "email"
    }


}
