package com.pi.newsc40.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pi.newsc40.data.api.model.SourceDM

@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(sources: List<SourceDM>)

    @Query("select * from SourceDM where category = :categoryId")
    suspend fun getSources(categoryId: String): List<SourceDM>
}