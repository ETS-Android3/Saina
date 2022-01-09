package com.dennis.saina.ui.numbers;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.DetectSignsActivity;
import com.dennis.saina.ExerciseActivity;
import com.dennis.saina.MainActivity;


import com.dennis.saina.databinding.FragmentNumbersBinding;


public class NumbersFragment extends Fragment {

    FragmentNumbersBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentNumbersBinding.inflate(inflater, container, false);

        binding.tryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DetectSignsActivity.class);
                //intent.putExtra("lessonsList",lessonArrayList);
                startActivity(intent);
            }
        });

        //Log.i("hey "," "+hey);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            String value = getArguments().getString("key");
        }


    }
}