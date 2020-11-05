package com.example.level4_task2

import android.content.Context

public class HistoryItemRepository (context: Context) {

    private var historyDao: HistoryDao

    init {
        val historyItemDatabase = HistoryItemDatabase.getDatabase(context)
        historyDao = historyItemDatabase!!.historyItemDao()
    }

    fun getAllHistoryItems(): List<HistoryItem>{
        return historyDao.getAllHistoryItems()
    }

    fun insertHistoryItem(historyItem: HistoryItem){
        historyDao.insertHistoryItem(historyItem)
    }

    fun deleteHistoryItem(historyItem: HistoryItem){
        historyDao.deleteHistoryItem(historyItem)
    }

    fun updateHistoryItem(historyItem: HistoryItem){
        historyDao.updateHistoryItem(historyItem)
    }

    fun deleteAllHistoryItems(){
        historyDao.deleteAllHistoryItems()
    }

}