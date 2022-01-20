package com.dennis.saina.ui.Lessons;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

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
//        lessonsViewModel =
//                new ViewModelProvider(this).get(LessonsViewModel.class);

        binding = FragmentLessonsBinding.inflate(inflater, container, false);

        lessonList = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        myAdapter = new MyAdapter(this.getActivity(), lessonList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getActivity(),
                1, GridLayoutManager.HORIZONTAL, false);

        binding.dataList.setLayoutManager(gridLayoutManager);
        binding.dataList.setHasFixedSize(true);
        binding.dataList.setAdapter(myAdapter);



        binding.dataList.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), binding.dataList, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.i("Hey", "pos: " + position);
                        //dummy comment
                        //Navigate to new view


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


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}