package com.example.final2.ui.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*


class ResourceViewModel(private val repository: ResourceRepository) : ViewModel() {
    fun upsert(item: ResourceItem) =
        CoroutineScope(Dispatchers.Main).launch {
            repository.upsert(item)
        }

    fun delete(item: ResourceItem) =
        CoroutineScope(Dispatchers.Main).launch {
            repository.delete(item)
    }

    fun getAllResourceItems() = repository.getAllResourceItems()

}