package com.example.dopt_app

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.databinding.ActivityPreferBinding
import kotlinx.android.synthetic.main.activity_nickname.*

class PreferActivity : AppCompatActivity() {
    lateinit var binding: ActivityPreferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefer)


    }
}