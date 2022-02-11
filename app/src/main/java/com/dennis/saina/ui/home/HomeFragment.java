package com.dennis.saina.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.R;
import com.dennis.saina.databinding.FragmentHomeBinding;
import com.dennis.saina.databinding.FragmentLessonsBinding;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);

        binding.vocabBtn.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(
                    R.id.action_navigation_home_to_navigation_lessons
            );
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }



}