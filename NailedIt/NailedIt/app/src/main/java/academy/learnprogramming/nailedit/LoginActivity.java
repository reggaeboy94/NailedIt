package academy.learnprogramming.nailedit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText TFLoginEmail;
    private EditText TFPasswordLogin;
    private Button loginConfirmBtn;
    private Button registerBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        TFLoginEmail = (EditText) findViewById(R.id.tfLoginEmail);
        TFPasswordLogin = (EditText) findViewById(R.id.tfPasswordLogin);
        loginConfirmBtn = (Button) findViewById(R.id.loginConfirmBtn);
        registerBtn = (Button) findViewById(R.id.registerBtn);

        configureButtons();
    }
    public void onStart() {
        super.onStart();
         //Check if user is signed in (non-null) and update UI accordingly.
       FirebaseUser currentUser = mAuth.getCurrentUser();
       updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    private void configureButtons() {
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick: logowanie powiodło się");
                signIn(TFLoginEmail.getText().toString(), TFPasswordLogin.getText().toString());
            }
        });
    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }


        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }
    private boolean validateForm() {
        boolean valid = true;

        String email = TFLoginEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            TFLoginEmail.setError("Required.");
            valid = false;
        } else {
            TFLoginEmail.setError(null);
        }

        String password = TFPasswordLogin.getText().toString();
        if (TextUtils.isEmpty(password)) {
            TFPasswordLogin.setError("Required.");
            valid = false;
        } else {
            TFPasswordLogin.setError(null);
        }

        return valid;
    }

}
