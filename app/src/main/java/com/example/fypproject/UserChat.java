package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import  com.example.fypproject.databinding.ActivityUserChatBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserChat extends AppCompatActivity {
    ActivityUserChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_chat);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_user_chat);


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
                Intent intent = new Intent (UserChat.this,SettingPage.class);
                startActivity(intent);
            }
        });
    }
}