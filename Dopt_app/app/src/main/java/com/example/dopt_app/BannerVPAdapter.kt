package com.example.dopt_app

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

//홈페이지 배너를 위한 클래스

class BannerVPAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    private val fragmentlist: ArrayList<Fragment> = ArrayList()
    //이 클래스 안에서만 사용할 변수여서 private 설정, 외부에서 데이터 변경을하지 못하게

    //이 클래스에 연결된 뷰페이저한테 데이터를 몇개 전달할건지
    override fun getItemCount(): Int = fragmentlist.size

    //프레그먼트리스트 안에있는 아이템, 즉 프레그먼트를 생성
    override fun createFragment(position: Int): Fragment = fragmentlist[position]

    //프레그먼트 리스트로 받은 인자값을 출력
    fun addFragment(fragment: Fragment){
        fragmentlist.add(fragment)
        notifyItemInserted(fragmentlist.size-1)
    }
}