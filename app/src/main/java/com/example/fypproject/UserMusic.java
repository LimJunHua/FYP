package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import com.example.fypproject.databinding.ActivityUserMusicBinding;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class UserMusic extends AppCompatActivity {
    ActivityUserMusicBinding binding;
    SharedPreferences sharedPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_music);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_music);


        binding.btnChatNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserMusic.this,UserChat.class);
                startActivity(intent);
                finish();
            }
        });
//        binding.btnMusicNav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent (UserMusic.this,UserMusic.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        binding.btnPostNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserMusic.this,UserPost.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserMusic.this,SettingPage.class);
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