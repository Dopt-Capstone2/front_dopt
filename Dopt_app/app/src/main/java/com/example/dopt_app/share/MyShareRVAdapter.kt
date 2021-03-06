package com.example.dopt_app.share

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.ItemMyShareBinding
import com.example.dopt_app.databinding.ItemShareBinding
import java.util.*


class MyShareRVAdapter (private val myshareList: ArrayList<Share>) : RecyclerView.Adapter<MyShareRVAdapter.ViewHolder>(){

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemClick(share: Share)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 뷰홀더에 던져줍니다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyShareRVAdapter.ViewHolder {
        val binding: ItemMyShareBinding = ItemMyShareBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    fun addItem(share: Share){
        myshareList.add(share)
        notifyDataSetChanged()
    }


    // 뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: MyShareRVAdapter.ViewHolder, position: Int) {
        holder.bind(myshareList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(myshareList[position]) }
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = myshareList.size

    // 뷰홀더
    inner class ViewHolder(val binding: ItemMyShareBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(share: Share){
            binding.itemAnimalImgIv.setImageResource(share.aniImg!!)
            binding.itemAnimalShareWeekTv.text = share.shareWk
            binding.itemAnimalShareShelterTv.text = share.shelNm
            binding.itemAnimalShareTitleTv.text = share.title
            binding.itemAnimalShareDateTv.text = share.upDate

        }
    }


}