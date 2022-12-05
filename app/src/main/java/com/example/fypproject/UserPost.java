package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.fypproject.databinding.ActivityUserPostBinding;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserPost extends AppCompatActivity {
    ActivityUserPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_post);


        binding.btnChatNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btnMusicNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btnPostNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserPost.this,SettingPage.class);
                startActivity(intent);
            }
        });
    }
}