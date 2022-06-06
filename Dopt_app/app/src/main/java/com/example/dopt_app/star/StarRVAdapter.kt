package com.example.dopt_app.star

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dopt_app.R
import com.example.dopt_app.data.Bookmark
import com.example.dopt_app.databinding.ItemSmallAnimalBinding
import kotlin.collections.ArrayList

class StarRVAdapter (private val animalList: ArrayList<Bookmark>) : RecyclerView.Adapter<StarRVAdapter.ViewHolder>(){

    // 클릭 인터페이스 정의
    interface MyItemClickListener{
        fun onItemClick(animal: Bookmark)
        fun onRemoveAlbum(position: Int)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        mItemClickListener = itemClickListener
    }

    // 뷰홀더를 생성해줘야 할 때 호출되는 함수 => 아이템 뷰 객체를 만들어서 뷰홀더에 던져줍니다.
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemSmallAnimalBinding = ItemSmallAnimalBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)

        return ViewHolder(binding)
    }

    fun addItem(bookmark: Bookmark){
        animalList.add(bookmark)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        animalList.removeAt(position)
        notifyDataSetChanged()
    }

    // 뷰홀더에 데이터를 바인딩해줘야 할 때마다 호출되는 함수 => 엄청나게 많이 호출
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(animalList[position])
        holder.itemView.setOnClickListener { mItemClickListener.onItemClick(animalList[position]) }
//        holder.binding.itemAlbumTitleTv.setOnClickListener { mItemClickListener.onRemoveAlbum(position) } //삭제됐을 때
    }

    // 데이터 세트 크기를 알려주는 함수 => 리사이클러뷰가 마지막이 언제인지를 알게 된다.
    override fun getItemCount(): Int = animalList.size

    // 뷰홀더
    inner class ViewHolder(val binding: ItemSmallAnimalBinding): RecyclerView.ViewHolder(binding.root){
        val image = itemView.findViewById<ImageView>(R.id.item_animal_img_iv)
        val kindCd = itemView.findViewById<TextView>(R.id.animal_type_tx)
        val neuterYn = itemView.findViewById<TextView>(R.id.star_neuterYn_tx)
        val sexCd = itemView.findViewById<TextView>(R.id.animal_gender_tx)
        val place = itemView.findViewById<TextView>(R.id.animal_place_tx)
        val state = itemView.findViewById<TextView>(R.id.item_animal_star_status_tv)

        fun bind(bookmark: Bookmark){
            if(bookmark.kindCd == "M"){
                kindCd.text = "수컷"
                state.text="즐겨찾기"
            }else if (bookmark.kindCd == "F"){
                kindCd.text = "암컷"
                state.text="입양 신청 완료"

            }else{
                kindCd.text = "성별미상"
            }

            if(bookmark.neuterYn == "Y"){
                neuterYn.text = "중성화(O)"
            }else if (bookmark.kindCd == "N"){
                neuterYn.text = "중성화(X)"
            }else{
                neuterYn.text = "미상"
            }
            sexCd.text=bookmark.sexCd
            place.text=bookmark.careAddr



            val imgUrl = bookmark.filename
            Glide.with(binding.root).load(imgUrl).into(image)
//            binding.itemAnimalImgIv.setImageResource((bookmark.filename!!))
        }
    }

    }