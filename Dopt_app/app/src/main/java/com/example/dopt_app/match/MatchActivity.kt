package com.example.dopt_app.match

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.auth.PreferActivity
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

        //임시로 넣을 문자열 데이터
        val testList = mutableListOf<String>()
        testList.add("a")
        testList.add("b")
        testList.add("c")

        cardStackAdapter= CardStackAdapter(baseContext, testList)
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