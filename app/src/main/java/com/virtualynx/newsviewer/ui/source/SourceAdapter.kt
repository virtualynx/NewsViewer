package com.virtualynx.newsviewer.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.Source

class SourceAdapter: RecyclerView.Adapter<SourceAdapter.SourceViewHolder>(){

    private var data : ArrayList<Source>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_source, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bindView(item)
    }

    fun setData(list: ArrayList<Source>){
        data = list
        notifyDataSetChanged()
    }

    class SourceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindView(item: Source?) {
//            itemView.tv_home_item_title.text = item?.title
//            itemView.tv_home_item_body.text = item?.body
        }

    }
}