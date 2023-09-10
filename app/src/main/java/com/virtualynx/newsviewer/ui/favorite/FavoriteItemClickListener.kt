package com.virtualynx.newsviewer.ui.favorite

import android.view.View
import com.virtualynx.newsviewer.model.ArticleModel
import com.virtualynx.newsviewer.model.SourceModel

interface FavoriteItemClickListener {
    fun onItemClicked(view: View, article: ArticleModel?)
    fun onItemFavoriteClicked(view: View, article: ArticleModel?)
}