package com.example.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.fypproject.databinding.ActivityVerificationRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class verificationRegister extends AppCompatActivity {
    private ActivityVerificationRegisterBinding binding;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_register);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_verification_register);
        SharedPreferences sh = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE);
        TextView msg= binding.tvMSGcode;
        int passcode = sh.getInt("code", 0);
        String code = "" + passcode;
        String email = sh.getString("email","");

        if (email == null){
            email = "not email import";
        }

        binding.tvMSGcode.setText("The code has been send to your email \n ("+email+") \n Please insert the code at the below:");
        binding.tvNumber1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.tvNumber1.getText().toString().length()==1)
                    binding.tvNumber2.requestFocus();
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.tvNumber2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.tvNumber2.getText().toString().length()==1)
                    binding.tvNumber3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.tvNumber3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.tvNumber3.getText().toString().length()==1)
                    binding.tvNumber4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.tvNumber4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.tvNumber4.getText().toString().length()==1)
                    binding.tvNumber5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.tvNumber5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.tvNumber5.getText().toString().length()==1)
                    binding.tvNumber6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.tvNumber6.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(binding.tvNumber6.getText().toString().length()==1)
                   binding.btnVerify.performClick();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.btnVerify.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int code1 = Integer.parseInt(binding.tvNumber1.getText().toString());
                int code2 = Integer.parseInt(binding.tvNumber2.getText().toString());
                int code3 = Integer.parseInt(binding.tvNumber3.getText().toString());
                int code4 = Integer.parseInt(binding.tvNumber4.getText().toString());
                int code5 = Integer.parseInt(binding.tvNumber5.getText().toString());
                int code6 = Integer.parseInt(binding.tvNumber6.getText().toString());
                String verifyCode = String.format("%d%d%d%d%d%d",code1,code2,code3,code4,code5,code6);
                if(verifyCode.equals(code)) {
                    mDatabase = FirebaseDatabase.getInstance().getReference();
                    String email = sh.getString("email", "");
                    String name = sh.getString("username", "");
                    String phoneNumber = sh.getString("phoneNumber", "");
                    String icNumber = sh.getString("icnumber", "");
                    String password = sh.getString("password", "");
                    String RawEmail = email.replace(".",",");
                    user user = new user(name, icNumber, phoneNumber, RawEmail, password);
                    mDatabase.child("users").child(RawEmail).setValue(user);
                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getBaseContext(), "Registration  successful!!", Toast.LENGTH_LONG).show();
                                // hide the progress bar
                                // if sign-in is successful
                                // intent to home activity
                                sh.edit().clear().commit();
                                mAuth.getInstance().signOut();
                                Intent intent = new Intent(verificationRegister.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                // sign-in failed
                                Toast.makeText(getBaseContext(), "Reason can not be blank", Toast.LENGTH_SHORT).show();// hide the progress bar
                            }
                        }
                    });


                }
                else{
                    Toast.makeText(verificationRegister.this, "Wrong Passcode", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}