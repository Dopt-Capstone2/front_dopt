package com.example.dopt_app.home

import com.example.dopt_app.share.MyShareFragment
import com.example.dopt_app.share.MyShareRVAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.FragmentHomeMoreBinding
import com.google.gson.Gson

class HomeMoreFragment : Fragment() {

    lateinit var binding: FragmentHomeMoreBinding
    private var myshareDatas = ArrayList<Share>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeMoreBinding.inflate(inflater, container, false)

        myshareDatas.apply {
            add(
                Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현", "1주차")
            )
            add(
                Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요 왕왕\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현", "2주차")
            )
            add(
                Share("냥냥이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.cat_num_one, "2022-05-02", "test11", "채현채현", "3주차")
            )
            add(
                Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현", "4주차")
            )
            add(
                Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현", "5주차")
            )
            add(
                Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현", "6주차")
            )
            add(
                Share("멍멍이의 성장과정", "입양했을때는 엄청 작았는데 벌써 이렇게 컸답니다ㅠㅠ\n 정말 뿌듯해요\n" +
                        " 다음 주차에도 후기 올리러 오겠습니다 ;) \n" +
                        " 만관부~~", R.drawable.dog_num_one, "2022-05-02", "test11", "채현채현", "7주차")
            )
        }

        // 더미데이터랑 Adapter 연결
        val myshareRVAdapter = MyShareRVAdapter(myshareDatas)
        // 리사이클러뷰에 어댑터를 연결
        binding.shareMyAnimalsRv.adapter = myshareRVAdapter

        myshareRVAdapter.setMyItemClickListener(object : MyShareRVAdapter.MyItemClickListener{

            override fun onItemClick(share: Share) {
                changeMyshareFragment(share)
            }

        })
        // 레이아웃 매니저 설정
        binding.shareMyAnimalsRv.layoutManager = GridLayoutManager(context, 2, )

        return binding.root
    }

    private fun changeMyshareFragment(share: Share) {
        (context as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, NewShareFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val myshareJson = gson.toJson(share)
                    putString("share", myshareJson)
                }
            })
            .commitAllowingStateLoss()
    }
}