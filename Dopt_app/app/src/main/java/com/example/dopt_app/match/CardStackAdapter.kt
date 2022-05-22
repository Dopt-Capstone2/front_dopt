package com.example.dopt_app.match

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dopt_app.R

class CardStackAdapter(val context: Context, val items: List<String>): RecyclerView.Adapter<CardStackAdapter.ViewHolder>() {

    //아이템뷰를 넣어주는곳
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardStackAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view : View = inflater.inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    //데이터를 넣어주는 부분
    override fun onBindViewHolder(holder: CardStackAdapter.ViewHolder, position: Int) {
        holder.binding((items[position]))
    }

    //아이템들의 사이즈
    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun binding(data : String){

        }
    }
}