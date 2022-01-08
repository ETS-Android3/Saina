package com.dennis.saina.ui.numbers;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.dennis.saina.databinding.FragmentNumbersBinding;


public class NumbersFragment extends Fragment {

    FragmentNumbersBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        binding = FragmentNumbersBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}