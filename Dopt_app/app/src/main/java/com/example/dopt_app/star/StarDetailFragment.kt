package com.example.dopt_app.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Animal
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.databinding.FragmentStarDetailBinding
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_star_detail.*

val choose : String = "최종 입양 신청 완료"

class StarDetailFragment: Fragment() {

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
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, StarFragment())
                .commitAllowingStateLoss()
        }
        return binding.root
    }

    private fun setInit(bookmark: Bookmark) {
        binding.animalGenderTx.text=bookmark.sexCd
        binding.animalNeuterYnTx.text=bookmark.neuterYn
        binding.animalTypeTx.text=bookmark.kindCd
        binding.animalCareAddrTx.text=bookmark.careAddr.toString()
        binding.animalAgeTx.text=bookmark.age.toString()
        binding.animalWeightTx.text=bookmark.weight.toString()
        binding.animalCareNmTx.text=bookmark.careNm.toString()
        binding.animalCareTelTx.text=bookmark.careTel.toString()
        binding.animalHappenPlaceTx.text=bookmark.happenPlace.toString()

        val imgUrl = bookmark.filename
        Glide.with(binding.root).load(imgUrl).into(item_animal_img_iv)    }
}
