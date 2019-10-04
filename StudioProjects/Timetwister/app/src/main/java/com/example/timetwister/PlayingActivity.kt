package com.example.timetwister

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import java.util.*
import android.os.CountDownTimer

class PlayingActivity : AppCompatActivity() {

    private val NUM_PLAYERS = "NUM_PLAYERS"
    private val LIFETOTAL = "LIFETOTAL"
    private val TIMER_LENGTH = "TIMER_LENGTH"

    private val defaultPlayers = 4
    private val defaultLifetotal = 40
    private val defaultTimer: Long = 300000
    private val timerDone = "0:00"
    private val running = "RUNNING"
    private val notRunning = "NOT_RUNNING"
    private val oneSec = 1000
    private val oneMin = 60000

    private var lifetotal = defaultLifetotal
    private var numPlayers = defaultPlayers
    private var timerLength = defaultTimer
    private var currentTimerTime = defaultTimer

    var playerOneStarted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        // Retrieve Values
        val bundle=intent.extras

        if(bundle!=null)
        {
            numPlayers = bundle.getInt(NUM_PLAYERS)
            lifetotal = bundle.getInt(LIFETOTAL)
            timerLength = (bundle.getInt(TIMER_LENGTH) * oneMin).toLong()
            currentTimerTime = timerLength
        }

        // Determine which fragment to launch
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        var fragment: Fragment = HomeScreenFrag()

        when (numPlayers) {
            //1 -> setContentView(R.layout.activity_main)
            2 -> fragment = TwoPlayer()
            3 -> fragment = ThreePlayer()
            4 -> fragment = FourPlayer()
            5 -> fragment = FivePlayer()
            6 -> fragment = SixPlayer()
        }

        // Complete Transaction
        fragmentTransaction.add(R.id.playing_activity, fragment)
        fragmentTransaction.commit()
    }

    private fun add(lifetotal: TextView) {
        lifetotal.text = (lifetotal.text.toString().toInt() + 1).toString()
    }

    private fun subtract(lifetotal: TextView) {
        lifetotal.text = (lifetotal.text.toString().toInt() - 1).toString()
    }

    fun setUpLayout(layout: RelativeLayout) {
        // Set up an individual player
        val playerLifetotal: TextView = layout.findViewById(R.id.life_total)
        playerLifetotal.text = lifetotal.toString()

        val playerTimer: TextView = layout.findViewById(R.id.text_timer)
        playerTimer.text = timerFormatter(timerLength)

        val playerAdd: Button = layout.findViewById(R.id.add_health)
        playerAdd.setOnClickListener {
            add(playerLifetotal)
        }

        val playerSubtract: Button = layout.findViewById(R.id.subtract_health)
        playerSubtract.setOnClickListener {
            subtract(playerLifetotal)
        }
    }

    fun setUpMenu(view: View) {
        // Set up menu_popup
        val menuButton: Button = view.findViewById(R.id.menu)
        val menuPopup: RelativeLayout = view.findViewById(R.id.menu_popup)
        menuButton.setOnClickListener {
            if (menuPopup.visibility == View.VISIBLE)
                menuPopup.visibility = View.GONE
            else
                menuPopup.visibility = View.VISIBLE
        }

        // Set up return to home screen
        val homescreenButton: Button = view.findViewById(R.id.homescreen_button)
        homescreenButton.setOnClickListener {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }

        // Set up reset
        val resetButton: Button = view.findViewById(R.id.reset_button)
        resetButton.setOnClickListener {
            val intent = Intent(this, PlayingActivity::class.java)
            intent.putExtra(NUM_PLAYERS, numPlayers)
            intent.putExtra(LIFETOTAL, lifetotal)
            intent.putExtra(TIMER_LENGTH, (timerLength / oneMin).toInt())
            startActivity(intent)
        }
    }

    fun startTimer(timerText: TextView) {
        val countDownTimer =  object: CountDownTimer(timerLength, oneSec.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                if (timerText.tag == notRunning)
                    cancel()

                currentTimerTime = millisUntilFinished
                timerUpdate(timerText)
            }

            override fun onFinish() {
                timerText.text = timerDone
            }
        }.start()
    }

    private fun timerUpdate(timerText: TextView) {
        timerText.text = timerFormatter(currentTimerTime)
    }

    private fun timerFormatter(time: Long): String {
        var minutes: Int = ((time / 1000) / 60).toInt()
        var seconds: Int = ((time / 1000) % 60).toInt()

        return String.format(Locale.getDefault(), "%d:%02d", minutes, seconds)
    }

    fun timerClicked(playerTimerA: TextView, playerTimerB: TextView) {
        if (!playerOneStarted) {
            playerOneStarted = true
            playerTimerA.tag = running
            startTimer(playerTimerA)
        } else {
            if (playerTimerA.tag == "RUNNING") {
                playerTimerA.tag = "NOT_RUNNING"
                playerTimerB.tag = "RUNNING"
                startTimer(playerTimerB)
            }
        }
    }
}
