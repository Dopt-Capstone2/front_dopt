package com.example.dopt_app.match

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.dopt_app.MainActivity
import com.example.dopt_app.R
import com.example.dopt_app.data.Item
import com.example.dopt_app.data.Share
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.activity_match.*

class CardStackAdapter(
    val context: Context,
    val items: List<Item>
    ):
    RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    // 클릭 인터페이스 정의
    interface MoreClickListener{
        fun onItemClick(v:View, items: Item)
    }

    // 리스너 객체를 전달받는 함수랑 리스너 객체를 저장할 변수
    private lateinit var moreClickListener: MoreClickListener

    fun setMyItemClickListener(itemClickListener: MoreClickListener){
        moreClickListener = itemClickListener
    }

    //아이템뷰를 넣어주는곳
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    //데이터를 넣어주는 부분
    override fun onBindViewHolder(holder: CardStackAdapter.ViewHolder, position: Int) {
        val item = items[position]
        // holder.itemView.setOnClickListener { moreClickListener.onItemClick(items [position]) }
    }

    //아이템들의 사이즈
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image = itemView.findViewById<ImageView>(R.id.cardImageArea)
        val kind = itemView.findViewById<TextView>(R.id.itemKindCd)
        val processState = itemView.findViewById<TextView>(R.id.animal_processState_tx)
        val sexCd = itemView.findViewById<TextView>(R.id.animal_sexCd_tx)
        val age = itemView.findViewById<TextView>(R.id.animal_age_tx)
        val careNm = itemView.findViewById<TextView>(R.id.animal_careNm_tx)


        fun binding(items : Item){
            val btn = itemView.findViewById<TextView>(R.id.item_more_info)
            itemView.setOnClickListener{
                moreClickListener.onItemClick(itemView,items)
            }
        }

    }


}

