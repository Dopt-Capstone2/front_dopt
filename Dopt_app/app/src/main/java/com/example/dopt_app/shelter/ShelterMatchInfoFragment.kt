package com.example.dopt_app.shelter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.dopt_app.api.RetrofitClient
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.data.Bookmark_Update
import com.example.dopt_app.data.PostResult
import com.example.dopt_app.databinding.FragmentShelterMatchInfoBinding
import com.example.dopt_app.module.GlideApp
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShelterMatchInfoFragment : Fragment() {

    private val TAG = "ShelterMatchFragment"
    lateinit var binding: FragmentShelterMatchInfoBinding
    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterMatchInfoBinding.inflate(inflater, container, false)

        val matchJason = arguments?.getString("item")
        val animal = gson.fromJson(matchJason, Bookmark::class.java)
        setInit(animal)


        binding.starFinalChoiceYesBtn.setOnClickListener{
            val POST_S_Bookmark_Data = Bookmark_Update("한국동물구조관리협회", animal.desertionNo, 2)
            RetrofitClient.Shelter_instance.POST_Bookmark(POST_S_Bookmark_Data)
                .enqueue(object: Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post S_B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(activity,"입양 허가 완료", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post S_B succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )
        }

        binding.starFinalChoiceNoBtn.setOnClickListener{
            val POST_S_Bookmark_Data = Bookmark_Update("한국동물구조관리협회", animal.desertionNo, 3)
            RetrofitClient.Shelter_instance.POST_Bookmark(POST_S_Bookmark_Data)
                .enqueue(object: Callback<PostResult> {
                    override fun onFailure(call: Call<PostResult>, t: Throwable) {
//                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post S_B failed")
                        Log.d(TAG, t.message.toString())
                    }
                    override fun onResponse(call: Call<PostResult>, response: Response<PostResult>) {
                        Toast.makeText(activity,"입양 반려", Toast.LENGTH_LONG).show()
                        Log.d(TAG, "Post S_B succeeded")
                        Log.d(TAG, response.body().toString())
                    }
                }
                )
        }

        binding.animalProcessState.setOnClickListener {

        }
        return binding.root

    }



    private fun setInit(animal: Bookmark) {
        binding.animalNeuterYnTx.text=animal.neuterYn.toString()
        binding.animalTypeTx.text=animal.kindCd.toString()
        binding.animalCareAddrTx.text=animal.careAddr.toString()
        binding.animalAgeTx.text=animal.age.toString()
        binding.animalCareNmTx.text=animal.careNm.toString()
        binding.animalCareTelTx.text=animal.careTel.toString()
        binding.animalHappenPlaceTx.text=animal.happenPlace.toString()

        Log.d(TAG, animal.toString())
        val imgUrl = animal.filename
        Log.d(TAG, imgUrl)

        when (animal.sexCd) {
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

        GlideApp.with(binding.root.context).load(imgUrl).into(binding.itemAnimalImgIv)
//
//        when(animal.isConsidered){
//            0 ->{
//                binding.animalProcessState.text = "즐겨찾기"
//            }
//            1 ->{
//                binding.animalProcessState.text = "입양 신청 완료"
//            }
//            2 ->{
//                binding.animalProcessState.text = "입양 허가"
//            }
//            3 ->{
//                binding.animalProcessState.text = "입양 거절"
//            }
//        }
    }
}