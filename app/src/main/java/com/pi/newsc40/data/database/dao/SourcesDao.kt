package com.pi.newsc40.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pi.newsc40.data.api.model.Source

@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(sources: List<Source>)

    @Query("select * from Source where category = :categoryId")
    suspend fun getSources(categoryId: String): List<Source>
}