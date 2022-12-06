package com.example.fypproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.databinding.DataBindingUtil;
import com.example.fypproject.databinding.ActivitySettingPageBinding;
import com.google.errorprone.annotations.MustBeClosed;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;

public class SettingPage extends AppCompatActivity {
    private FirebaseAuth mAuth;
    ActivitySettingPageBinding binding;
    SwitchCompat switchCompat;
    SharedPreferences sharedPreferences = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);
        mAuth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.setContentView(this,R.layout.activity_setting_page);
        binding.btnChatNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingPage.this, UserChat.class);
                startActivity(intent);
                finish();
            }
        });
        binding.btnMusicNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingPage.this, UserMusic.class);
                startActivity(intent);
                finish();

            }
        });
        binding.btnPostNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingPage.this,UserPost.class);
                startActivity(intent);
                finish();
            }
        });
//        binding.btnSetting.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent (SettingPage.this,SettingPage.class);
//                startActivity(intent);
//                finish();
//            }
//        });
        binding.btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (SettingPage.this,UserProfile.class);
                startActivity(intent);
            }
        });
        switchCompat = findViewById(R.id.switchCompat);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    switchCompat.setChecked(true);
                    binding.btnSetting.setImageResource(R.drawable.white_setting);
                    binding.imageView4.setImageResource(R.drawable.black_logo);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",true);
                    editor.commit();


                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    switchCompat.setChecked(false);
                    binding.btnSetting.setImageResource(R.drawable.black_setting);
                    binding.imageView4.setImageResource(R.drawable.white_logo);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("night_mode",false);
                    editor.commit();


                }

            }

        });
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.getInstance().signOut();
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode",false);
                editor.commit();

                Intent intent = new Intent(SettingPage.this,MainActivity.class);
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
            switchCompat.setChecked(true);
        }

    }
}