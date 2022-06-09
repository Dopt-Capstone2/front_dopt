package com.example.dopt_app.shelter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dopt_app.R
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.databinding.ItemShelterMatchBinding
import com.example.dopt_app.databinding.ItemSmallAnimalBinding

class ShelterMatchRVAdapter(val context: ShelterHomeFragment, private val shelterItems: ArrayList<Bookmark>) : RecyclerView.Adapter<ShelterMatchRVAdapter.ViewHolder>() {
    private val TAG = "ShelterMatchRVAdapter"

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemClick(item: Bookmark)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShelterMatchRVAdapter.ViewHolder {
        val binding: ItemSmallAnimalBinding = ItemSmallAnimalBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)

    }

    fun  addItem(item: Bookmark){
        shelterItems.add(item)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(shelterItems[position])
        holder.itemView.setOnClickListener {
            mItemClickListener.onItemClick(shelterItems[position])}}

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = shelterItems.size

    inner class ViewHolder(val binding: ItemSmallAnimalBinding): RecyclerView.ViewHolder(binding.root){

        private val image: ImageView = itemView.findViewById(R.id.item_animal_img_iv)
        private val kindCd: TextView = itemView.findViewById(R.id.animal_type_tx)
        private val age: TextView = itemView.findViewById(R.id.star_neuterYn_tx)
        private val sexCd: TextView = itemView.findViewById(R.id.animal_gender_tx)
        private val place: TextView = itemView.findViewById(R.id.animal_place_tx)
        private val state: TextView = itemView.findViewById(R.id.item_animal_star_status_tv)

        fun bind(animalList: Bookmark) {
            val imgUrl = animalList.filename
            Log.d(TAG, imgUrl)
            Glide.with(context).load(imgUrl).into(image)

            when (animalList.sexCd) {
                "M" -> {
                    sexCd.text = "수컷"
                }
                "F" -> {
                    sexCd.text = "암컷"
                }
                else -> {
                    sexCd.text = "성별미상"
                }
            }
            when (animalList.isConsidered) {
                0 -> {
                    state.text = "즐겨찾기 설정"
                }
                1 -> {
                    state.text = "입양 신청 완료"
                }
                2 -> {
                    state.text = "입양 허가"
                }
                3 -> {
                    state.text = "입양 거절"
                }
            }

            age.text = animalList.age
            kindCd.text = animalList.kindCd
            place.text = animalList.careAddr
        }
    }
}