package com.androiddevs.grocerylist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.final2.ui.dashboard.ResourceDao

import com.example.final2.ui.dashboard.ResourceItem

@Database(
    entities = [ResourceItem::class],
    version = 1
)
abstract class ResourceDatabase: RoomDatabase() {

    abstract fun getResourceDao(): ResourceDao

    companion object {
        @Volatile
        private var instance: ResourceDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
                instance
                    ?: createDatabase(
                        context
                    ).also { instance = it }
            }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                ResourceDatabase::class.java, "ResourceDB.db").build()
    }
}