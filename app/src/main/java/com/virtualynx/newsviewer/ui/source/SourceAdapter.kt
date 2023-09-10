package com.virtualynx.newsviewer.ui.source

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.SourceModel

class SourceAdapter(
    private val _listener: SourceItemClickListener
): RecyclerView.Adapter<SourceAdapter.ViewHolder>(){
    private var _data : List<SourceModel>? = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textSource: TextView

        init{
            textSource = itemView.findViewById(R.id.text_source)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_source, parent, false))
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

        holder.textSource.text = item?.name
        holder.textSource.setOnClickListener {
            _listener.onItemClicked(it, item)
        }
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
