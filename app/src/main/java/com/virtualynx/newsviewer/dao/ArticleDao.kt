package com.virtualynx.newsviewer.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.virtualynx.newsviewer.entity.ArticleEntity
import com.virtualynx.newsviewer.model.ArticleModel

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articleentity")
    fun getAll(): List<ArticleEntity>

    @Query("SELECT * FROM articleentity WHERE url = (:url)")
    fun findByUrl(url: String): ArticleEntity

    @Insert
    fun insertAll(article: ArticleEntity)

    @Delete
    fun delete(article: ArticleEntity)
}