package com.virtualynx.newsviewer.ui.category

import android.view.View
import com.virtualynx.newsviewer.model.CategoryModel

interface CategoryItemClickListener {
    fun onItemClicked(view: View, category: CategoryModel?)
}