package com.example.dopt_app.shelter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentShelterAccountBinding

class ShelterAccountFragment : Fragment() {

    lateinit var binding: FragmentShelterAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterAccountBinding.inflate(inflater, container, false)

        return binding.root
    }

}