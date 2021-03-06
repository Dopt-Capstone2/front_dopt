package com.example.dopt_app.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.dopt_app.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.dopt_app.*
import com.example.dopt_app.data.Monthly_Statistics
import com.example.dopt_app.data.Share
import com.example.dopt_app.share.WriteShareFragment
import com.google.gson.Gson
import java.util.ArrayList
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.match.getAnimalData_10Days
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"


    lateinit var binding: FragmentHomeBinding
    private var shareDatas = ArrayList<Share>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

//        getMonthly()

        shareDatas.apply {
            add(Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                    " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                    " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현"))
            add(Share("냥냥이 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요ㅜㅠ \n 다음 주차에도 후기 올리러 오겠습니다 ;) \n 만관부~~", R.drawable.cat_num_one, "test22"))
            add(Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요 왕왕\n" +
                    " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                    " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현"))
            add(Share("냥냥이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                    " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                    " 만관부~~", R.drawable.cat_num_one, "2022-05-02", "test11", "채현채현"))
            add(Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                    " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                    " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현"))
        }

        val shareRVAdapter = ShareRVAdapter(shareDatas)

        binding.homeRecentShareRv.adapter=shareRVAdapter

        shareRVAdapter.setMyItemClickListener(object : ShareRVAdapter.MyItemClickListener {
            override fun onItemClick(share: Share) {
                changeNewShareFragment(share)
            }
        })

        binding.homeRecentShareRv.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_001))
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_002))
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_003))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

//        binding.shareMoreBtn.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, HomeMoreFragment())
//                .commitAllowingStateLoss()
//        }

        return binding.root
    }

    private fun changeNewShareFragment(share: Share) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, NewShareFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val shareJson = gson.toJson(share)
                    putString("share", shareJson)
                }
            })
            .commitAllowingStateLoss()
    }

//    private fun getMonthly(){
//        //Monthly Statistics API
//            var Monthly_Statistics_Response = MutableLiveData<Monthly_Statistics>()
//            RetrofitClient.Monthly_Statistics__instance.GET_Monthly_Statistics()
//                .enqueue(object: Callback<Monthly_Statistics> {
//                    override fun onFailure(call: Call<Monthly_Statistics>, t: Throwable) {
//                        // Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "failed")
//                        Log.d(TAG, t.message.toString())
//                    }
//                    override fun onResponse(call: Call<Monthly_Statistics>, response: Response<Monthly_Statistics>) {
//                        // Toast.makeText(applicationContext, response.body().toString(), Toast.LENGTH_LONG).show()
//                        Log.d(TAG, "succeeded")
//                        Log.d(TAG, response.body().toString())
//                        Monthly_Statistics_Response.value = response.body() as Monthly_Statistics
//                        // 값을 복사
//                        val Monthly_Statistics_Raw = Monthly_Statistics_Response.value?.copy()
//                        // 데이터 클래스들의 배열 출력
//                        Log.d("Monthly_Statistics_Raw", Monthly_Statistics_Raw.toString())
//                        //요소별 접근
//                        //response의 각 데이터 클래스 접근
//                        if (Monthly_Statistics_Raw != null) {
//                            Log.d("Monthly_Statistics_", Monthly_Statistics_Raw.data[0].toString())
//                        }
//                    }
//                }
//                )
//    }
}