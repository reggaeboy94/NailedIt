package academy.learnprogramming.nailedit;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText tfEmailRegister;
    private EditText tfPasswordRegister;
    private EditText tfPasswordRepeat;
    private Button registerConfirmBtn;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        tfEmailRegister = (EditText) findViewById(R.id.tfEmailRegister);
        tfPasswordRegister = (EditText) findViewById(R.id.tfPasswordRegister);
        tfPasswordRepeat = (EditText) findViewById(R.id.tfPasswordRepeat);
        registerConfirmBtn = (Button) findViewById(R.id.registerConfimBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        configureButtons();
    }


    private void configureButtons() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        registerConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount(tfEmailRegister.getText().toString(), tfPasswordRegister.getText().toString());
            }
        });
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                    }
                });
        // [END create_user_with_email]
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = tfEmailRegister.getText().toString();
        if (TextUtils.isEmpty(email)) {
            tfEmailRegister.setError("Required.");
            valid = false;
        } else {
            tfEmailRegister.setError(null);
        }

        String password = tfPasswordRegister.getText().toString();
        if (TextUtils.isEmpty(password)) {
            tfPasswordRegister.setError("Required.");
            valid = false;
        } else {
            tfPasswordRegister.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {

            startActivity(new Intent(RegisterActivity.this, MainActivity.class));

        } else {
            Toast.makeText(this, "Wystąpił błąd podczas logowania. Niepoprawne dane", Toast.LENGTH_SHORT).show();
        }
    }

}