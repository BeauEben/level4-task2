package com.example.level4_task2

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.view.View.INVISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.graphics.drawable.toDrawable
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game.*
import java.lang.Math.random
import java.text.DateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

const val REQ_HISTORY_KEY = "req_history"
const val BUNDLE_HISTORY_KEY = "bundle_history"

class GameFragment : Fragment(){

    var win: Int = 0
    var draw: Int = 0
    var lose: Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        viewInit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun  viewInit(){

        ivRock.setOnClickListener{
            playerMove(0)
        }

        ivPaper.setOnClickListener{
            playerMove(1)
        }

        ivScissors.setOnClickListener{
            playerMove(2)
        }

        tvStatistics.text = getString(R.string.statistics, win, draw, lose)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun playerMove(Move: Int){
        when (Move) {
            0 -> {
                ivPlayerRoll.setImageResource(R.drawable.rock)
            }
            1 -> ivPlayerRoll.setImageResource(R.drawable.paper)
            2 -> ivPlayerRoll.setImageResource(R.drawable.scissors)
        }
        calculateWinner(Move, cpuRoll())
    }

    private fun cpuRoll(): Int {
        val roll = Random.nextInt(0, 3)
        when (roll){
            0 -> ivCPURoll.setImageResource(R.drawable.rock)
            1 -> ivCPURoll.setImageResource(R.drawable.paper)
            2 -> ivCPURoll.setImageResource(R.drawable.scissors)
        }
        return roll
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateWinner(player: Int, cpu: Int){
        var outcome: String
        var cMove: Int
        var pMove: Int
        var itemDate: String

        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = currentTime.format(formatter)


        itemDate = formatted

        var historyItem : HistoryItem? = null

        if (player == 0){
            if (cpu == 0){
                draw += 1
                Toast.makeText(activity, getString(R.string.historyDraw), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.rock
                cMove = R.drawable.rock
                outcome = getString(R.string.historyDraw)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }

            if (cpu == 1){
                lose += 1
                Toast.makeText(activity, getString(R.string.historyLose), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.rock
                cMove = R.drawable.paper
                outcome = getString(R.string.historyLose)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }

            if (cpu == 2){
                win += 1
                Toast.makeText(activity, getString(R.string.historyWin), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.rock
                cMove = R.drawable.scissors
                outcome = getString(R.string.historyWin)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }
        }

        if (player == 1){
            if (cpu == 0){
                win += 1
                Toast.makeText(activity, getString(R.string.historyWin), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.paper
                cMove = R.drawable.rock
                outcome = getString(R.string.historyWin)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }

            if (cpu == 1){
                draw += 1
                Toast.makeText(activity, getString(R.string.historyDraw), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.paper
                cMove = R.drawable.paper
                outcome = getString(R.string.historyDraw)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }

            if (cpu == 2){
                lose += 1
                Toast.makeText(activity, getString(R.string.historyLose), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.paper
                cMove = R.drawable.scissors
                outcome = getString(R.string.historyLose)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }
        }

        if (player == 2){
            if (cpu == 0){
                lose += 1
                Toast.makeText(activity, getString(R.string.historyLose), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.scissors
                cMove = R.drawable.rock
                outcome = getString(R.string.historyLose)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }

            if (cpu == 1){
                win += 1
                Toast.makeText(activity, getString(R.string.historyWin), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.scissors
                cMove = R.drawable.paper
                outcome = getString(R.string.historyWin)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }

            if (cpu == 2){
                draw += 1
                Toast.makeText(activity, getString(R.string.historyDraw), Toast.LENGTH_SHORT).show()

                pMove = R.drawable.scissors
                cMove = R.drawable.scissors
                outcome = getString(R.string.historyDraw)

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)
            }
        }

        setFragmentResult(REQ_HISTORY_KEY, bundleOf(Pair(BUNDLE_HISTORY_KEY, historyItem)))

        tvStatistics.text = getString(R.string.statistics, win, draw, lose)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_history).isVisible = true
        menu.findItem(R.id.action_delete_all).isVisible = false
    }
}