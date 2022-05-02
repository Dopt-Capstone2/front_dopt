package com.example.dopt_app.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentStarBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Animal
import com.google.gson.Gson
import java.util.ArrayList

class StarFragment : Fragment() {

    lateinit var binding: FragmentStarBinding
    private var animalDatas = ArrayList<Animal>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStarBinding.inflate(inflater, container, false)


        animalDatas.apply {
            add(Animal("2002(년생)", "00 00시 00군 00도 00읍 00", "채현 보호소", "00-00-00", "채현","연한 갈색", null,
                R.drawable.dog_num_one,"", "", "[개]포메라니안","중성화X","", "", "", "", "", "", "보호중", "암컷", "추위를 잘 탐", "2(kg)" ))
            add(Animal("2002(년생)", "00 00시 00군 00도 00읍", "채현이 보호소", "00-00-000", "채현이","갈색", null,
                R.drawable.cat_num_one,"", "", "[고양이]두두두","중성화O","", "", "", "", "", "", "보호중", "암컷", "추위를 잘 탐", "3(kg)" ))
            add(Animal("2002(년생)", "00 00시 00군 00도 00", "이채현 보호소", "00-000-00", "이채현","진한 갈색", null,
                R.drawable.dog_num_one,"", "", "[개]포메라니안","중성화X","", "", "", "", "", "", "보호중", "암컷", "추위를 잘 탐", "2(kg)" ))
        }

        // 더미데이터랑 Adapter 연결
        val starRVAdapter = StarRVAdapter(animalDatas)
        // 리사이클러뷰에 어댑터를 연결
        binding.starAnimalsRv.adapter = starRVAdapter

        starRVAdapter.setMyItemClickListener(object : StarRVAdapter.MyItemClickListener {
            override fun onItemClick(animal: Animal) {
                changeAnimalFragment(animal)
            }
            override fun onRemoveAlbum(position: Int) {
                starRVAdapter.removeItem(position)
            }
        })
        // 레이아웃 매니저 설정
        binding.starAnimalsRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        return binding.root
    }

    private fun changeAnimalFragment(animal: Animal) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, StarDetailFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val animalJson = gson.toJson(animal)
                    putString("animal", animalJson)
                }
            })
            .commitAllowingStateLoss()
    }
}