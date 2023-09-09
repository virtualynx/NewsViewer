package com.virtualynx.newsviewer.ui.source

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.virtualynx.newsviewer.R
import com.virtualynx.newsviewer.model.Source

class SourceArrayAdapter(
    private val context: Activity,
    private val sources: List<Source>
) : ArrayAdapter<Int>(context, R.layout.row_source) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val source: Source = sources[position]

//        val binding = RowSourceBinding.inflate(inflater, parent, false)
//        binding.sourceName.text = source.name

        var rowView = inflater.inflate(R.layout.row_source, null, true)

        val sourceName: TextView = rowView.findViewById(R.id.sourceName)
        sourceName.text = source.name

        return rowView
    }
}