package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.databinding.ActivityPreferBinding
import kotlinx.android.synthetic.main.activity_prefer.*

class PreferActivity : AppCompatActivity() {
    lateinit var binding: ActivityPreferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefer)

        //이전, 다음으로 넘어가
        dogbreed_previous_btn.setOnClickListener{
            val intent= Intent(this, NicknameActivity::class.java)
            startActivity(intent)
        }
        dogbreed_next_btn.setOnClickListener{
            val intent= Intent(this, PreferbreadActivity::class.java)
            startActivity(intent)
        }

    }
}