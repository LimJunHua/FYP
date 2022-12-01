package com.example.fypproject;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.example.fypproject.databinding.ActivityForgetPasswordBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgetPassword extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityForgetPasswordBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_forget_password);
        binding.btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = binding.resetPassword.getText().toString().trim();
                if(email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Please Insert Your Email!!", Toast.LENGTH_LONG).show();
                    binding.resetPassword.setError("Email is Required");
                    binding.resetPassword.requestFocus();
                }
                else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), "Please Insert a valid Email!!", Toast.LENGTH_LONG).show();
                    binding.resetPassword.setError("Email format Error");
                    binding.resetPassword.requestFocus();

                }else{
                    mAuth.getInstance().sendPasswordResetEmail(email)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "sent successful!!", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(), "sent failed!!", Toast.LENGTH_LONG).show();
                                    }
                                }
                });

            }

        };
        });
        binding.ForgotPasswordBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
