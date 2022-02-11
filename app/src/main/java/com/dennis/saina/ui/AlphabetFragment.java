package com.dennis.saina.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dennis.saina.R;
import com.dennis.saina.databinding.FragmentAlphabetBinding;
import com.dennis.saina.databinding.FragmentNumbersBinding;


public class AlphabetFragment extends Fragment {

    FragmentAlphabetBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAlphabetBinding.inflate(inflater, container, false);

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(getParentFragment()).navigate(
                        R.id.action_navigation_alphabet_to_navigation_numbers
                );
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(binding.btn.getText()=="See Number Alphabet"){
                  binding.alphabetImg.setImageResource(R.drawable.numbers);
                  binding.btn.setText("See Letter Alphabet");
              }else if(binding.btn.getText()=="See Letter Alphabet"){
                  binding.alphabetImg.setImageResource(R.drawable.alphabet);
                  binding.btn.setText("See Number Alphabet");
              }else{
                  binding.alphabetImg.setImageResource(R.drawable.numbers);
                  binding.btn.setText("See Letter Alphabet");
              }

            }
        });

        return binding.getRoot();
    }
}