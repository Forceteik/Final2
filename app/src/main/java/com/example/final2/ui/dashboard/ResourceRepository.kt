package com.example.final2.ui.dashboard

import com.androiddevs.grocerylist.data.db.ResourceDatabase

class ResourceRepository(
    private val db: ResourceDatabase
) {
    suspend fun upsert(item: ResourceItem) = db.getResourceDao().upsert(item)

    suspend fun delete(item: ResourceItem) = db.getResourceDao().delete(item)

    fun getAllResourceItems() = db.getResourceDao().getAllResourceItems()
}