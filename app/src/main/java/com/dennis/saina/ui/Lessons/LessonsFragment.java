package com.dennis.saina.ui.Lessons;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dennis.saina.CheckNetwork;
import com.dennis.saina.R;
import com.dennis.saina.databinding.FragmentLessonsBinding;
import com.dennis.saina.ui.Lesson;
import com.dennis.saina.LessonData;
import com.dennis.saina.ui.adapters.ExercisesAdapter;
import com.dennis.saina.ui.adapters.MyAdapter;
import com.dennis.saina.ui.RecyclerItemClickListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class LessonsFragment extends Fragment {

    //    private LessonsViewModel lessonsViewModel;
    private FragmentLessonsBinding binding;


    private ArrayList<Lesson> lessonList;

    MyAdapter myAdapter;
    ExercisesAdapter exercisesAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;
    LessonData lessonData;

    Button exerciseBtn;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLessonsBinding.inflate(inflater, container, false);

        lessonList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter(this.getActivity(), lessonList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                1, GridLayoutManager.HORIZONTAL, false);

        binding.dataList.setLayoutManager(gridLayoutManager);
        binding.dataList.setHasFixedSize(true);
        binding.dataList.setAdapter(myAdapter);




        binding.goToExerciseBtn.setOnClickListener(view -> {
            NavHostFragment.findNavController(this).navigate(
                    R.id.action_navigation_lessons_to_navigation_exercise
            );
        });



//        int position=0;
//        binding.dataList.setOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                int offset=recyclerView.computeHorizontalScrollOffset();
//
//                if (offset % recyclerView.getWidth() == 0) {
//                    int position = offset / recyclerView.getWidth();
//                    System.out.println("Current position is"+position);
//                }
//                Log.i("scroll","Yep"+position);
//
//            }
//        });
        binding.dataList.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), binding.dataList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.i("Hey", "pos: " + position);
                        //dummy comment



                        //binding.lessonText.setText("Lesson: "+position+"/"+lessonList.size());
                        // Navigate to new view
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Fetching Data ...");
        progressDialog.show();

        Thread thread = new Thread() {
            public void run() {
                LessonData.EventChangeListener(getContext(), myAdapter, lessonList, progressDialog);
            }
        };
        thread.start();


        return binding.getRoot();
    }

    private void CheckNetworkConnection() {
        if(CheckNetwork.isInternetAvailable(getActivity())) //returns true if internet available
        {

            //do something. loadwebview.
        }
        else
        {
            Toast.makeText(getActivity(),"No Internet Connection",Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}