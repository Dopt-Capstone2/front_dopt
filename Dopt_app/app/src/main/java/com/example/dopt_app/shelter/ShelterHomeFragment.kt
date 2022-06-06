package com.example.dopt_app.shelter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Item
import com.example.dopt_app.databinding.FragmentShelterHomeBinding
import com.google.gson.Gson

class ShelterHomeFragment : Fragment() {

    lateinit var binding: FragmentShelterHomeBinding
    private var matchShel = ArrayList<Item>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterHomeBinding.inflate(inflater, container, false)
//
//        matchShel.apply {
//            add(
//                Item("2015(년생)","경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터","경주동물사랑보호센터","010-3841-6303","경주시","크림",
//                    "447505202200524","http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg",20220530,"양남면 동해안로 603-19 한수원 사택",
//                    "[개] 아메리칸 코카 스파니엘","N",20220609,"경북-경주-2022-00509",20220530,"054-779-6305","경상북도 경주시",
//                    "http://www.animal.go.kr/files/shelter/2022/05/202205301605627_s.jpg","입양승인 ","F","쫄래쫄래 잘 따라오는 이쁜이", "0.2(Kg)"
//                ))
//
//            add(
//                Item("2022(년생)",
//                    "경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터",
//                    "경주동물사랑보호센터",
//                    "010-3841-6303",
//                    "경주시",
//                    "갈색",
//                    "448537202200425" ,
//                    "R.drawable.dog_num_one",
//                    20220523,
//                    "구매3길5-1대호빌",
//                    "[개] 믹스견",
//                    "N",
//                    20220602,
//                    "경남-거제-2022-00348",
//                    20220523,
//                    "055-639-6366",
//                    "경상남도 거제시",
//                    "http://www.animal.go.kr/files/shelter/2022/05/202205271305199_s.jpg",
//                    "입양 승인",
//                    "Q",
//                    "허피스증세",
//                    "0.2(Kg)"
//                )
//            )
//            add(
//                Item("2022(년생)",
//                    "경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터",
//                    "경주동물사랑보호센터",
//                    "010-3841-6303",
//                    "경주시",
//                    "갈색",
//                    "448537202200425",
//                    "R.drawable.dog_num_one",
//                    20220523,
//                    "구매3길5-1대호빌",
//                    "[개] 믹스견",
//                    "N",
//                    20220602,
//                    "경남-거제-2022-00348",
//                    20220523,
//                    "055-639-6366",
//                    "경상남도 거제시",
//                    "http://www.animal.go.kr/files/shelter/2022/04/202205271005489_s.jpg",
//                    "보호중",
//                    "Q",
//                    "허피스증세",
//                    "0.2(Kg)"
//                )
//            )
//            add(
//                Item("2022(년생)",
//                    "경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터",
//                    "경주동물사랑보호센터",
//                    "010-3841-6303",
//                    "경주시",
//                    "갈색",
//                    "448537202200425",
//                    "R.drawable.dog_num_one",
//                    20220523,
//                    "구매3길5-1대호빌",
//                    "[개] 믹스견",
//                    "N",
//                    20220602,
//                    "경남-거제-2022-00348",
//                    20220523,
//                    "055-639-6366",
//                    "경상남도 거제시",
//                    "http://www.animal.go.kr/files/shelter/2022/05/202205271305304.jpg",
//                    "보호중",
//                    "M",
//                    "허피스증세",
//                    "0.2(Kg)"
//                )
//            )
//            add(
//                Item("2022(년생)",
//                    "경상북도 경주시 천북면 천북로 8-4 경주시 동물사랑보호센터",
//                    "경주동물사랑보호센터",
//                    "010-3841-6303",
//                    "경주시",
//                    "갈색",
//                    "448537202200425",
//                    "R.drawable.dog_num_one",
//                    20220523,
//                    "구매3길5-1대호빌",
//                    "[개] 믹스견",
//                    "N",
//                    20220602,
//                    "경남-거제-2022-00348",
//                    20220523,
//                    "055-639-6366",
//                    "경상남도 거제시",
//                    "http://www.animal.go.kr/files/shelter/2022/04/202205271005617.jpg",
//                    "보호중",
//                    "M",
//                    "허피스증세",
//                    "0.2(Kg)"
//                )
//            )
//        }
//        val shelterMatchRVAdapter=  ShelterMatchRVAdapter(matchShel)
//        binding.shelterAnimalsRv.adapter=shelterMatchRVAdapter
//
//        shelterMatchRVAdapter.setMyItemClickListener(object : ShelterMatchRVAdapter.MyItemClickListener {
//            override fun onItemClick(item: Item) {
//                shelterMatchFragment(item)
//            }
//        })

        // binding.shelterAnimalsRv.layoutManager=LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        return binding.root
    }
    private fun shelterMatchFragment(item: Item) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, ShelterMatchFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val animalJson = gson.toJson(item)
                    putString("item", animalJson)
                }
            })
            .commitAllowingStateLoss()
    }

}