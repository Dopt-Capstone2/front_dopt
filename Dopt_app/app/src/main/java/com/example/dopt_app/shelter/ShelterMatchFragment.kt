package com.example.dopt_app.shelter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Animal
import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.FragmentShelterMatchBinding
import com.example.dopt_app.home.HomeFragment
import com.google.gson.Gson

class ShelterMatchFragment : Fragment(){
    lateinit var binding: FragmentShelterMatchBinding
    private var gson: Gson = Gson()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShelterMatchBinding.inflate(inflater, container, false)

        val matchJason = arguments?.getString("animal")
        val animal = gson.fromJson(matchJason, Animal::class.java)

        setInit(animal)

        binding.shelterBackIg.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ShelterHomeFragment())
                .commitAllowingStateLoss()
        }
        return binding.root

    }

    private fun setInit(animal: Animal) {
        binding.animalProcessState.text=animal.processState.toString()
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