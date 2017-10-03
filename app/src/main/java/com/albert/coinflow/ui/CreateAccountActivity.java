package com.albert.coinflow.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.albert.coinflow.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.userNameEditText) EditText mUserNameEditTex;
    @Bind(R.id.passwordEditText) EditText mPasswordEditText;
    @Bind(R.id.confirmPasswordText) EditText mConfirmPasswordText;
    @Bind(R.id.emailEditText) EditText mEmailEditText;
    @Bind(R.id.createAccountButton) Button mCreateAccountButton;
    @Bind(R.id.loginTextView) TextView mLoginTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String mName;
    private ProgressDialog mAuthProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        ButterKnife.bind(this);

        mLoginTextView.setOnClickListener(this);
        mCreateAccountButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
        createAuthListener();
        createAuthProgressDialog();
    }

    /** Method to add users to the database
     * Checks the input the user inserts */
    private void createNewUser() {
        mName = mUserNameEditTex.getText().toString().trim();
        final String email = mEmailEditText.getText().toString().trim();
        String password = mPasswordEditText.getText().toString().trim();
        String confirmPassword = mConfirmPasswordText.getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(mName);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;
        mAuthProgressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mAuthProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            createFirebaseUserProfile(task.getResult().getUser());
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    /** To create Firebase users */
    private void createFirebaseUserProfile(final FirebaseUser user) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();
        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d("New User", user.getDisplayName());
                        }
                    }
                });
    }

    /* Method for the auth listener */
    private void createAuthListener() {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(CreateAccountActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View v) {
        if (v == mLoginTextView) {
//            Intent intent = new Intent(CreateAccountActivity.this, LoginActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
            finish();
        }

        if (v == mCreateAccountButton) {
            createNewUser();
        }
    }

    /* Boolean methods to help in verifying users */
    /* Checks the editText values */
    private boolean isValidEmail(String email) {
        boolean goodEmail = (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!goodEmail) {
            mEmailEditText.setError("Sorry somethings not right.");
            return false;
        }
        return goodEmail;
    }

    private boolean isValidName(String name) {
        if (name.equals("")) {
            mUserNameEditTex.setError("I don't think your name should be blank");
            return false;
        }
        return true;
    }

    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPasswordEditText.setError("Sorry password too short");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPasswordEditText.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    /* Progress Dialog to keep the screen loading */
    /* This is important to keep the user occupied */
    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
    }

    /** Override methods to help with the Firebase auth
     * The listener needs an onStart and onStop */
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
