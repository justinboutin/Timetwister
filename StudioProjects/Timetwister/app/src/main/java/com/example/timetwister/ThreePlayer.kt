package com.example.timetwister

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView

class ThreePlayer : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.three_player, container, false)

        (activity as PlayingActivity).setUpMenu(view)

        // Create player one
        val playerOne: RelativeLayout = view.findViewById(R.id.player_one)
        val playerOneTimerText: TextView = playerOne.findViewById(R.id.text_timer)
        (activity as PlayingActivity).setUpLayout(playerOne)

        // Create player two
        val playerTwo: RelativeLayout = view.findViewById(R.id.player_two)
        val playerTwoTimerText: TextView = playerTwo.findViewById(R.id.text_timer)
        (activity as PlayingActivity).setUpLayout(playerTwo)

        // Create player three
        val playerThree: RelativeLayout = view.findViewById(R.id.player_three)
        val playerThreeTimerText: TextView = playerThree.findViewById(R.id.text_timer)
        (activity as PlayingActivity).setUpLayout(playerThree)

        // Set up timer transitions
        val playerOneTimer: Button = playerOne.findViewById(R.id.timer_button)
        playerOneTimer.setOnClickListener {
            (activity as PlayingActivity).timerClicked(playerOneTimerText, playerTwoTimerText)
        }

        val playerTwoTimer: Button = playerTwo.findViewById(R.id.timer_button)
        playerTwoTimer.setOnClickListener {
            (activity as PlayingActivity).timerClicked(playerTwoTimerText, playerThreeTimerText)
        }

        val playerThreeTimer: Button = playerThree.findViewById(R.id.timer_button)
        playerThreeTimer.setOnClickListener {
            (activity as PlayingActivity).timerClicked(playerThreeTimerText, playerOneTimerText)
        }

        return view
    }
}