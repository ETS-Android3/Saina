package com.dennis.saina.ui

import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.dennis.saina.DetectActivity
import com.dennis.saina.databinding.RealTimeFragmentBinding

class RealTimeFragment : Fragment() {


    lateinit var binding: RealTimeFragmentBinding

    lateinit var bitmap: Bitmap


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RealTimeFragmentBinding.inflate(inflater, container, false)

        binding.detectBtn.setOnClickListener {
            val intent = Intent(getActivity(), DetectActivity::class.java)
            getActivity()?.startActivity(intent)
        }


        return binding.root
    }


}