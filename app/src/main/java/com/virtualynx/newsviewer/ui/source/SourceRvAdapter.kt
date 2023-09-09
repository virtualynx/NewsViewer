package com.virtualynx.newsviewer.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.SourceModel

class SourceRvAdapter(): RecyclerView.Adapter<SourceRvAdapter.ViewHolder>(){
    private var _data : List<SourceModel>? = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textTitle: TextView
        val textBody: TextView

        init{
            textTitle = itemView.findViewById(R.id.text_item_title)
            textBody = itemView.findViewById(R.id.text_item_body)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_source, parent, false))
//        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_item_source, parent, false))
    }

    override fun getItemCount(): Int {
        return _data?.size ?: 0
    }

    fun setData(list: List<SourceModel>){
        _data = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _data?.get(position)

//        holder.bindView(item)
        holder.textTitle.text = item?.id
        holder.textBody.text = item?.name
    }

//    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
//        fun bindView(item: SourceModel?) {
//            val textTitle = itemView.findViewById<TextView>(R.id.text_item_title)
//            val textBody = itemView.findViewById<TextView>(R.id.text_item_body)
//
//            textTitle.text = item?.id
//            textBody.text = item?.name
//        }
//    }
}