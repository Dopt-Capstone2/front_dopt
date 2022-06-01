package com.example.dopt_app.match

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
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
import com.example.dopt_app.data.*
import kotlinx.android.synthetic.main.item_card.*


class MatchActivity : AppCompatActivity() {
    private val TAG = "MatchActivity"
    //전역 변수
    lateinit var binding: ActivityMatchBinding

    lateinit var cardStackAdapter: CardStackAdapter

    // 카드스택뷰의 레이아웃 매니져
    lateinit var manager : CardStackLayoutManager

    private var isConsidered : Int = 3
    private var userEmail : String ="123@123"

    // 카드 아이템 안의 정보 recycler view
    private var cardItems = mutableListOf<Bookmark>()

    private lateinit var itemString : String
    private var cardCount = 0

    var animalResponse_10Days = MutableLiveData<OpenAnimal>()
    var endde_10Days = get_endde()
    var bgnde_10Days = get_bgnde(10)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_match)

        getItems()
        initClickListener()

        val cardStackView = findViewById<CardStackView>(R.id.cardStackView)


        manager= CardStackLayoutManager(baseContext, object: CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if(direction==Direction.Right){
                    itemLike(cardCount)

                }else if (direction==Direction.Left){

                }else if (direction==Direction.Top){

                }
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
//            getAnimalData_10Days()
//            add(
//                Item("2022(년생)",
//                "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
//            "거제시유기동물보호소",
//            "055-639-6368",
//            "김부근",
//            "연갈색 줄무늬",
//            "448537202200425",
//            "http://www.animal.go.kr/files/shelter/2022/04/202205231405234_s.jpg",
//            20220523,
//            "일운면 스타힐스",
//            "[고양이] 한국 고양이",
//            "N",
//            20220602,
//            "경남-거제-2022-00348",
//            20220523,
//            "055-639-6366",
//            "경상남도 거제시",
//            "http://www.animal.go.kr/files/shelter/2022/04/202205231405234.jpg",
//            "보호중",
//            "Q",
//            "허피스증세",
//            "0.2(Kg)"
//                )
//            )
//            add(
//                Item("2022(년생)",
//                    "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
//                    "거제시유기동물보호소",
//                    "055-639-6368",
//                    "김부근",
//                    "연갈색 줄무늬",
//                    "448537202200425",
//                    "http://www.animal.go.kr/files/shelter/2022/04/202205231405234_s.jpg",
//                    20220523,
//                    "일운면 스타힐스",
//                    "[고양이] 한국 고양이",
//                    "N",
//                    20220602,
//                    "경남-거제-2022-00348",
//                    20220523,
//                    "055-639-6366",
//                    "경상남도 거제시",
//                    "http://www.animal.go.kr/files/shelter/2022/04/202205231405234.jpg",
//                    "보호중",
//                    "Q",
//                    "허피스증세",
//                    "0.2(Kg)"
//                )
//            )
//        }

        cardStackAdapter= CardStackAdapter(baseContext, cardItems)
        cardStackView.layoutManager=manager
        cardStackView.adapter = cardStackAdapter

//        cardStackAdapter.setMyItemClickListener(object :CardStackAdapter.MoreClickListener{
//            override fun onItemClick(v: View, items: Item) {
//                Intent(this@MatchActivity, CardInfoActivity::class.java).run { startActivity(this) }
//            }
//        })


    }

    private fun getItems() {
        getAnimalData_10Days()

        val animalRaw10Days = animalResponse_10Days.value?.copy()
        val animalItems10Days = animalRaw10Days?.response?.body?.items

        //println(animalItems_10Days.item.size)
        //println(animalItems_10Days.item[0])
        val size : Int = animalItems10Days!!.item.size

        for(i : Int in 0..size){
            val token = animalItems10Days!!.item[i].toString().split(",")
            var age: String = token[0]
            var careAddr: String = token[1]
            var careNm: String = token[2]
            var careTel: String = token[3]
            var chargeNm: String = token[4]
            var colorCd: String = token[5]
            var desertionNo: String = token[6]
            var filename: String = token[7]
            var happenDt: Int = token[8].toInt()
            var happenPlace: String = token[9]
            var kindCd: String = token[10]
            var neuterYn: String = token[11]
            var noticeEdt: Int = token[12].toInt()
            var noticeNo: String = token[13]
            var noticeSdt: Int = token[14].toInt()
            var officetel: String = token[15]
            var orgNm: String = token[16]
            var popfile: String = token[17]
            var processState: String = token[18]
            var sexCd: String = token[19]
            var specialMark: String = token[20]
            var weight: String = token[21]

            val cardItem = Bookmark(userEmail,age,careAddr,careNm,careTel,chargeNm,colorCd,desertionNo,
                filename,happenDt,happenPlace,kindCd,neuterYn,noticeEdt,noticeNo,noticeSdt,officetel,orgNm,
                popfile,processState,sexCd,specialMark,weight,isConsidered)

            cardItems.add(i,cardItem)
            }
//            else{
//                println("animalRaw is null")
//            }
            //Log.d("animalList_val", getAnimalData().value.toString())
    }

    private fun itemLike(count: Int){
//        val POST_Bookmark_Data = Bookmark(userEmail, "2021년생", "careAddr2", "careNm2", "02-222-2222", "chargeNm2", "하양색", "desertionNo22", "file.jpg", 20220527, "서울특별시 동작구", "[개] 골드 리트리버", "Y", 20220527, "22", 20220610, "02-222-2212", "orgNm2", "pofile2", "보호중", "M", "목에 흉터가 있음", "3kg", 0)
        cardItems[count].isConsidered to 0
        val POST_Bookmark_Data = cardItems[count]
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
                }
                )

    }

    private fun initClickListener() {
        //메인 액티비티로 돌아가는 화살표
        bot_arrow_img.setOnClickListener{
            val intent= Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}