package com.itworx.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.itworx.core.data.local.ArticleDao
import com.itworx.core.data.local.entity.ArticleEntity

@Database(
    entities = [ArticleEntity::class],
    version = 1
)
abstract class ArticleDatabase : RoomDatabase() {

    abstract val dao: ArticleDao
}