package com.virtualynx.newsviewer.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.ArticleModel

class FavoriteAdapter(
    private val _listener: FavoriteItemClickListener
): RecyclerView.Adapter<FavoriteAdapter.ViewHolder>(){
    private var _data : List<ArticleModel>? = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val layout: ConstraintLayout
        val textTitle: TextView
        val textBody: TextView
        val btnDelete: Button

        init{
            layout = itemView.findViewById(R.id.layout_article)
            textTitle = itemView.findViewById(R.id.text_title)
            textBody = itemView.findViewById(R.id.text_body)
            btnDelete = itemView.findViewById(R.id.btn_del_favorite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_favorite, parent, false))
    }

    override fun getItemCount(): Int {
        return _data?.size ?: 0
    }

    fun setData(list: List<ArticleModel>){
        _data = list
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = _data?.get(position)

        holder.textTitle.text = item?.title
        holder.textBody.text = item?.content
        holder.layout.setOnClickListener {
            _listener.onItemClicked(it, item)
        }
        holder.btnDelete.setOnClickListener {
            _listener.onItemFavoriteClicked(it, item)
        }
    }
}
