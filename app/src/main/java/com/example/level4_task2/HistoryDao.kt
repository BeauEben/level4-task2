package com.example.level4_task2

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM historyTable")
    suspend fun getAllHistoryItems(): List<HistoryItem>

    @Insert
    suspend fun insertHistoryItem(historyItem: HistoryItem)

    @Delete
    suspend fun deleteHistoryItem(historyItem: HistoryItem)

    @Update
    suspend fun updateHistoryItem(historyItem: HistoryItem)

    @Query("DELETE FROM historyTable")
    suspend fun deleteAllHistoryItems()
}