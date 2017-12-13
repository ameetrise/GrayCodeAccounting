package com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.Activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitbhattarai.graycodeaccounting.Navigation.NavigationDrawerPackage.SupportClasses.Pref;
import com.example.amitbhattarai.graycodeaccounting.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private static final int REQUEST_READ_CONTACTS = 0;
    private AutoCompleteTextView companyName;
    private EditText passwordView;
    private EditText username;
    Button sign_in;
    private View mProgressView;
    private View mLoginFormView;
    TextView forgot_password;
    List<String> companies = new ArrayList<>();
    Pref pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = new Pref(this);
        companyName = findViewById(R.id.activity_login_company);
        sign_in = findViewById(R.id.activity_login_signin_button);
        forgot_password = findViewById(R.id.activity_login_forgot_password);
        username = findViewById(R.id.activity_login_username);
        ArrayList<String> list = new ArrayList<>();
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        list = gson.fromJson(pref.getCompanyList(), type);

        handleClicks();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        companyName.setAdapter(adapter);
        companyName.setThreshold(0);//start searching from 1 character
        companyName.setAdapter(adapter);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        passwordView = findViewById(R.id.activity_login_password);

        Button mEmailSignInButton = findViewById(R.id.activity_login_signin_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    public boolean onNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    public void handleClicks() {
        forgot_password.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (companyName.getText().toString().equals(null) || companyName.getText().toString().equals("")) {
                    companyName.setError("Company name required");
                }
                if (username.getText().toString().equals(null) || username.getText().toString().equals("")) {
                    username.setError("Username required");
                } else showAlert();
            }
        });

        sign_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (companyName.getText().toString().equals(null) || companyName.getText().toString().equals("")) {
                    companyName.setError("Company name required");
                }
                if (username.getText().toString().equals(null) || username.getText().toString().equals("")) {
                    username.setError("Username required");
                }

                if (passwordView.getText().toString().equals(null) || passwordView.getText().toString().equals("")) {
                    passwordView.setError("Password required");
                } else {
                    companies = new Gson().fromJson(pref.getCompanyList(), new TypeToken<List<String>>() {
                    }.getType());
                    companies.add(companyName.getText().toString());
                    pref.saveString(pref.COMPANIES, new Gson().toJson(companies));
                    pref.saveString(pref.SELECTEDCOMPANY, companyName.getText().toString());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
            }
        });
    }

    public void showAlert() {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Password Recovery")
                .setMessage("Recovery link has been sent to your email.")
                .setPositiveButton("Go to mail", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}

