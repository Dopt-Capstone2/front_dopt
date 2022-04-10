package com.example.dopt_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentProfiledetailBinding

class ProfiledetailFragment : Fragment() {

    lateinit var binding: FragmentProfiledetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfiledetailBinding.inflate(inflater, container, false)


        binding.backProfileBt.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ProfileFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }


}