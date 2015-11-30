package com.bugs.bugs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginActivity extends Activity {
    Button btnLogin;
    private LoginButton loginButton;
    private TextView info;
    private CallbackManager callbackManager;

    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        callbackManager = CallbackManager.Factory.create();
        FacebookSdk.sdkInitialize(getApplicationContext());
        btnLogin = (Button) findViewById(R.id.btnLogin);
        info = (TextView)findViewById(R.id.info);
        loginButton = (LoginButton)findViewById(R.id.login_button);
        btnSignUp= (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });
                btnLogin.setOnClickListener(new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText email = (EditText) findViewById(R.id.txtEmail);
                        EditText password = (EditText) findViewById(R.id.txtPassword);
                        CharSequence emailValidating = email.getText().toString();

                        if (email.getText().toString().equals("") && password.getText().toString().equals(""))
                            Toast.makeText(getApplicationContext(), "Make sure you have entered both your email and password", Toast.LENGTH_LONG).show();
                        else if (email.getText().toString().equals(""))
                            Toast.makeText(getApplicationContext(), "Make sure you have entered your email", Toast.LENGTH_LONG).show();
                        else if (password.getText().toString().equals(""))
                            Toast.makeText(getApplicationContext(), "Make sure you have entered your password", Toast.LENGTH_LONG).show();
                        else if(!isEmailValid(emailValidating))
                            Toast.makeText(getApplicationContext(), "Make sure you have entered a valid email", Toast.LENGTH_LONG).show();
                        else if (password.getText().toString().length()<6)
                            Toast.makeText(getApplicationContext(), "your password couldn't be less than 6 characters", Toast.LENGTH_LONG).show();
                        else if(!email.getText().toString().equals("") && !password.getText().toString().equals("")){
                            startActivity(new Intent(LoginActivity.this, profile.class));
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }
    public void FBlogin(View view) {
//        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                info.setText(
                        "User ID: "
                                + loginResult.getAccessToken().getUserId()
                                + "\n" +
                                "Auth Token: "
                                + loginResult.getAccessToken().getToken()
                );
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                info.setText("Login attempt failed.");

            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}