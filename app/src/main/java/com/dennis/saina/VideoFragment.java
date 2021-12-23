package com.dennis.saina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.databinding.FragmentLessonsBinding;
import com.dennis.saina.databinding.FragmentVideoBinding;


public class VideoFragment extends Fragment {

    FragmentVideoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentVideoBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }
}