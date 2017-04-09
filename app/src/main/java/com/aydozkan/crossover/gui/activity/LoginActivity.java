package com.aydozkan.crossover.gui.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.aydozkan.crossover.CrossOverApplication;
import com.aydozkan.crossover.R;
import com.aydozkan.crossover.presenter.AuthenticationPresenter;
import com.aydozkan.crossover.utility.SharedPreferenceWrapper;
import com.aydozkan.crossover.utility.Utility;
import com.aydozkan.crossover.view.AuthenticationView;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity implements AuthenticationView {

    private static final String TAG = LoginActivity.class.getName();
    private AuthenticationPresenter mAuthenticationPresenter;
    private AutoCompleteTextView mEtEmail;
    private EditText mEtPassword;
    private TextView mTvNotMember;
    private Button mBtnLogin;
    private AuthenticationType mAuthenticationType;
    private String mEmail, mPassword;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        //Initialize AuthenticationType to LOGIN
        mAuthenticationType = AuthenticationType.LOGIN;

        //Initialize AuthenticationPresenter
        mAuthenticationPresenter = new AuthenticationPresenter(this);

        //Initialize Views
        mEtEmail = (AutoCompleteTextView) findViewById(R.id.etEmail);

        mEtPassword = (EditText) findViewById(R.id.etPassword);

        mTvNotMember = (TextView) findViewById(R.id.tvNotMember);
        mTvNotMember.setPaintFlags(mTvNotMember.getPaintFlags()
                | Paint.UNDERLINE_TEXT_FLAG);
        mTvNotMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                /*Change AuthenticationType Message and mBtnLogin Text
                according to mAuthenticationType*/
                switch (mAuthenticationType) {
                    case LOGIN:
                        mAuthenticationType = AuthenticationType.REGISTER;

                        mTvNotMember.setText(getString(R.string.already_registered));

                        mBtnLogin.setText(getString(R.string.action_register));
                        break;
                    case REGISTER:
                        mAuthenticationType = AuthenticationType.LOGIN;

                        mTvNotMember.setText(getString(R.string.not_registered));

                        mBtnLogin.setText(getString(R.string.action_login));
                        break;
                    default:
                        break;
                }
            }
        });

        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (isFormValid()) {

                    //Call appropriate authentication service according to mAuthenticationType
                    switch (mAuthenticationType) {
                        case LOGIN:
                            authenticateUser();
                            break;
                        case REGISTER:
                            registerUser();
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        TextView tvAbout = (TextView) findViewById(R.id.tvAbout);
        tvAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // create a WebView with licenses.html
                WebView webView = new WebView(LoginActivity.this);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                webView.loadUrl("file:///android_asset/licenses.html");

                // display the WebView in an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle(getString(R.string.licences))
                        .setView(webView)
                        .setCancelable(true)
                        .show();
            }
        });
    }

    /**
     * Validates email and password.
     * Shows error if occurs.
     *
     * @return Form Validity
     */
    public boolean isFormValid() {

        // Reset errors.
        mEtEmail.setError(null);
        mEtPassword.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mEtEmail.getText().toString();
        mPassword = mEtPassword.getText().toString();

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(mPassword)) {
            mEtPassword.setError(getString(R.string.error_invalid_password));
            return false;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmail)) {
            mEtEmail.setError(getString(R.string.error_field_required));
            return false;
        } else if (!Utility.isValidEmail(mEmail)) {
            mEtEmail.setError(getString(R.string.error_invalid_email));
            return false;
        }

        return true;
    }

    /**
     * Will try to authenticate an existing user
     */
    private void authenticateUser() {
        if (mAuthenticationPresenter != null) {
            mAuthenticationPresenter.authenticateUser(mEmail, mPassword);
        }
    }

    /**
     * AuthenticateUser Service Callback
     *
     * @param accessToken generated Access Token
     */
    @Override
    public void onUserAuthentication(String accessToken) {
        storeAccessToken(accessToken);

        showPlacesActivity();
    }

    /**
     * Will try to register a new user
     */
    private void registerUser() {
        if (mAuthenticationPresenter != null) {
            mAuthenticationPresenter.registerUser(mEmail, mPassword);
        }
    }

    /**
     * RegisterUser Service Callback
     *
     * @param accessToken generated Access Token
     */
    @Override
    public void onUserRegistration(String accessToken) {
        storeAccessToken(accessToken);

        showPlacesActivity();
    }

    /**
     * Encrypt accessToken and store to SharedPreferences
     *
     * @param accessToken access Token returned from Authentication Service
     */
    public void storeAccessToken(String accessToken) {
        try {
            SharedPreferenceWrapper.getInstance().setAccessToken(CrossOverApplication.getInstance().getCrypt().encrypt(accessToken));
        } catch (UnsupportedEncodingException
                | NoSuchAlgorithmException
                | NoSuchPaddingException
                | InvalidAlgorithmParameterException
                | InvalidKeyException
                | InvalidKeySpecException
                | BadPaddingException
                | IllegalBlockSizeException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Opens PlacesActivity
     */
    private void showPlacesActivity() {
        startActivity(new Intent(LoginActivity.this, PlacesActivity.class));
        finish();
    }

    /**
     * AuthenticationType defines whether the user will Log in or Register.
     */
    private enum AuthenticationType {
        LOGIN, REGISTER
    }
}
