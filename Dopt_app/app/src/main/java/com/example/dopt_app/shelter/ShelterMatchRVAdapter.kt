package com.example.dopt_app.shelter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopt_app.data.Animal
import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Share
import com.example.dopt_app.databinding.ItemShareBinding
import com.example.dopt_app.databinding.ItemShelterMatchBinding
import com.example.dopt_app.home.ShareRVAdapter

class ShelterMatchRVAdapter (private val matchDatas: ArrayList<Animal>) : RecyclerView.Adapter<ShelterMatchRVAdapter.ViewHolder>() {

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemClick(animal: Animal)
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

    fun  addItem(animal: Animal){
        matchDatas.add(animal)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(matchDatas[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(matchDatas[position])}}

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = matchDatas.size

    inner class ViewHolder(val binding: ItemShelterMatchBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(animal: Animal){
            binding.animalGenderTx.text=animal.sexCd
            binding.animalNeuterYnTx.text=animal.neuterYn
            binding.animalTypeTx.text=animal.kindCd
            binding.animalPlaceTx.text=animal.careAddr
            binding.itemAnimalImgIv.setImageResource((animal.filename!!))
        }
    }
}