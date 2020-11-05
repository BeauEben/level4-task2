package com.example.level4_task2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)
abstract class HistoryItemDatabase : RoomDatabase() {
    abstract fun historyItemDao(): HistoryDao

    companion object{
        private const val DATABASE_NAME = "HISTORY_ITEM_DATABASE"

        @Volatile
        private var historyItemRoomDatabaseInstance: HistoryItemDatabase? = null

        fun getDatabase(context: Context): HistoryItemDatabase? {
            if (historyItemRoomDatabaseInstance == null){
                synchronized(HistoryItemDatabase::class.java){
                    if (historyItemRoomDatabaseInstance == null){
                        historyItemRoomDatabaseInstance = Room.databaseBuilder(context.applicationContext, HistoryItemDatabase::class.java, DATABASE_NAME)
                                .build()
                    }
                }
            }
            return historyItemRoomDatabaseInstance
        }
    }
}