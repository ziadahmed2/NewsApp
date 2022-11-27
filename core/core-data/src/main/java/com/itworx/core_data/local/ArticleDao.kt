package com.itworx.core_data.local

import androidx.room.*
import com.itworx.core_data.local.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: ArticleEntity)

    @Query("SELECT * FROM articleentity")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Query("DELETE FROM articleentity WHERE title = :title")
    suspend fun deleteArticle(title: String)
}