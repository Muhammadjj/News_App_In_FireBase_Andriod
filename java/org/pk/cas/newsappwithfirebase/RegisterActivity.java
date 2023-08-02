package org.pk.cas.newsappwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    ImageView imageView;
    MaterialButton register_btn;
    TextInputLayout registerName, register_Email, register_Password;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Objects.requireNonNull(getSupportActionBar()).hide();
        imageView = findViewById(R.id.register_imageView);
        register_btn = findViewById(R.id.register_btn);
        registerName = findViewById(R.id.register_name);
        register_Password = findViewById(R.id.register_Password);
        register_Email = findViewById(R.id.register_Email);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();


        /*Blink Animation for the News Image */
        Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.blink_animation);
        imageView.startAnimation(animation);

        register_btn.setOnClickListener(v -> {
            String email_for_register = register_Email.getEditText().getText().toString().trim();
            String password_for_register = register_Password.getEditText().getText().toString().trim();
            String name_for_register = registerName.getEditText().getText().toString().trim();

            if (name_for_register.isEmpty()) {
                registerName.setError("Enter your Nick Name");
            } else if (email_for_register.isEmpty()) {
                register_Email.setError("Enter your Correct E_mail");
            }
//            else if (!Patterns.EMAIL_ADDRESS.matcher(email_for_register).matches()){
//                register_Email.setError("Invalid email. Enter valid email address");}

            else if (password_for_register.isEmpty()) {
                register_Password.setError("Enter the Password & Must needed Capital  Word.");
            } else if (password_for_register.length() < 6) {
                register_Password.setError("Maximum Six 6 Character.");
            } else {
                progressDialog.setTitle("Register Page");
                progressDialog.setMessage("Please wait a few mint.....");
                progressDialog.setIcon(R.drawable.wait);
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(email_for_register, password_for_register).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            if (user.isEmailVerified()) {
//                                Toast.makeText(RegisterActivity.this, "User Verified and signIn!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                user.sendEmailVerification();
//                                Toast.makeText(RegisterActivity.this, "Don't account Verified Please Account Verified!", Toast.LENGTH_SHORT).show();
//                            }
                            Toast.makeText(RegisterActivity.this, "Register Successfully!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginPageActivity.class));
                            progressDialog.dismiss();
                        }else{
                            Toast.makeText(RegisterActivity.this, "Don't Register", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar snackbar = Snackbar.make(v, Objects.requireNonNull(e.getMessage()), Snackbar.LENGTH_LONG);
                        snackbar.setDuration(2000);
                        snackbar.show();
                        progressDialog.dismiss();
                    }
                });
            }
        });


    }
}