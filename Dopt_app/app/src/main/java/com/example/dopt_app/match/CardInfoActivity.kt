package com.example.dopt_app.match

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dopt_app.R
import com.example.dopt_app.data.Item
import kotlinx.android.synthetic.main.activity_card_info.*
import kotlinx.android.synthetic.main.activity_popup_profile_done.*
import kotlinx.android.synthetic.main.item_card.*

class CardInfoActivity: AppCompatActivity() {
    lateinit var datas : Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_info)

        datas=intent.getSerializableExtra("data") as Item


        go_back_btn.setOnClickListener{
            finish()
        }

    }
}