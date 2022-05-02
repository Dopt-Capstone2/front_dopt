package com.example.dopt_app.star

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Animal
import com.example.dopt_app.databinding.FragmentStarDetailBinding
import com.google.gson.Gson

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
        val animalJson = arguments?.getString("animal")
        val animal = gson.fromJson(animalJson, Animal::class.java)
        // Star 에서 넘어온 데이터를 반영
        setInit(animal)

        binding.backStarBt.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, StarFragment())
                .commitAllowingStateLoss()
        }
        return binding.root
    }

    private fun setInit(animal: Animal) {
        binding.animalGenderTx.text=animal.sexCd.toString()
        binding.animalNeuterYnTx.text=animal.neuterYn.toString()
        binding.animalTypeTx.text=animal.kindCd.toString()
        binding.animalCareAddrTx.text=animal.careAddr.toString()
        binding.animalAgeTx.text=animal.age.toString()
        binding.animalWeightTx.text=animal.weight.toString()
        binding.animalCareNmTx.text=animal.careNm.toString()
        binding.animalCareTelTx.text=animal.careTel.toString()
        binding.animalHappenPlaceTx.text=animal.happenPlace.toString()

        binding.itemAnimalImgIv.setImageResource((animal.filename!!))
    }
}
