package com.example.dopt_app.shelter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.auth.PreferActivity
import com.example.dopt_app.databinding.ActivityShelterNameBinding

import kotlinx.android.synthetic.main.activity_shelter_name.*

class ShelterNameActivity: AppCompatActivity() {

    lateinit var binding: ActivityShelterNameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shelter_name)

        start_shelter_btn.setOnClickListener{
            val intent= Intent(this, ShelterMainActivity::class.java)
            startActivity(intent)
        }

    }
}