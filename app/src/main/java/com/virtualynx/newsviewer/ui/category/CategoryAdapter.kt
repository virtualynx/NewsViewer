package com.virtualynx.newsviewer.ui.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.CategoryModel

class CategoryAdapter(
    private val _context: Context,
    private val _listener: CategoryItemClickListener
): BaseAdapter()
{
    private var _data : List<CategoryModel>? = emptyList()

    override fun getCount(): Int {
        return _data?.size ?: 0
    }

    override fun getItem(position: Int): Any? {
        return _data?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(_context).inflate(R.layout.row_category, parent, false)
        }
        val currentItem = getItem(position) as CategoryModel
        val textCategory = convertView?.findViewById(R.id.text_source) as TextView
        textCategory.text = currentItem.name
        textCategory.setOnClickListener {
            _listener.onItemClicked(it, currentItem)
        }

        return convertView
    }

    fun setData(list: List<CategoryModel>){
        _data = list
        notifyDataSetChanged()
    }
}