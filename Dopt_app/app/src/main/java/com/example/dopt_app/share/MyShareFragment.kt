package com.example.dopt_app.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.FragmentMyShareBinding
import com.google.gson.Gson

class MyShareFragment : Fragment() {

    lateinit var binding: FragmentMyShareBinding
    private var gson: Gson = Gson()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyShareBinding.inflate(inflater,container,false)

        // Home 에서 넘어온 데이터 받아오기
        val shareJson = arguments?.getString("share")
        val share = gson.fromJson(shareJson, Share::class.java)
        // Home에서 넘어온 데이터를 반영
        setInit(share)

        binding.backMyshareBt.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, ShareFragment())
                .commitAllowingStateLoss()
        }
        return binding.root
    }

    private fun setInit(share: Share) {
        binding.itemAnimalImgIv.setImageResource(share.aniImg!!)
        binding.myShareTitleTv.text = share.title.toString()
        binding.myShareTextTv.text = share.text.toString()
        binding.userNameTx.text=share.userNm.toString()
        binding.shareWeekTx.text= share.shareWk.toString()
        binding.shareDateTx.text= share.upDate

    }


}