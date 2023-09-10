package com.virtualynx.newsviewer.ui.article

import android.view.View
import com.virtualynx.newsviewer.model.ArticleModel
import com.virtualynx.newsviewer.model.SourceModel

interface ArticleItemClickListener {
    fun onItemClicked(view: View, article: ArticleModel?)
    fun onItemFavoriteClicked(view: View, article: ArticleModel?)
}