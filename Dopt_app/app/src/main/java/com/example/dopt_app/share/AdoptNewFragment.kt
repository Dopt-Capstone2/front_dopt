package com.example.dopt_app.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentAdoptNewBinding

class AdoptNewFragment: Fragment() {
    lateinit var binding:FragmentAdoptNewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdoptNewBinding.inflate(inflater, container, false)

        return binding.root
    }
}