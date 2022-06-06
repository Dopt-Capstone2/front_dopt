package com.example.dopt_app.shelter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dopt_app.R
import com.example.dopt_app.data.Item
import com.example.dopt_app.databinding.ItemShelterMatchBinding

class ShelterMatchRVAdapter(
    private val matchDatas: ArrayList<Item>) : RecyclerView.Adapter<ShelterMatchRVAdapter.ViewHolder>() {

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemClick(item: Item)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: ShelterMatchRVAdapter.MyItemClickListener

    fun setMyItemClickListener(itemClickListener: ShelterMatchRVAdapter.MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShelterMatchRVAdapter.ViewHolder {
        val binding: ItemShelterMatchBinding = ItemShelterMatchBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)

    }

    fun  addItem(item: Item){
        matchDatas.add(item)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(matchDatas[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(matchDatas[position])}}

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = matchDatas.size

    inner class ViewHolder(val binding: ItemShelterMatchBinding): RecyclerView.ViewHolder(binding.root){

        val image = itemView.findViewById<ImageView>(R.id.cardImageArea)
        val kind = itemView.findViewById<TextView>(R.id.itemKindCd)
        val processState = itemView.findViewById<TextView>(R.id.animal_processState_tx)
        val sexCd = itemView.findViewById<TextView>(R.id.animal_sexCd_tx)
        val age = itemView.findViewById<TextView>(R.id.animal_age_tx)
        val careNm = itemView.findViewById<TextView>(R.id.animal_careNm_tx)

        // val btn = itemView.findViewById<TextView>(R.id.item_more_info)

        fun binding(items: Item) {

//            val imgUrl = items.filename
//            Glide.with(context).load(imgUrl).into(image)

//            kind.text = items.kindCd
//            processState.text = items.processState
//            sexCd.text = items.sexCd
//            age.text = items.age
//            careNm.text = items.careNm
        }
    }
}