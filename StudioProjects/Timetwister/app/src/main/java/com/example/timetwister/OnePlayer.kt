package com.example.timetwister

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.homescreen.*
import kotlinx.android.synthetic.main.timer.*

class OnePlayer : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.one_player, container, false)

        (activity as PlayingActivity).setUpMenu(view)

        // Create player one
        val playerOne: RelativeLayout = view.findViewById(R.id.player_one)
        (activity as PlayingActivity).setUpLayout(playerOne)
        val playerOneTimerText: TextView = playerOne.findViewById(R.id.text_timer)

        // Set up timer transitions
        val playerOneTimer: Button = playerOne.findViewById(R.id.timer_button)
        playerOneTimer.setOnClickListener {
            (activity as PlayingActivity).timerClicked(playerOneTimerText, playerOneTimerText)
        }

        return view
    }


}