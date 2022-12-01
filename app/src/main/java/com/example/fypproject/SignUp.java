package com.example.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fypproject.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

import org.jetbrains.annotations.NotNull;

import java.util.Properties;

import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.random.Random;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    private ActivitySignUpBinding binding;
    private DatabaseReference databases;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        //mAuth = FirebaseAuth.getInstance();
        binding= DataBindingUtil.setContentView(this,R.layout.activity_sign_up);


        binding.btnRegister.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Boolean Error = false;
                String AccUsername, icnumber,phoneNumber, email, AccPassword,comfirmPassword;
                AccUsername = binding.signupName.getText().toString().trim();
                icnumber = binding.signupICNumber.getText().toString().trim();
                phoneNumber = binding.signupPhoneNumber.getText().toString().trim();
                email = binding.signupEmail.getText().toString();
                AccPassword = binding.signupPassword.getText().toString().trim();
                comfirmPassword = binding.signupComfirmPassword.getText().toString().trim();
                if(AccUsername.isEmpty()){
                    binding.signupName.setError("Username is Required");
                    Error = true;
                }
                if(icnumber.isEmpty()){
                    binding.signupICNumber.setError("IC Number is Required");
                    Error = true;
                }
                if(!Patterns.PHONE.matcher(phoneNumber).matches()) {
                    binding.signupPhoneNumber.setError("Phone format Error");
                    Error = true;
                }
//                if(phoneNumber.length() == 11 ||phoneNumber.length() ==10){
//                    binding.signupPhoneNumber.setError("Phone number must be 10 or 11");
//                    Error = true;
//                }
                if(phoneNumber.isEmpty()){
                    binding.signupPhoneNumber.setError("Phone Number is Required");
                    Error = true;
                }

                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.signupEmail.setError("Email format Error");
                    Error = true;
                }
                if(email.isEmpty()){
                    binding.signupEmail.setError("Email is Required");
                    Error = true;
                }
                if(AccPassword.isEmpty()){
                    binding.signupPassword.setError("Password is Required");
                    Error = true;
                }
                if(comfirmPassword.isEmpty()){
                    binding.signupComfirmPassword.setError("Comfirm Password is Required");
                }
                if(!AccPassword.equals(comfirmPassword)){
                    binding.signupComfirmPassword.setError("Password does not match");
                    Error = true;
                }
                if(Error == false){
                    myEdit.putString("username", AccUsername);
                    myEdit.putString("icnumber", icnumber);
                    myEdit.putString("phoneNumber",phoneNumber);
                    myEdit.putString("email", email);
                    myEdit.putString("password", AccPassword);
                    myEdit.commit();
                    sendEmail();
                    Intent intent = new Intent(SignUp.this, verificationRegister.class);
                    startActivity(intent);
                }






//                binding.tvEmail.setText(binding.signupEmail.getText().toString());
//                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                     @Override
//                      public void onComplete(@NonNull Task<AuthResult> task) {
//                          if (task.isSuccessful()) {
//                              Toast.makeText(getApplicationContext(), "Registration  successful!!", Toast.LENGTH_LONG).show();
//                              // hide the progress bar
//                              // if sign-in is successful
//                              // intent to home activity
//                              Intent intent = new Intent(SignUp.this, verificationRegister.class);
//                              startActivity(intent);
//                          }
//                          else {
//                              // sign-in failed
//                              Toast.makeText(getApplicationContext(), "Registration  failed!!", Toast.LENGTH_LONG).show();// hide the progress bar
//                          }
//                      }
//                  });
            }


            private void registerUser() {
//                if (TextUtils.isEmpty(email)) {
//                    Toast.makeText(getApplicationContext(),
//                                    "Please enter email!!",
//                                    Toast.LENGTH_LONG)
//                            .show();
//                    return;
//                }
//                if (TextUtils.isEmpty(password)) {
//                    Toast.makeText(getApplicationContext(),
//                                    "Please enter password!!",
//                                    Toast.LENGTH_LONG)
//                            .show();
//                    return;
//                }
//                if (TextUtils.isEmpty(username)) {
//                    Toast.makeText(getApplicationContext(),
//                                    "Please enter username!!",
//                                    Toast.LENGTH_LONG)
//                            .show();
//                    return;
//                }
//                if (TextUtils.isEmpty(icnumber)) {
//                    Toast.makeText(getApplicationContext(),
//                                    "Please enter IC Number!!",
//                                    Toast.LENGTH_LONG)
//                            .show();
//                    return;
//                }
//                if (TextUtils.isEmpty(phoneNumber)) {
//                    Toast.makeText(getApplicationContext(),
//                                    "Please enter Phone Number!!",
//                                    Toast.LENGTH_LONG)
//                            .show();
//                    return;
//                }
//                if (TextUtils.isEmpty(comfirmPassword)) {
//                    Toast.makeText(getApplicationContext(),
//                                    "Please enter Comfirm Password!!",
//                                    Toast.LENGTH_LONG)
//                            .show();
//                    return;
//                }


            }
        });


    }
    private void sendEmail() {
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        //Getting content for email
        int max = 999999;
        int min = 100000;
        int range = max - min + 1;
        int code = (int)Math.floor(Math.random()*(max-min+1)+min);
        String email = binding.signupEmail.getText().toString().trim();
        String subject = String.format("Account Verification Code");
        String message = String.format("Here is Attach ur account verification Code \n Verification Code :"+code+" \n enjoy our app~");
        myEdit.putInt("code",code);
        myEdit.commit();
        //Creating SendMail object
        SendMail sm = new SendMail( this,email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
    @Override
    public void onClick(View view) {
        sendEmail();
    }
}