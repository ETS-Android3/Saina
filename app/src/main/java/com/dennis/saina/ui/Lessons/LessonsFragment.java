package com.dennis.saina.ui.Lessons;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.dennis.saina.R;
import com.dennis.saina.databinding.FragmentLessonsBinding;
import com.dennis.saina.ui.Adapter;
import com.dennis.saina.ui.RecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class LessonsFragment extends Fragment {

//    private LessonsViewModel lessonsViewModel;
    private FragmentLessonsBinding binding;
    private List<String> titles;
    private List<Integer> images;

    Adapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        lessonsViewModel =
//                new ViewModelProvider(this).get(LessonsViewModel.class);

        binding = FragmentLessonsBinding.inflate(inflater, container, false);

        titles=new ArrayList<>();
        images=new ArrayList<>();

        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");
        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");
        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");
        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");


        images.add(R.drawable.ic_baseline_add_alert_24);
        images.add(R.drawable.ic_baseline_add_task_24);
        images.add(R.drawable.ic_baseline_airplanemode_active_24);
        images.add(R.drawable.ic_baseline_airplay_24);
        images.add(R.drawable.ic_baseline_add_alert_24);
        images.add(R.drawable.ic_baseline_add_task_24);
        images.add(R.drawable.ic_baseline_airplanemode_active_24);
        images.add(R.drawable.ic_baseline_airplay_24);
        images.add(R.drawable.ic_baseline_add_alert_24);
        images.add(R.drawable.ic_baseline_add_task_24);
        images.add(R.drawable.ic_baseline_airplanemode_active_24);
        images.add(R.drawable.ic_baseline_airplay_24);
        images.add(R.drawable.ic_baseline_add_alert_24);
        images.add(R.drawable.ic_baseline_add_task_24);
        images.add(R.drawable.ic_baseline_airplanemode_active_24);
        images.add(R.drawable.ic_baseline_airplay_24);


        adapter=new Adapter(this.getActivity(),titles,images);



        GridLayoutManager gridLayoutManager=new GridLayoutManager(this.getActivity(),
                2,GridLayoutManager.VERTICAL,false);

        binding.dataList.setLayoutManager(gridLayoutManager);
        binding.dataList.setAdapter(adapter);

        binding.dataList.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), binding.dataList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.i("Hey","pos: "+position);

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}