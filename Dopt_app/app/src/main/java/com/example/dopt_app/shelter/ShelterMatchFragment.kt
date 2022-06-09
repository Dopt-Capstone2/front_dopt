package com.example.dopt_app.shelter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.emailInfo
import com.example.dopt_app.data.*
import com.example.dopt_app.databinding.FragmentShelterMatchBinding
import com.example.dopt_app.home.HomeFragment
import com.example.dopt_app.module.GlideApp
import com.example.dopt_app.share.ShareVPAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShelterMatchFragment : Fragment(){
    private val TAG = "ShelterMatchFragment"

    lateinit var binding: FragmentShelterMatchBinding
    private var gson: Gson = Gson()
    private val infoList = arrayListOf("🐶","➕")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterMatchBinding.inflate(inflater, container, false)

        val matchJason = arguments?.getString("item")
        val animal = gson.fromJson(matchJason, Bookmark::class.java)

        val lockerAdapter = ShelterMatchInfoVPAdapter(this)
        binding.userShareContentVp.adapter = lockerAdapter
        TabLayoutMediator(binding.userShareContentTb, binding.userShareContentVp){
                tab, position ->
            tab.text = infoList[position]
        }.attach()

        binding.shelterBackIg.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root

    }



}