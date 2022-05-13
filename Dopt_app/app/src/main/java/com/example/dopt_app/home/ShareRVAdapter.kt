package com.example.dopt_app.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.ItemShareBinding
import kotlin.collections.ArrayList

class ShareRVAdapter(private val shareList: ArrayList<Share>) : RecyclerView.Adapter<ShareRVAdapter.ViewHolder>() {

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemClick(share: Share)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemShareBinding = ItemShareBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)

    }

    fun  addItem(share: Share){
        shareList.add(share)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shareList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(shareList[position])}}

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = shareList.size

    inner class ViewHolder(val binding: ItemShareBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(share: Share){
            binding.itemAnimalImgIv.setImageResource(share.aniImg!!)
            binding.itemAnimalShareTitleTv.text = share.title
        }
    }
}