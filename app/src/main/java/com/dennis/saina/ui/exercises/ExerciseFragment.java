package com.dennis.saina.ui.exercises;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.bumptech.glide.Glide;
import com.dennis.saina.ExerciseActivity;
import com.dennis.saina.Question;
import com.dennis.saina.R;


import com.dennis.saina.databinding.FragmentExerciseBinding;
import com.dennis.saina.ui.Lesson;
import com.dennis.saina.LessonData;
import com.dennis.saina.ui.adapters.ExercisesAdapter;
import com.dennis.saina.ui.adapters.MyAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

import java.util.Random;

public class ExerciseFragment extends Fragment {

    private ExerciseViewModel mViewModel;
    FirebaseFirestore db;
    ArrayList<Lesson> lessonArrayList;
    ExercisesAdapter exercisesAdapter;
    MyAdapter myAdapter;
    FragmentExerciseBinding binding;

    ArrayList<String> names;
    ArrayList<String> images;

    ArrayList<Integer> answeredNumbers;
    ArrayList<Question> questionsList;
    Random random;

    Question question;

    int[] positions;
    RadioGroup questionGroup;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentExerciseBinding.inflate(inflater, container, false);
        db = FirebaseFirestore.getInstance();
        lessonArrayList = new ArrayList<>();
        myAdapter = new MyAdapter(getContext(), lessonArrayList);
        exercisesAdapter = new ExercisesAdapter(getContext(), lessonArrayList);



        names = new ArrayList<>();
        images = new ArrayList<>();
        answeredNumbers = new ArrayList<>();
        questionsList = new ArrayList<>();


        //exit splash when clicked
        binding.startButton.setOnClickListener(v -> {
//            binding.splash.setVisibility(View.GONE);
//            binding.main.setVisibility(View.VISIBLE);

            Intent intent = new Intent(getContext().getApplicationContext(), ExerciseActivity.class);
            startActivity(intent);
            prepareQuestion();
        });




        binding.nextBtn.setOnClickListener(v -> {
            Log.i("msgRes", "" + prepareQuestion());

            //store the currectQuestion

            //check if selected item ==correct answer


            //Log.i("selected Answer: "," "+ selectedButton[0]);


        });
        binding.prevBtn.setOnClickListener(v -> {

        });

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data ...");
        progressDialog.show();

        Thread thread = new Thread() {
            public void run() {

                LessonData.EventChangeListener(getContext(), myAdapter, lessonArrayList, progressDialog);
            }
        };
        thread.start();


        return binding.getRoot();


    }


    private int prepareQuestion() {

        if (binding.readyBtn.getVisibility() != View.GONE) {
            binding.readyBtn.setVisibility(View.GONE);
        }

        binding.Name.setText(R.string.quiz_question);
        binding.nextBtn.setText(R.string.Done);

        //For each lesson separate images and names
        for (Lesson lesson : lessonArrayList) {
            names.add(lesson.getName());
            images.add(lesson.getImage());
        }

        positions = generate();
        //generate random numbers
        int[] radioButtonAnswers = generateQuestions();
        //here the first one is correct answer

        answeredNumbers.add(radioButtonAnswers[0]);
        question = new Question(
                images.get(radioButtonAnswers[0]),
                names.get(radioButtonAnswers[0]),
                names.get(radioButtonAnswers[1]),
                names.get(radioButtonAnswers[2]));


        questionsList.add(question);


        Glide.with(requireContext())
                .load(question.getmQuestionImage())
                .into(binding.Image);

        for (int i = 0; i < 3; i++) {
            if (positions[0] == i) {
                binding.firstAnswer.setText("" + names.get(radioButtonAnswers[i]));
            } else if (positions[1] == i) {
                binding.secondAnswer.setText("" + names.get(radioButtonAnswers[i]));
            } else if (positions[2] == i) {
                binding.thirdAnswer.setText("" + names.get(radioButtonAnswers[i]));
            }
        }


        Log.i("MSGt", "" + question.getmQuestionAnswer());


        //to avoid repetition remove correct answers used

        names.remove(names.get(radioButtonAnswers[0]));
        images.remove(images.get(radioButtonAnswers[0]));

        //this returns position of correct answer
        return findIndex(positions, 0);
    }

    // Linear-search function to find the index of an element
    public static int findIndex(int[] arr, int t) {

        // if array is Null
        if (arr == null) {
            return -1;
        }

        // find length of array
        int len = arr.length;
        int i = 0;

        // traverse in the array
        while (i < len) {

            // if the i-th element is t
            // then return the index
            if (arr[i] == t) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    private int[] generate() {
        //randomize answer
        random = new Random();
        int[] nums = new int[3];
        nums[0] = random.nextInt(3);
        nums[1] = random.nextInt(3);
        nums[2] = random.nextInt(3);

        while (nums[0] == nums[1])
            nums[0] = random.nextInt(3);
        while (nums[0] == nums[2])
            nums[2] = random.nextInt(3);
        while (nums[1] == nums[2])
            nums[2] = random.nextInt(3);

        if (nums[0] == nums[1] || nums[0] == nums[2] || nums[1] == nums[2]) {
            nums = generate();
        }
        Log.i("Nums", "num0: " + nums[0] + " num1: " + nums[1] + " num2: " + nums[2]);
        return nums;

    }

    private int[] generateQuestions() {
        int[] questions = new int[3];
        Random rand = new Random();
        questions[0] = rand.nextInt(lessonArrayList.size());//correct answer
        questions[1] = rand.nextInt(lessonArrayList.size());
        questions[2] = rand.nextInt(lessonArrayList.size());

        while (questions[1] == questions[0]) {
            questions[1] = rand.nextInt(lessonArrayList.size());
        }
        while (questions[2] == questions[0]) {
            questions[2] = rand.nextInt(lessonArrayList.size());
        }
        while (questions[2] == questions[1]) {
            questions[2] = rand.nextInt(lessonArrayList.size());
        }


        return questions;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}