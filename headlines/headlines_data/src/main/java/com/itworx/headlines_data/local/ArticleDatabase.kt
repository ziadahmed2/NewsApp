package com.itworx.headlines_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itworx.headlines_data.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val dao: ArticleDao
}