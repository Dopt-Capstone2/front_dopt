package com.example.dopt_app.shelter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentShelterShareBinding
import com.example.dopt_app.share.ShareVPAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ShelterShareFragment : Fragment() {

    lateinit var binding: FragmentShelterShareBinding
    private val information = arrayListOf("🐱", "➕")


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterShareBinding.inflate(inflater, container, false)

        val lockerAdapter = ShelterShareVPAdapter(this)
        binding.shelterShareContentVp.adapter = lockerAdapter
        TabLayoutMediator(binding.shelterShareContentTb, binding.shelterShareContentVp){
                tab, position ->
            tab.text = information[position]
        }.attach()

        return binding.root
    }

}