package com.example.level4_task2

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM historyTable")
     fun getAllHistoryItems(): List<HistoryItem>

    @Insert
     fun insertHistoryItem(historyItem: HistoryItem)

    @Delete
     fun deleteHistoryItem(historyItem: HistoryItem)

    @Update
     fun updateHistoryItem(historyItem: HistoryItem)

    @Query("DELETE FROM historyTable")
     fun deleteAllHistoryItems()
}