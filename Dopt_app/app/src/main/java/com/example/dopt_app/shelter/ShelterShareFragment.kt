package com.example.dopt_app.shelter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentShelterShareBinding

class ShelterShareFragment : Fragment() {

    lateinit var binding: FragmentShelterShareBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterShareBinding.inflate(inflater, container, false)

        return binding.root
    }

}