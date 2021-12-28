package com.dennis.saina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.databinding.FragmentExerciseSplashBinding;



public class ExerciseSplashFragment extends Fragment {

    FragmentExerciseSplashBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExerciseSplashBinding.inflate(inflater, container, false);


//        binding.startButton.setOnClickListener(v -> {
//
//            NavController navController = Navigation.findNavController(requireActivity(),
//                    R.id.nav_host_fragment_activity_main);
//
//            navController.navigate(ExerciseSplashFragmentDirections.actionNavigationExerciseToExerciseView());
//
//        });


        return binding.getRoot();
    }
}