package com.example.dopt_app.match

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dopt_app.R
import com.example.dopt_app.data.DataX
import com.example.dopt_app.module.GlideApp

class CardStackAdapter(
    val context: Context,
    val items: List<DataX>
    ):
    RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    private val TAG = "CardStackAdapter"
    //아이템뷰를 넣어주는곳
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    //데이터를 넣어주는 부분
    override fun onBindViewHolder(holder: CardStackAdapter.ViewHolder, position: Int) {
        holder.binding(items[position])
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

        // val btn = itemView.findViewById<TextView>(R.id.item_more_info)

        fun binding(items: DataX){

            val imgUrl = items.filename
            GlideApp.with(context).load(imgUrl).into(image)

            kind.text=items.kindCd
            processState.text=items.processState
            sexCd.text=items.sexCd
            age.text=items.age
            careNm.text=items.careNm

            Log.d(TAG+"왜", kind.toString())

        }
    }
}

