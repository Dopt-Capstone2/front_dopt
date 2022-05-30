package com.example.dopt_app.shelter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Animal
import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.FragmentShelterHomeBinding
import com.example.dopt_app.home.NewShareFragment
import com.example.dopt_app.home.ShareRVAdapter
import com.google.gson.Gson

class ShelterHomeFragment : Fragment() {

    lateinit var binding: FragmentShelterHomeBinding
    private var matchDatas = ArrayList<Animal>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterHomeBinding.inflate(inflater, container, false)

        matchDatas.apply {
            add(
                Animal("2022(년생)",
                    "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
                    "거제시유기동물보호소",
                    "055-639-6368",
                    "김부근",
                    "연갈색 줄무늬",
                    "448537202200425",
                    R.drawable.dog_num_one,
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
                Animal("2022(년생)",
                    "경상남도 거제시 사등면 두동로1길 109 (사등면, 한국자원재생공사폐비닐적재장) 거제시유기동물보호소",
                    "거제시유기동물보호소",
                    "055-639-6368",
                    "김부근",
                    "연갈색 줄무늬",
                    "448537202200425",
                    R.drawable.dog_num_one,
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
        val shelterMatchRVAdapter= ShelterMatchRVAdapter(matchDatas)

        shelterMatchRVAdapter.setMyItemClickListener(object : ShelterMatchRVAdapter.MyItemClickListener {
            override fun onItemClick(animal: Animal) {
                shelterMatchFragment(animal)
            }
        })

        binding.shelterAnimalsRv.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        return binding.root
    }
    private fun shelterMatchFragment(animal: Animal) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, ShelterMatchFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val animalJson = gson.toJson(animal)
                    putString("animal", animalJson)
                }
            })
            .commitAllowingStateLoss()
    }

}