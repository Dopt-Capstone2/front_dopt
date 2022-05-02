package com.example.dopt_app.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.databinding.FragmentAccountBinding

class AccountFragment : Fragment() {

    lateinit var binding: FragmentAccountBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater, container, false)

        binding.profileDetailBtn.setOnClickListener{
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ProfileFragment())
                .commitAllowingStateLoss()
        }

        return binding.root
    }
}