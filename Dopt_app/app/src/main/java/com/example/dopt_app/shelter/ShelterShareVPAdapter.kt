package com.example.dopt_app.shelter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.dopt_app.share.AdoptNewFragment
import com.example.dopt_app.share.AdoptShareFragment

class ShelterShareVPAdapter (fragment : Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int  = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AdoptShareFragment()
            else -> AdoptNewFragment()
        }
    }
}