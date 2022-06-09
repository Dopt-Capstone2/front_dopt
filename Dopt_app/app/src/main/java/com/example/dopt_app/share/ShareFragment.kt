package com.example.dopt_app.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.account.AccountFragment
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.FragmentShareBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson

class ShareFragment : Fragment() {

    lateinit var binding: FragmentShareBinding
    lateinit var shelNm : String
    private val information = arrayListOf("입양 완료", "+")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShareBinding.inflate(inflater, container, false)

        val lockerAdapter = ShareVPAdapter(this)
        binding.shareContentVp.adapter = lockerAdapter
        TabLayoutMediator(binding.shareContentTb, binding.shareContentVp){
                tab, position ->
            tab.text = information[position]
        }.attach()


        return binding.root
    }



}