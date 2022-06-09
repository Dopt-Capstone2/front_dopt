package com.example.dopt_app.star

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
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
        return binding.root
    }



    private fun setInit(item: Bookmark) {
        binding.animalGenderTx.text=item.sexCd
        binding.animalNeuterYnTx.text=item.neuterYn
        binding.animalTypeTx.text=item.kindCd
        binding.animalCareAddrTx.text=item.careAddr
        binding.animalAgeTx.text=item.age
//        binding.animalWeightTx.text=bookmark.weight.toString()
        binding.animalCareNmTx.text=item.careNm
        binding.animalCareTelTx.text=item.careTel
        binding.animalHappenPlaceTx.text=item.happenPlace



        Log.d(TAG, item.toString())
        val imgUrl = item.filename
        Log.d(TAG, imgUrl)

        GlideApp.with(binding.root.context).load(imgUrl).into(binding.bookAnimalImg)
    }
}
