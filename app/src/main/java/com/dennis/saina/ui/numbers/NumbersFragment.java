package com.dennis.saina.ui.numbers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.MainActivity;


import com.dennis.saina.databinding.FragmentNumbersBinding;


public class NumbersFragment extends Fragment {

    FragmentNumbersBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentNumbersBinding.inflate(inflater, container, false);

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