package com.example.level4_task2

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)
abstract class HistoryItemDatabase : RoomDatabase{
    abstract fun historyItemDao(): HistoryDao

    
}