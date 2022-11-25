package com.itworx.headlines_data.local

import androidx.room.*
import com.itworx.headlines_data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

    @Query("SELECT * FROM articleentity")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Delete
    suspend fun deleteArticle(article: ArticleEntity)
}