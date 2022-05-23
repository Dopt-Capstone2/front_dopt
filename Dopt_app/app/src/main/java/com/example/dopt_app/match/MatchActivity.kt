package com.example.dopt_app.match

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.auth.PreferActivity
import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.ActivityMatchBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.activity_nickname.*

class MatchActivity : AppCompatActivity() {

    //전역 변수
    lateinit var binding: ActivityMatchBinding

    lateinit var cardStackAdapter: CardStackAdapter

    // 카드스택뷰의 레이아웃 매니져
    lateinit var manager : CardStackLayoutManager

    // 카드 아이템 안의 정보 recycler view

    private var cardItems = ArrayList<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_match)

        initClickListener()

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)

        manager= CardStackLayoutManager(baseContext, object: CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {

            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {

            }

            override fun onCardDisappeared(view: View?, position: Int) {

            }
        })

        cardItems.apply {
            add(
                Item("2022(년생)",
                "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
            "거제시유기동물보호소",
            "055-639-6368",
            "김부근",
            "연갈색 줄무늬",
            448537202200425,
            "http://www.animal.go.kr/files/shelter/2022/04/202205231405234_s.jpg",
            20220523,
            "일운면 스타힐스",
            "[고양이] 한국 고양이",
            "N",
            20220602,
            "경남-거제-2022-00348",
            20220523,
            "055-639-6366",
            "경상남도 거제시",
            "http://www.animal.go.kr/files/shelter/2022/04/202205231405234.jpg",
            "보호중",
            "Q",
            "허피스증세",
            "0.2(Kg)"
                )
            )
            add(
                Item("2022(년생)",
                    "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
                    "거제시유기동물보호소",
                    "055-639-6368",
                    "김부근",
                    "연갈색 줄무늬",
                    448537202200425,
                    "http://www.animal.go.kr/files/shelter/2022/04/202205231405234_s.jpg",
                    20220523,
                    "일운면 스타힐스",
                    "[고양이] 한국 고양이",
                    "N",
                    20220602,
                    "경남-거제-2022-00348",
                    20220523,
                    "055-639-6366",
                    "경상남도 거제시",
                    "http://www.animal.go.kr/files/shelter/2022/04/202205231405234.jpg",
                    "보호중",
                    "Q",
                    "허피스증세",
                    "0.2(Kg)"
                )
            )
        }


        cardStackAdapter= CardStackAdapter(baseContext, cardItems)
        cardStackView.layoutManager=manager
        cardStackView.adapter = cardStackAdapter
    }

    private fun initClickListener() {
        bot_arrow_img.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}