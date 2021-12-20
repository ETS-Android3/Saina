package com.dennis.saina;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.databinding.FragmentCustomVideogridLayoutBinding;
import com.dennis.saina.databinding.FragmentPlayLessonViewBinding;


public class fragment_play_lesson_view extends Fragment {

    FragmentPlayLessonViewBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding =   FragmentPlayLessonViewBinding.inflate(inflater, container, false);


        String videopath="src/main/res/assets/videos/abandon.gif";
        Uri uri=Uri.parse(videopath);
        binding.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.videoView.setVideoURI(uri);
                binding.videoView.start();
            }
        });
        View root = binding.getRoot();

        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}