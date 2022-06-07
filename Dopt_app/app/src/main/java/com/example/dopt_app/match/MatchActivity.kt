package com.example.dopt_app.match

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.*
import com.example.dopt_app.api.AnimalOpenAPI
import com.example.dopt_app.api.BookmarkAPI
import com.example.dopt_app.databinding.ActivityMatchBinding
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.activity_match.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.*
import com.example.dopt_app.data.*
import kotlinx.android.synthetic.main.item_card.*
import java.time.LocalDate

//var Match_Response = MutableLiveData<Match>()


class MatchActivity : AppCompatActivity() {
    private val TAG = "MatchActivity"
    //전역 변수
    lateinit var binding: ActivityMatchBinding

    lateinit var cardStackAdapter: CardStackAdapter

    // 카드스택뷰의 레이아웃 매니져
    lateinit var manager : CardStackLayoutManager

    // 카드 아이템 안의 정보 recycler view
//    private var cardItems = mutableListOf<DataX>()
    private var cardCount = 0
//    private var cardItems = mutableListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_match)
        initClickListener()
        getUserMatch()

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)

        shelter_logo_img.setOnClickListener{
            val intent = Intent(this, CardInfoActivity::class.java)
            startActivity(intent)
        }
//        for (i: Int in 0..Match_Raw.size) {
//            cardItems[i] = Match_Raw[i].data[i]
//        }

        manager= CardStackLayoutManager(baseContext, object: CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if(direction==Direction.Right){
                    itemLike(cardCount)

                }else if (direction==Direction.Left){
                    Toast.makeText(this@MatchActivity, "NOPE", Toast.LENGTH_SHORT).show()
                }
//                else if (direction==Direction.Top){
//                }
                cardCount += 1
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

//        cardItems.apply {
//            add( Item("2015(년생)",
//                "경상북도 포항시 북구 흥해읍 덕장길 224 (흥해읍)",
//            "영일동물플러스","054-262-8295","이형석",
//            "흑","447502202200877",
//                "http://www.animal.go.kr/files/shelter/2022/05/202206021706466_s.jpg",20220602,
//            "경상북도 포항시 남구 동해면 연오로 91 포항코아루블루인시티 앞","[개] 푸들","Y",20220613,"경북-포항-2022-00498",
//                20220602,"054-270-3368","경상북도포항시",
//                "http://www.animal.go.kr/files/shelter/2022/05/202206021706466_s.jpg",
//            "보호중","M","내장칩 있음,전문샵에서 미용하고 몇개월 지난듯함,초록색 목테,활발하고 조금 마른편,온순함","0.2(Kg)"
//              )
//            )
//            add(Item("2015(년생)","경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터","경주동물사랑보호센터","010-3841-6303","경주시","크림",
//                "447505202200524","http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg",20220530,"양남면 동해안로 603-19 한수원 사택",
//                "[개] 아메리칸 코카 스파니엘","N",20220609,"경북-경주-2022-00509",20220530,"054-779-6305","경상북도 경주시",
//                "http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg","보호중","F","쫄래쫄래 잘 따라오는 이쁜이", "0.2(Kg)"
//            ))
//            add( Item("2015(년생)","경상북도 문경시 중앙로 170 (흥덕동) 종합가축병원","종합가축병원","054-552-2233","문경시 유통축산과",
//                "흑갈색","447512202200366","http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg",20220602,
//                "시외터미널인근","[개] 스탠다드 푸들","U",20220609,"경북-문경-2022-00181",20220602,"054-550-6282","경상북도 문경시",
//                "http://www.animal.go.kr/files/shelter/2022/05/202206021806956[1]_s.jpg","보호중","M","목줄과 칩이 업없고 치석이 많이 있음","0.2(Kg)"
//            ))
//            add( Item("2017(년생)","경상북도 영주시 문수면 반구로324번길 135 (문수면) 장수면 반구리 407-8","영주시 동물보호센터","010-6682-1185","영주시","황색흰색 점박이",
//                "447509202200149","http://www.animal.go.kr/files/shelter/2022/04/202205301705954_s.jpg",20220530,"휴천동 석미APT부근","[개] 믹스견","N",20220609,
//            "경북-영주-2022-00149",20220530,"054-639-7592","경상북도영주시","http://www.animal.go.kr/files/shelter/2022/04/202205301705954.jpeg","종료(반환)","M","건강상태양호", "3"))
//        }

//        Log.d(TAG+"널이니?", cardItems[2].toString())
        cardStackAdapter= CardStackAdapter(baseContext, cardItems)
        cardStackView.layoutManager=manager
        cardStackView.adapter = cardStackAdapter


//        cardStackAdapter.setMyItemClickListener(object :CardStackAdapter.MoreClickListener{
//            override fun onItemClick(v: View, items: Item) {
//                Intent(this@MatchActivity, CardInfoActivity::class.java).run { startActivity(this) }
//            }
//        })

//        Log.d(TAG+"널이니?", cardItems[2].toString())

    }

    private fun getUserMatch(){

    }

    private fun itemLike(count: Int){
        var POST_Bookmark_Data = Bookmark(emailInfo, cardItems[count].age, cardItems[count].careAddr, cardItems[count].careNm,
            cardItems[count].careTel, cardItems[count].chargeNm, cardItems[count].colorCd, cardItems[count].desertionNo,
            cardItems[count].filename, cardItems[count].happenDt, cardItems[count].happenPlace, cardItems[count].kindCd,
            cardItems[count].neuterYn, cardItems[count].noticeEdt, cardItems[count].noticeNo, cardItems[count].noticeSdt,
            cardItems[count].officetel, cardItems[count].orgNm, cardItems[count].popfile, cardItems[count].processState,
            cardItems[count].sexCd, cardItems[count].specialMark, cardItems[count].weight, 1)

        Log.d(TAG+"아래", POST_Bookmark_Data.toString())

        RetrofitClient.Bookmark_instance.POST_Bookmark(POST_Bookmark_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d(TAG, "Post B succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                })
    }

    private fun initClickListener() {
        //메인 액티비티로 돌아가는 화살표
        bot_arrow_img.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}

//    private fun getItems() {
//            var age: String = token[0]
//            var careAddr: String = token[1]
//            var careNm: String = token[2]
//            var careTel: String = token[3]
//            var chargeNm: String = token[4]
//            var colorCd: String = token[5]
//            var desertionNo: String = token[6]
//            var filename: String = token[7]
//            var happenDt: Int = token[8].toInt()
//            var happenPlace: String = token[9]
//            var kindCd: String = token[10]
//            var neuterYn: String = token[11]
//            var noticeEdt: Int = token[12].toInt()
//            var noticeNo: String = token[13]
//            var noticeSdt: Int = token[14].toInt()
//            var officetel: String = token[15]
//            var orgNm: String = token[16]
//            var popfile: String = token[17]
//            var processState: String = token[18]
//            var sexCd: String = token[19]
//            var specialMark: String = token[20]
//            var weight: String = token[21]
//
//            val cardItem = Bookmark(
//                emailInfo,age,careAddr,careNm,careTel,chargeNm,colorCd,desertionNo,
//                filename,happenDt,happenPlace,kindCd,neuterYn,noticeEdt,noticeNo,noticeSdt,officetel,orgNm,
//                popfile,processState,sexCd,specialMark,weight,isConsidered)
//                }