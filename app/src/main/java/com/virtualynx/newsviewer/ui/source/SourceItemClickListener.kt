package com.virtualynx.newsviewer.ui.source

import android.view.View
import com.virtualynx.newsviewer.model.SourceModel

interface SourceItemClickListener {
    fun onItemClicked(view: View, source: SourceModel?)
}