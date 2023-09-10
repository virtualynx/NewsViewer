package com.virtualynx.newsviewer.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.virtualynx.newsviewer.model.CategoryModel

class CategoryViewModel : ViewModel() {
    val categories : MutableLiveData<List<CategoryModel>> = MutableLiveData()
}