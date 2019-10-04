package com.example.timetwister

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.ContextCompat.startActivity
import android.text.InputType
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.homescreen.*
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import android.view.KeyEvent.KEYCODE_ENTER
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.support.v4.content.ContextCompat.getSystemService
import java.text.SimpleDateFormat
import java.util.*


class HomeScreenFrag : Fragment() {

    data class SetPlayers(var selected: Int,
                          val dieOne: RelativeLayout,
                          val dieTwo: RelativeLayout,
                          val dieThree: RelativeLayout,
                          val dieFour: RelativeLayout,
                          val dieFive: RelativeLayout,
                          val dieSix: RelativeLayout)

    data class SetLifetotal(var lifetotal: Int,
                            val buttonTwenty: Button,
                            val buttonThirty: Button,
                            val buttonForty: Button,
                            val editTextCustom: EditText)

    private val NUM_PLAYERS = "NUM_PLAYERS"
    private val oneSelected = 1
    private val twoSelected = 2
    private val threeSelected = 3
    private val fourSelected = 4
    private val fiveSelected = 5
    private val sixSelected = 6

    private val LIFETOTAL = "LIFETOTAL"
    private val twentyHP = 20
    private val thirtyHP = 30
    private val fortyHP = 40

    private val defaultTime = 5

    private val TIMER_LENGTH = "TIMER_LENGTH"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.homescreen, container, false)

        // Initiate data classes
        val players = SetPlayers(fourSelected,
            view.findViewById(R.id.die_one),
            view.findViewById(R.id.die_two),
            view.findViewById(R.id.die_three),
            view.findViewById(R.id.die_four),
            view.findViewById(R.id.die_five),
            view.findViewById(R.id.die_six))

        val health = SetLifetotal(fortyHP,
            view.findViewById(R.id.twenty),
            view.findViewById(R.id.thirty),
            view.findViewById(R.id.forty),
            view.findViewById(R.id.custom))

        // Set Button Listeners
        val onePlayer: Button = view.findViewById(R.id.button_one)
        onePlayer.setOnClickListener {
            players.selected = oneSelected
            dieClick(players)
        }

        val twoPlayer: Button = view.findViewById(R.id.button_two)
        twoPlayer.setOnClickListener {
            players.selected = twoSelected
            dieClick(players)
        }

        val threePlayer: Button = view.findViewById(R.id.button_three)
        threePlayer.setOnClickListener {
            players.selected = threeSelected
            dieClick(players)
        }

        val fourPlayer: Button = view.findViewById(R.id.button_four)
        fourPlayer.setOnClickListener {
            players.selected = fourSelected
            dieClick(players)
        }

        val fivePlayer: Button = view.findViewById(R.id.button_five)
        fivePlayer.setOnClickListener {
            players.selected = fiveSelected
            dieClick(players)
        }

        val sixPlayer: Button = view.findViewById(R.id.button_six)
        sixPlayer.setOnClickListener {
            players.selected = sixSelected
            dieClick(players)
        }

        health.buttonTwenty.setOnClickListener {
            health.lifetotal = twentyHP
            healthClick(health)
        }

        health.buttonThirty.setOnClickListener {
            health.lifetotal = thirtyHP
            healthClick(health)
        }

        health.buttonForty.setOnClickListener {
            health.lifetotal = fortyHP
            healthClick(health)
        }

        // Check if keypress is done key press
        health.editTextCustom.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                event?.action == KeyEvent.ACTION_DOWN && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                health.lifetotal = health.editTextCustom.text.toString().toInt()

                val imm = v.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v.windowToken, 0)

                healthClick(health)
                true
            } else {
                false
            }
        }

        val timerLength = view.findViewById<EditText>(R.id.timer_length)

        val startGame = view.findViewById<Button>(R.id.start_game)
        startGame.setOnClickListener {

            var time = defaultTime
            if (timerLength.text.toString() != "")
                time = timerLength.text.toString().toInt()

            startPlaying(players.selected, health.lifetotal, time)
        }

        return view
    }

    private fun dieClick(players: HomeScreenFrag.SetPlayers) {
        // function that changes background and sets values when a rounded_corners_white button is pressed
        players.dieOne.setBackgroundResource(R.drawable.rounded_corners_white)
        players.dieTwo.setBackgroundResource(R.drawable.rounded_corners_white)
        players.dieThree.setBackgroundResource(R.drawable.rounded_corners_white)
        players.dieFour.setBackgroundResource(R.drawable.rounded_corners_white)
        players.dieFive.setBackgroundResource(R.drawable.rounded_corners_white)
        players.dieSix.setBackgroundResource(R.drawable.rounded_corners_white)

        when(players.selected) {
            1 -> players.dieOne.setBackgroundResource(R.drawable.selected)
            2 -> players.dieTwo.setBackgroundResource(R.drawable.selected)
            3 -> players.dieThree.setBackgroundResource(R.drawable.selected)
            4 -> players.dieFour.setBackgroundResource(R.drawable.selected)
            5 -> players.dieFive.setBackgroundResource(R.drawable.selected)
            6 -> players.dieSix.setBackgroundResource(R.drawable.selected)
            else -> Log.d("HomeFrag: dieClick", "No rounded_corners_white selected")
        }
    }

    private fun healthClick(health: HomeScreenFrag.SetLifetotal) {
        // function that changes background and sets value when lifetotal button is pressed
        health.buttonTwenty.setBackgroundResource(R.drawable.rounded_corners_white)
        health.buttonThirty.setBackgroundResource(R.drawable.rounded_corners_white)
        health.buttonForty.setBackgroundResource(R.drawable.rounded_corners_white)
        health.editTextCustom.setBackgroundResource(R.drawable.rounded_corners_white)

        when(health.lifetotal) {
            20 -> health.buttonTwenty.setBackgroundResource(R.drawable.selected)
            30 -> health.buttonThirty.setBackgroundResource(R.drawable.selected)
            40 -> health.buttonForty.setBackgroundResource(R.drawable.selected)
            else -> health.editTextCustom.setBackgroundResource(R.drawable.selected)
        }
    }

    private fun startPlaying(numPlayers: Int, lifetotal: Int, time: Int) {
        val intent = Intent(activity, PlayingActivity::class.java)
        intent.putExtra(NUM_PLAYERS, numPlayers)
        intent.putExtra(LIFETOTAL, lifetotal)
        intent.putExtra(TIMER_LENGTH, time)
        startActivity(intent)
    }
}
