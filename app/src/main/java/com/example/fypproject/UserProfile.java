package com.example.fypproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.fypproject.databinding.ActivityUserProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

public class UserProfile extends AppCompatActivity {
    ActivityUserProfileBinding binding;
    DatabaseReference databases;


//    @SuppressLint("ResourceAsColor")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_profile);
        SharedPreferences sh = getSharedPreferences("login", Context.MODE_PRIVATE);
//change Background color
//        @SuppressLint("ResourceAsColor")
//        LinearLayout icLayout = binding.ICLayout;
//        icLayout.setBackgroundResource(R.color.red);


//        LinearLayout icLayout = binding.ICLayout;
//        String color = "R.color.red";
//        icLayout.setBackgroundColor(-1);
        String email = sh.getString("loginEmail","");
        String SeacrhEmail = email.replace(".",",");
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("users");
        db.child(SeacrhEmail).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult().exists()){
                        DataSnapshot dataSnapshot = task.getResult();
                        String username = String.valueOf(dataSnapshot.child("name").getValue());
                        String ICNumber = String.valueOf(dataSnapshot.child("icNumber").getValue());
                        String PhoneNumber = String.valueOf(dataSnapshot.child("phoneNumber").getValue());
                        binding.tvDataEmail.setText(email);
                        binding.tvDataUsername.setText(username);
                        binding.tvDataIC.setText(ICNumber);
                        binding.tvDataPhone.setText(PhoneNumber);

                    }
                }
            }
        });



           // databases = FirebaseDatabase.getInstance().getReference();
    }
}