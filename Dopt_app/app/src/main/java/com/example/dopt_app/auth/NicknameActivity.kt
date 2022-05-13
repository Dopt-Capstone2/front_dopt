package com.example.dopt_app.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import kotlinx.android.synthetic.main.activity_nickname.*

// 프로필 입력 순서
// 닉네임 - 종 - 품종 - 성별 - 색 - 크기
class NicknameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nickname)

        go_profile_btn.setOnClickListener{
            val intent= Intent(this, PreferActivity::class.java)
            startActivity(intent)
        }

    }

}