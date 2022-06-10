package com.example.dopt_app.shelter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


class ShelterMatchInfoVPAdapter(fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int  = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ShelterMatchInfoFragment()
            else -> ShelterUserCheckFragment()
        }
    }
}