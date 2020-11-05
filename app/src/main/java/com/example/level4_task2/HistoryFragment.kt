package com.example.level4_task2

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.Toast
import android.widget.Toolbar
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

    private val mainScope = CoroutineScope(Dispatchers.Main)

    private lateinit var historyItemRepository: HistoryItemRepository

    private val historyItems = arrayListOf<HistoryItem>()
    private val historyItemAdapter = HistoryItemAdapter(historyItems)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initViews()
        observeAddHistoryResult()


        historyItemRepository = HistoryItemRepository(requireContext())
        getHistoryItemsFromDatabase()
    }

    private fun initViews(){
        rvHistory.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        rvHistory.adapter = historyItemAdapter
        rvHistory.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvHistory)
    }

    private fun getHistoryItemsFromDatabase(){
        mainScope.launch {
            val historyItems = withContext(Dispatchers.IO){
                historyItemRepository.getAllHistoryItems()
            }
            this@HistoryFragment.historyItems.clear()
            this@HistoryFragment.historyItems.addAll(historyItems)
            historyItemAdapter.notifyDataSetChanged()
        }

    }

    private fun observeAddHistoryResult(){
        setFragmentResultListener(REQ_HISTORY_KEY) { _, bundle -> bundle.getParcelable<HistoryItem>(BUNDLE_HISTORY_KEY)?.let    {
                mainScope.launch {
                    withContext(Dispatchers.IO){
                        historyItemRepository.insertHistoryItem(it)
                    }
                }
                getHistoryItemsFromDatabase()
            }
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val itemToDelete = historyItems[position]

                mainScope.launch {
                    withContext(Dispatchers.IO) {
                        historyItemRepository.deleteHistoryItem(itemToDelete)
                    }
                    getHistoryItemsFromDatabase()
                }
                getHistoryItemsFromDatabase()
            }
        }
        return ItemTouchHelper(callback)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_history).isVisible = false
        menu.findItem(R.id.action_delete_all).isVisible = true
    }

    private fun deleteAllItems(){
        mainScope.launch{
            withContext(Dispatchers.IO){
                historyItemRepository.deleteAllHistoryItems()
            }
        }
        getHistoryItemsFromDatabase()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.action_delete_all ->{
                deleteAllItems()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
