package com.example.dopt_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dopt_app.databinding.FragmentHomeBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.Gson
import java.util.ArrayList

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    private var shareDatas = ArrayList<Share>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        shareDatas.apply {
            add(Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ 정말 뿌듯해요", R.drawable.dog_num_one, "test11"))
            add(Share("냥냥이 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ 정말 뿌듯해요", R.drawable.cat_num_one, "test22"))
            add(Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ 정말 뿌듯해요", R.drawable.dog_num_one, "test11"))
            add(Share("냥냥이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ 정말 뿌듯해요", R.drawable.cat_num_one, "test22"))
            add(Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ 정말 뿌듯해요", R.drawable.dog_num_one, "test11"))
        }

        val shareRVAdapter = ShareRVAdapter(shareDatas)

        binding.homeRecentShareRv.adapter=shareRVAdapter

        shareRVAdapter.setMyItemClickListener(object : ShareRVAdapter.MyItemClickListener{
            override fun onItemClick(share: Share) {
                changeNewShareFragment(share)
            }
        })

        binding.homeRecentShareRv.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val bannerAdapter = BannerVPAdapter(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_001))
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_002))
        bannerAdapter.addFragment(BannerFragment(R.drawable.banner_003))

        binding.homeBannerVp.adapter = bannerAdapter
        binding.homeBannerVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    private fun changeNewShareFragment(share: Share) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, NewShareFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val shareJson = gson.toJson(share)
                    putString("share", shareJson)
                }
            })
            .commitAllowingStateLoss()
    }
}