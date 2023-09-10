package com.virtualynx.newsviewer.ui.article

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.SourceModel

class ArticleAdapter: RecyclerView.Adapter<ArticleAdapter.ViewHolder>(){

    private var data : ArrayList<SourceModel>?=null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bindView(item: SourceModel?) {
//            itemView.tv_home_item_title.text = item?.title
//            itemView.tv_home_item_body.text = item?.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_source, parent, false))
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.bindView(item)
    }

    fun setData(list: ArrayList<SourceModel>){
        data = list
        notifyDataSetChanged()
    }
}