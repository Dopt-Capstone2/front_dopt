package com.example.dopt_app

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.databinding.ActivityPreferBinding
import com.example.dopt_app.databinding.ActivityPrefercolorBinding
import kotlinx.android.synthetic.main.activity_nickname.*
import kotlinx.android.synthetic.main.activity_prefer.*
import kotlinx.android.synthetic.main.activity_preferbread.*
import kotlinx.android.synthetic.main.activity_prefercolor.*

class PrefercolorActivity : AppCompatActivity() {

    lateinit var binding: ActivityPrefercolorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferbread)

        //이전, 다음으로 넘어가
        color_previous_btn.setOnClickListener{
            val intent= Intent(this, PrefergenderActivity::class.java)
            startActivity(intent)
        }
        color_next_btn.setOnClickListener{
            val intent= Intent(this, PrefersizeActivity::class.java)
            startActivity(intent)
        }

    }
}