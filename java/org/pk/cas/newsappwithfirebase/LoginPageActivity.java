package org.pk.cas.newsappwithfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.pk.cas.fragements.PakNews;

import java.util.Objects;

public class LoginPageActivity extends AppCompatActivity {
    ImageView imageView;
    TextView signup_register;
    TextInputLayout email, password;
    MaterialButton btn_login;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        Objects.requireNonNull(getSupportActionBar()).hide();
        imageView = findViewById(R.id.imageView);
        email = findViewById(R.id.login_Email);
        password = findViewById(R.id.login_Password);
        btn_login = findViewById(R.id.guest_btn);
        signup_register = findViewById(R.id.login_signup_Register_Activity);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        /*Blink Animation for the News Image */
        Animation animation = AnimationUtils.loadAnimation(LoginPageActivity.this, R.anim.blink_animation);
        imageView.startAnimation(animation);


        /*SharedPreferences ka mqsd ya ha ky ya minimum data apny ps store kr skta ha SharedPreferences
         * asy hm Auto login page ka laya use krty ha i mean ka agr hmre koi bhi email firebase ya kase
         * aur plateForm pr mojod ha to login wale activity pr sa open nhi ho ge direct hi hmy
         * SharedPreferences apne main activity pr la jaye ga js pr hm na intent sa btya ho ga ky hmy kn
         * si activity ma jana ha */
//        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
//        final String type = sharedPreferences.getString("email", "");
//        if (type.isEmpty()) {
//            Toast.makeText(this, "please login ", Toast.LENGTH_SHORT).show();
//        } else {
//            startActivity(new Intent(LoginPageActivity.this, PakNews.class));
//            Toast.makeText(this, "Account login", Toast.LENGTH_SHORT).show();
//            finish();
//        }


        btn_login.setOnClickListener(v -> {
            String email_for_login = email.getEditText().getText().toString().trim();
            String password_for_login = password.getEditText().getText().toString().trim();

            if (email_for_login.isEmpty()) {
                email.setError("Enter your Correct E_mail");
            } else if (password_for_login.isEmpty()) {
                password.setError("Enter the Password & Must needed Capital  Word.");
            } else if (password_for_login.length() < 6) {
                password.setError("Maximum Six 6 Character.");
            } else {
                progressDialog.setTitle("Login Page");
                progressDialog.setMessage("Please wait a few mint.....");
                progressDialog.setIcon(R.drawable.wait);
                progressDialog.setCanceledOnTouchOutside(true);
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email_for_login, password_for_login).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                            if (user.isEmailVerified()) {
//                                Toast.makeText(LoginPageActivity.this, "User Verified and signIn!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                user.sendEmailVerification();
//                                Toast.makeText(LoginPageActivity.this, "Don't account Verified Please Account Verified!", Toast.LENGTH_SHORT).show();
//                            }
                            Toast.makeText(LoginPageActivity.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            startActivity(new Intent(LoginPageActivity.this, MainActivity.class));
//                            share();// Method checkOut the SharedPreferences
                            finish();
                        } else {
                            Toast.makeText(LoginPageActivity.this, "Don't Successful Login Page!", Toast.LENGTH_SHORT).show();
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

        signup_register.setOnClickListener(v -> {
            startActivity(new Intent(LoginPageActivity.this, RegisterActivity.class));
        });
    }

    /* Share() method as method ka andr hm na asy btya ha ky jo hm na login page ma email aur password
     * dyna ha asy SharedPreferences.Editor as ma store kr la {SharedPreferences bhi database ki trha
     * ha but ya minimum data ko store krta ha } aur store krna ka bd ya hmy ays activity ma la
     * jaye js ka hm na intent ki help sa btya howa ha. */

    private void share() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", Objects.requireNonNull(email.getEditText()).getText().toString());
        editor.putString("password", Objects.requireNonNull(password.getEditText()).getText().toString());
        editor.apply();
        Toast.makeText(LoginPageActivity.this, "Account login successfully", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginPageActivity.this, PakNews.class));
    }
}