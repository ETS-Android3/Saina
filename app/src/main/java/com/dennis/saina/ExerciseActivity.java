package com.dennis.saina;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dennis.saina.ui.Lesson;
import com.dennis.saina.ui.adapters.MyAdapter;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;


public class ExerciseActivity extends AppCompatActivity {

    ArrayList<Lesson> lessonArrayList;
    ArrayList<String> names;
    ArrayList<String> images;
    ArrayList<Integer> answeredNumbers;
    ArrayList<Question> questionsList;
    Question question;

    Random random;
    MyAdapter myAdapter;
    RadioGroup questionGroup;
    TextView Name;
    ImageView Image;
    Button nextBtn, prevBtn;
    int[] positions;
    RadioButton firstAnswer, secondAnswer, thirdAnswer;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        //get data from firebase
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data ...");
        progressDialog.show();

        Thread thread = new Thread() {
            public void run() {
                LessonData.EventChangeListener(ExerciseActivity.this, myAdapter, lessonArrayList, progressDialog);
            }
        };
        thread.start();


        lessonArrayList = new ArrayList<>();
        names = new ArrayList<>();
        images = new ArrayList<>();
        answeredNumbers = new ArrayList<>();
        questionsList = new ArrayList<>();
        myAdapter = new MyAdapter(this, lessonArrayList);


        questionGroup = findViewById(R.id.questionGroup);
        firstAnswer = findViewById(R.id.firstAnswer);
        secondAnswer = findViewById(R.id.secondAnswer);
        thirdAnswer = findViewById(R.id.thirdAnswer);

        Name = findViewById(R.id.Name);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        Image = findViewById(R.id.Image);


//
        prepareQuestion();
//
//
//


    }


    private int prepareQuestion() {


        Name.setText(R.string.quiz_question);
        nextBtn.setText(R.string.Done);

        //For each lesson separate images and names
        for (Lesson lesson : lessonArrayList) {
            names.add(lesson.getName());
            images.add(lesson.getImage());
        }

        positions = generate();
        //generate random numbers
        int[] radioButtonAnswers = generateQuestions();
        //here the first one is correct answer

//        answeredNumbers.add(radioButtonAnswers[0]);
//        question = new Question(
//                images.get(radioButtonAnswers[0]),
//                names.get(radioButtonAnswers[0]),
//                names.get(radioButtonAnswers[1]),
//                names.get(radioButtonAnswers[2]));
//
//
//
//        questionsList.add(question);


//        Glide.with(this)
//                .load(question.getmQuestionImage())
//                .into(Image);

//        for (int i = 0; i < 3; i++) {
//            if (positions[0] == i) {
//                firstAnswer.setText("" + names.get(radioButtonAnswers[i]));
//            } else if (positions[1] == i) {
//                secondAnswer.setText("" + names.get(radioButtonAnswers[i]));
//            } else if (positions[2] == i) {
//                thirdAnswer.setText("" + names.get(radioButtonAnswers[i]));
//            }
//        }


//        Log.i("MSGt", "" + question.getmQuestionAnswer());


        //to avoid repetition remove correct answers used
//
//        names.remove(names.get(radioButtonAnswers[0]));
//        images.remove(images.get(radioButtonAnswers[0]));

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

        if (lessonArrayList.size() > 0) {
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
        }


        return questions;
    }
}