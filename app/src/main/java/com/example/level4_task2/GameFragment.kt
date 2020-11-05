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

        viewInit()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun  viewInit(){

        ivRock.setOnClickListener{
            playerMove(0)
        }

        ivPaper.setOnClickListener{
            //playerMove(1)
        }

        ivScissors.setOnClickListener{
            //playerMove(2)
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
    @SuppressLint("StringFormatMatches")
    private fun calculateWinner(player: Int, cpu: Int){
        var outcome: String
        var cMove: Int
        var pMove: Int
        var itemDate: String

        val currentTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
        val formatted = currentTime.format(formatter)


        var historyItem : HistoryItem

        if (player == 0){
            if (cpu == 0){
                draw += 1
                Toast.makeText(activity, "its a draw", Toast.LENGTH_SHORT).show()

                pMove = R.drawable.rock
                cMove = R.drawable.rock
                outcome = "Draw"
                itemDate = formatted

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)

                setFragmentResult(REQ_HISTORY_KEY, bundleOf(Pair(BUNDLE_HISTORY_KEY, historyItem)))
            }

            if (cpu == 1){
                lose += 1
                Toast.makeText(activity, "you lost", Toast.LENGTH_SHORT).show()

                pMove = R.drawable.rock
                cMove = R.drawable.paper
                outcome = "Computer wins!"
                itemDate = formatted

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)

                setFragmentResult(REQ_HISTORY_KEY, bundleOf(Pair(BUNDLE_HISTORY_KEY, historyItem)))
            }

            if (cpu == 2){
                win += 1
                Toast.makeText(activity, "you won", Toast.LENGTH_SHORT).show()

                pMove = R.drawable.rock
                cMove = R.drawable.scissors
                outcome = "You won!"
                itemDate = formatted

                historyItem = HistoryItem(outcome, cMove, pMove, itemDate)

                setFragmentResult(REQ_HISTORY_KEY, bundleOf(Pair(BUNDLE_HISTORY_KEY, historyItem)))
            }
        }

        if (player == 1){
            if (cpu == 0){
                win += 1
                Toast.makeText(activity, "you won", Toast.LENGTH_SHORT).show()
            }

            if (cpu == 1){
                draw += 1
                Toast.makeText(activity, "its a draw", Toast.LENGTH_SHORT).show()
            }

            if (cpu == 2){
                lose += 1
                Toast.makeText(activity, "you lost", Toast.LENGTH_SHORT).show()
            }
        }

        if (player == 2){
            if (cpu == 0){
                lose += 1
                Toast.makeText(activity, "you lost", Toast.LENGTH_SHORT).show()
            }

            if (cpu == 1){
                win += 1
                Toast.makeText(activity, "you won", Toast.LENGTH_SHORT).show()
            }

            if (cpu == 2){
                draw += 1
                Toast.makeText(activity, "its a draw", Toast.LENGTH_SHORT).show()
            }
        }

        tvStatistics.text = getString(R.string.statistics, win, draw, lose)
    }
}