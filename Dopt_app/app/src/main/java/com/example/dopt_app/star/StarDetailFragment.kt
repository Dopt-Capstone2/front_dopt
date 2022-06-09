package com.example.dopt_app.star

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Animal
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.databinding.FragmentStarDetailBinding
import com.example.dopt_app.module.GlideApp
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_star_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.auth.emailInfo
import com.example.dopt_app.data.Bookmark_Update
import com.example.dopt_app.data.PostResult


val choose : String = "최종 입양 신청 완료"

class StarDetailFragment: Fragment() {
    private val TAG = "StarDetailFragment"

    lateinit var binding: FragmentStarDetailBinding
    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarDetailBinding.inflate(inflater, container, false)

        // Star 에서 넘어온 데이터 받아오기
        val animalJson = arguments?.getString("bookmark")
        val bookmark = gson.fromJson(animalJson, Bookmark::class.java)
        // Star 에서 넘어온 데이터를 반영
        setInit(bookmark)

        binding.backStarBt.setOnClickListener {
//            (context as MainActivity).supportFragmentManager.beginTransaction()
//                .replace(R.id.main_frm, StarFragment())
//                .commitAllowingStateLoss()
            activity?.onBackPressed()
        }

        //입양신청
        binding.starFinalChoiceBtn.setOnClickListener{

            val POST_S_Bookmark_Data = Bookmark_Update(emailInfo, bookmark.desertionNo, 1)
            RetrofitClient.Shelter_instance.POST_Bookmark(POST_S_Bookmark_Data)
                .enqueue(object: Callback <PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post S_B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(activity,"입양 신청 완료", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post S_B succeeded")
                        Log.d(TAG, response.body().toString())
                        activity?.onBackPressed()
                    }
                }
                )
        }


        return binding.root
    }



    private fun setInit(item: Bookmark) {
        binding.animalNeuterYnTx.text=item.neuterYn
        binding.animalTypeTx.text=item.kindCd
        binding.animalCareAddrTx.text=item.careAddr
        binding.animalAgeTx.text=item.age
        binding.animalCareNmTx.text=item.careNm
        binding.animalCareTelTx.text=item.careTel
        binding.animalHappenPlaceTx.text=item.happenPlace

        when (item.sexCd) {
            "M" -> {
                binding.animalGenderTx.text = "수컷"
            }
            "F" -> {
                binding.animalGenderTx.text = "암컷"
            }
            else -> {
                binding.animalGenderTx.text = "성별미상"
            }
        }

        when(item.isConsidered){
            0 ->{
                binding.animalProcessState.text = "즐겨찾기"
            }
            1 ->{
                binding.animalProcessState.text = "입양 신청 완료"
            }
            2 ->{
                binding.animalProcessState.text = "입양 허가"
            }
            3 ->{
                binding.animalProcessState.text = "입양 거절"
            }
        }

        Log.d(TAG, item.toString())
        val imgUrl = item.filename
        Log.d(TAG, imgUrl)

        GlideApp.with(binding.root.context).load(imgUrl).into(binding.bookAnimalImg)
    }
}
