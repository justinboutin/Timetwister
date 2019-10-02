package com.example.timetwister

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentTransaction
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout

class HomeFrag : Fragment() {

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
                            val buttonCustom: Button)

    private val oneSelected = 1
    private val twoSelected = 2
    private val threeSelected = 3
    private val fourSelected = 4
    private val fiveSelected = 5
    private val sixSelected = 6

    private val twentyHP = 20
    private val thirtyHP = 30
    private val fortyHP = 40

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
        val onePlayer = view.findViewById<Button>(R.id.button_one)
        onePlayer.setOnClickListener {
            players.selected = oneSelected
            dieClick(players)
        }

        val twoPlayer = view.findViewById<Button>(R.id.button_two)
        twoPlayer.setOnClickListener {
            players.selected = twoSelected
            dieClick(players)
        }

        val threePlayer = view.findViewById<Button>(R.id.button_three)
        threePlayer.setOnClickListener {
            players.selected = threeSelected
            dieClick(players)
        }

        val fourPlayer = view.findViewById<Button>(R.id.button_four)
        fourPlayer.setOnClickListener {
            players.selected = fourSelected
            dieClick(players)
        }

        val fivePlayer = view.findViewById<Button>(R.id.button_five)
        fivePlayer.setOnClickListener {
            players.selected = fiveSelected
            dieClick(players)
        }

        val sixPlayer = view.findViewById<Button>(R.id.button_six)
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

        health.buttonCustom.setOnClickListener {
            health.lifetotal = 100
            healthClick(health)
        }


        val startGame = view.findViewById<Button>(R.id.start_game)
        startGame.setOnClickListener {

            when(players.selected) {
                // 1 -> //launch 1 player
                2 -> replaceFragment(TwoPlayer())
                3 -> replaceFragment(ThreePlayer())
                4 -> replaceFragment(FourPlayer())
                5 -> replaceFragment(FivePlayer())
                6 -> replaceFragment(SixPlayer())
            }
        }

        return view
    }

    private fun dieClick(players: SetPlayers) {
        players.dieOne.setBackgroundResource(R.drawable.die)
        players.dieTwo.setBackgroundResource(R.drawable.die)
        players.dieThree.setBackgroundResource(R.drawable.die)
        players.dieFour.setBackgroundResource(R.drawable.die)
        players.dieFive.setBackgroundResource(R.drawable.die)
        players.dieSix.setBackgroundResource(R.drawable.die)

        when(players.selected) {
            1 -> players.dieOne.setBackgroundResource(R.drawable.selected)
            2 -> players.dieTwo.setBackgroundResource(R.drawable.selected)
            3 -> players.dieThree.setBackgroundResource(R.drawable.selected)
            4 -> players.dieFour.setBackgroundResource(R.drawable.selected)
            5 -> players.dieFive.setBackgroundResource(R.drawable.selected)
            6 -> players.dieSix.setBackgroundResource(R.drawable.selected)
            else -> Log.d("HomeFrag: dieClick", "No die selected")
        }
    }

    private fun healthClick(health: SetLifetotal) {
        health.buttonTwenty.setBackgroundResource(R.drawable.die)
        health.buttonThirty.setBackgroundResource(R.drawable.die)
        health.buttonForty.setBackgroundResource(R.drawable.die)
        health.buttonCustom.setBackgroundResource(R.drawable.die)

        when(health.lifetotal) {
            20 -> health.buttonTwenty.setBackgroundResource(R.drawable.selected)
            30 -> health.buttonThirty.setBackgroundResource(R.drawable.selected)
            40 -> health.buttonForty.setBackgroundResource(R.drawable.selected)
            else -> health.buttonCustom.setBackgroundResource(R.drawable.selected)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = activity!!.supportFragmentManager.beginTransaction()
        transaction.replace(this.id, fragment)
        transaction.commit()
    }
}
