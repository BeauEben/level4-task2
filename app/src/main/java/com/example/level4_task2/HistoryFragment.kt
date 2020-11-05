package com.example.level4_task2

import android.annotation.SuppressLint
import android.app.ActionBar
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.fragment_history.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HistoryFragment : Fragment() {

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
    }

    private fun getHistoryItemsFromDatabase(){
        val historyItems = historyItemRepository.getAllHistoryItems()
        this@HistoryFragment.historyItems.clear()
        this@HistoryFragment.historyItems.addAll(historyItems)
        historyItemAdapter.notifyDataSetChanged()
    }

    private fun observeAddHistoryResult(){
        setFragmentResultListener(REQ_HISTORY_KEY) { _, bundle -> bundle.getParcelable<HistoryItem>(BUNDLE_HISTORY_KEY)?.let {
                historyItemRepository.insertHistoryItem(it)
                getHistoryItemsFromDatabase()
            }
        }
    }

    @SuppressLint("ResourceType")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        //super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_history, menu)
    }
}
