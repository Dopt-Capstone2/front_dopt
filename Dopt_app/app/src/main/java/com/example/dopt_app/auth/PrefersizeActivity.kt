package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.databinding.ActivityPrefersizeBinding
import com.example.dopt_app.popup.PopupProfileDoneActivity
import kotlinx.android.synthetic.main.activity_preferbread.*


// 프로필 입력 순서
// 닉네임 - 종 - 품종 - 성별 - 색 - 크기
class PrefersizeActivity : AppCompatActivity() {

    lateinit var binding: ActivityPrefersizeBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prefersize)

        //이전, 다음으로 넘어가
        breed_previous_btn.setOnClickListener{
            val intent= Intent(this, PrefercolorActivity::class.java)
            startActivity(intent)
        }
        breed_next_btn.setOnClickListener{
            val intent= Intent(this, PopupProfileDoneActivity::class.java)
            startActivity(intent)
        }
    }
}