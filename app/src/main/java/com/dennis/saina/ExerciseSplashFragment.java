package com.dennis.saina;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.databinding.FragmentExerciseSplashBinding;
import com.dennis.saina.databinding.FragmentLessonsBinding;
import com.dennis.saina.ui.Lesson;
import com.dennis.saina.ui.LessonData;
import com.dennis.saina.ui.adapters.ExercisesAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Objects;


public class ExerciseSplashFragment extends Fragment {

    FragmentExerciseSplashBinding binding;
    ExercisesAdapter exercisesAdapter;
    ArrayList<Lesson> lessonArrayList;
    FirebaseFirestore db;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExerciseSplashBinding.inflate(inflater, container, false);
        exercisesAdapter = new ExercisesAdapter(getContext(), lessonArrayList);
        lessonArrayList = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        binding.startButton.setOnClickListener(v -> {
            NavController navController = Navigation.findNavController(requireActivity(),
                    R.id.nav_host_fragment_activity_main);

            navController.navigate(ExerciseSplashFragmentDirections.actionNavigationExerciseToExerciseView());

        });


        return binding.getRoot();
    }
}