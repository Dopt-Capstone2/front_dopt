package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.databinding.ActivityPrefergenderBinding
import kotlinx.android.synthetic.main.activity_prefergender.*

class PrefergenderActivity : AppCompatActivity() {
    lateinit var binding: ActivityPrefergenderBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefergender)

        //이전, 다음으로 넘어가
        gender_previous_btn.setOnClickListener{
            val intent= Intent(this, PreferbreadActivity::class.java)
            startActivity(intent)
        }
        gender_next_btn.setOnClickListener{
            val intent= Intent(this, PrefercolorActivity::class.java)
            startActivity(intent)
        }

    }
}