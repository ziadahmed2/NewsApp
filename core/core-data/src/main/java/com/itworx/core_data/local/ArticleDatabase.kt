package com.itworx.core_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itworx.core_data.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val dao: ArticleDao
}