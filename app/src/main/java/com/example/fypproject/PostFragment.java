package com.example.fypproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.fypproject.databinding.FragmentPostBinding;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;




public class PostFragment extends Fragment {
    FragmentPostBinding binding;


    public PostFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post,container,false);
        Button btnPost = (Button) view.findViewById(R.id.postButton);


        btnPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UserMainPost.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}