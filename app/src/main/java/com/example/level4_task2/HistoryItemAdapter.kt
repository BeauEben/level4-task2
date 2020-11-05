package com.example.level4_task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_item.view.*

class HistoryItemAdapter (private val historyItems: List<HistoryItem>) : RecyclerView.Adapter<HistoryItemAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun databind (historyItem: HistoryItem){
            itemView.tvHistoryDate.text = historyItem.historyDate
            itemView.tvHistoryOutcome.text = historyItem.historyOutcome
            itemView.ivComputer.setImageResource(historyItem.historyMoveComputer)
            itemView.ivPlayer.setImageResource(historyItem.historyMovePlayer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return historyItems.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(historyItems[position])
    }
}