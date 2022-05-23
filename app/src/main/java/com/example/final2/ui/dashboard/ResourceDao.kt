package com.example.final2.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ResourceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ResourceItem)

    @Delete
    suspend fun delete(item: ResourceItem)

    @Query("SELECT * FROM resource_items")
    fun getAllResourceItems(): LiveData<List<ResourceItem>>
}