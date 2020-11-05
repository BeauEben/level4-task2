package com.example.level4_task2

import android.content.Context

public class HistoryItemRepository (context: Context) {

    private var historyDao: HistoryDao

    init {
        val historyItemDatabase = HistoryItemDatabase.getDatabase(context)
        historyDao = historyItemDatabase!!.historyItemDao()
    }

    suspend fun getAllHistoryItems(): List<HistoryItem>{
        return historyDao.getAllHistoryItems()
    }

    suspend fun insertHistoryItem(historyItem: HistoryItem){
        historyDao.insertHistoryItem(historyItem)
    }

    suspend fun deleteHistoryItem(historyItem: HistoryItem){
        historyDao.deleteHistoryItem(historyItem)
    }

    suspend fun updateHistoryItem(historyItem: HistoryItem){
        historyDao.updateHistoryItem(historyItem)
    }

    suspend fun deleteAllHistoryItems(){
        historyDao.deleteAllHistoryItems()
    }

}