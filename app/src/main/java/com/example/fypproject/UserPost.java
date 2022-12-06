package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import com.example.fypproject.databinding.ActivityUserPostBinding;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class UserPost extends AppCompatActivity {
    ActivityUserPostBinding binding;
    SharedPreferences sharedPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_post);


        binding.btnChatNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserPost.this,UserChat.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnMusicNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserPost.this,UserMusic.class);
                startActivity(intent);
                finish();
            }
        });
//        binding.btnPostNav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent (UserPost.this,UserPost.class);
//                startActivity(intent);
//                finish();
//            }
//        });


        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserPost.this,SettingPage.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        sharedPreferences = getSharedPreferences("night",0);
        Boolean booleanValue = sharedPreferences.getBoolean("night_mode",true);
        if (booleanValue){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            binding.btnSetting.setImageResource(R.drawable.white_setting);
            binding.imageView4.setImageResource(R.drawable.black_logo);
        }

    }
}