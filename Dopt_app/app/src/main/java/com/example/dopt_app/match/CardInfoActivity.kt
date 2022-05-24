package com.example.dopt_app.match

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.R
import com.example.dopt_app.auth.PreferActivity
import com.example.dopt_app.data.Item
import kotlinx.android.synthetic.main.activity_card_info.*

class CardInfoActivity: AppCompatActivity() {
    lateinit var datas : Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)

        go_back_btn.setOnClickListener{
            finish()
        }

    }
}