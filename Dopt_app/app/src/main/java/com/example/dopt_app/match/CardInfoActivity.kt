package com.example.dopt_app.match

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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

//        if (intent.hasExtra("data")) {
//            datas=intent.getSerializableExtra("data") as Item
//        } else {
//            Toast.makeText(this, "전달된 이름이 없습니다", Toast.LENGTH_SHORT).show()
//        }

        val imgUrl = "http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg"
        // Glide.with(this).load(imgUrl).into(cardImageArea)

//        moreKindCd.text = datas.kindCd
//        more_processState_tx.text = datas.processState
//        more_sexCd_tx.text=datas.sexCd
//        more_age_tx.text=datas.age
//        more_weight_tx.text=datas.weight
//        more_specialMark_tx.text=datas.specialMark
//        more_noticeSdt_tx.text=datas.noticeSdt.toString()
//
//        more_careNm_tx.text=datas.careNm
//        more_chargeNm_tx.text=datas.chargeNm
//        more_careTel_tx.text=datas.careTel

        go_back_btn.setOnClickListener{
            finish()
        }

    }
}