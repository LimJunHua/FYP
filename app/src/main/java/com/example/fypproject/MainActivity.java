package com.example.fypproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.example.fypproject.databinding.ActivityMainBinding;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityMainBinding binding;
   // ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        mAuth.getInstance().signOut();
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                mAuth.signInWithEmailAndPassword(email, password)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          if (task.isSuccessful()) {
                              Toast.makeText(getApplicationContext(), "Login successful!!", Toast.LENGTH_LONG).show();
                              // hide the progress bar
                              // if sign-in is successful/
                              // intent to home activity
                              myEdit.putString("loginEmail",email);
                              myEdit.commit();
                            Intent intent = new Intent(MainActivity.this, UserChat.class);
                            startActivity(intent);
                          }
                          else{
                              Toast.makeText(getApplicationContext(), "Login failed!!", Toast.LENGTH_LONG).show();

                          }

                      }
                  });
            }
        });

//




        binding.tvRegister.setOnClickListener((new View.OnClickListener() {
            public final void onClick(View it) {
                Intent myIntent = new Intent((Context)MainActivity.this, SignUp.class);
               MainActivity.this.startActivity(myIntent);
          }
        }));
        binding.tvForgetPassword.setOnClickListener((new View.OnClickListener() {
            public final void onClick(View it) {
                Intent myIntent = new Intent((Context)MainActivity.this, ForgetPassword.class);
                MainActivity.this.startActivity(myIntent);
            }
        }));
//



//        btnLogin.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
//            public final void onClick(View it) {
//                SharedPreferences sharedPref = MainActivity.this.getSharedPreferences("addName", 0);
//                final Ref.ObjectRef edit = new Ref.ObjectRef();
//                edit.element = sharedPref.edit();
//                EditText dataEmail = binding.dataEmail;
//                Intrinsics.checkNotNullExpressionValue(dataEmail, "binding.dataEmail");
//                final String email = dataEmail.getText().toString();
//                EditText dataPassword = binding.dataPassword;
//                Intrinsics.checkNotNullExpressionValue(dataPassword, "binding.dataPassword");
//                String password = dataPassword.getText().toString();
//                CharSequence var6 = (CharSequence)email;
//                if (var6.length() > 0) {
//                    var6 = (CharSequence)password;
//                    if (var6.length() > 0) {
//                        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener((OnCompleteListener)(new OnCompleteListener() {
//                            public final void onComplete(@NotNull Task it) {
//                                Intrinsics.checkNotNullParameter(it, "it");
//                                if (it.isSuccessful()) {
//                                    Intent myIntent = new Intent((Context)MainActivity.this, UserMainPost.class);
//                                    ((SharedPreferences.Editor)edit.element).putString("email", email);
//                                    ((SharedPreferences.Editor)edit.element).commit();
//                                    Toast.makeText(MainActivity.this.getApplicationContext(), (CharSequence)"Welcome to St4yAlive ", Toast.LENGTH_SHORT).show();
//                                    MainActivity.this.startActivity(myIntent);
//                                } else {
//                                    Toast.makeText((Context)MainActivity.this, (CharSequence)String.valueOf(it.getException()), Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        }));
//                    }
//                }
//
//                Toast.makeText((Context)MainActivity.this, (CharSequence)"Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show();
//            }
//        }));

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent myIntent = new Intent((Context)MainActivity.this, UserChat.class);
            MainActivity.this.startActivity(myIntent);
        }
    }


//
}