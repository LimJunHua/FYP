package com.example.fypproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class UserMainFragment extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super .onCreate(savedInstanceState);
            setContentView(R.layout.activity_user_main_fragment);
            FragmentManager fragmentManager = getSupportFragmentManager();


            //transact to Chat
            Button btnChat = findViewById(R.id.btnChatNav);
            btnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    fragmentManager.beginTransaction()
//                            .replace(R.id.fragment_container, ExampleFragment.class, null)
//                            .setReorderingAllowed(true)
//                            .addToBackStack("name") // name can be null
//                            .commit();

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, ChatFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack("name")
                            .commit();

                }
            });

            //transact to Post
            Button btnPost = findViewById(R.id.btnPostNav);
            btnPost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, PostFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack("name")
                            .commit();

                }
            });

            //transact to Music
            Button btnMusic = findViewById(R.id.btnMusicNav);
            btnMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, MusicFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack("name")
                            .commit();

                }
            });

            //transact to Setting
            Button btnSetting = findViewById(R.id.btnSettingNav);
            btnSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainerView, SettingFragment.class, null)
                            .setReorderingAllowed(true).addToBackStack("name")
                            .commit();

                }
            });

        }
}
