package com.example.dopt_app.shelter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.databinding.ActivityShelterMainBinding


class ShelterMainActivity : AppCompatActivity() {

    lateinit var binding: ActivityShelterMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShelterMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initShelterNavigation()
    }

    private fun initShelterNavigation(){

        supportFragmentManager.beginTransaction()
            .replace(R.id.shelter_main_frm, ShelterHomeFragment())
            .commitAllowingStateLoss()

        binding.shelterMainBnv.setOnItemSelectedListener{ item ->
            when (item.itemId) {

                R.id.shelterShareFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.shelter_main_frm, ShelterShareFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.shelterHomeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.shelter_main_frm, ShelterHomeFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.shelterAccountFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.shelter_main_frm, ShelterAccountFragment())
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }
    }
}