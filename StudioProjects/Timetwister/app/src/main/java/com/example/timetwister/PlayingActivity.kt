package com.example.timetwister

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View

class PlayingActivity : AppCompatActivity() {

    private val NUM_PLAYERS = "NUM_PLAYERS"
    private val LIFETOTAL = "LIFETOTAL"

    private val defaultPlayers = 4
    private val defaultLifetotal = 40

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        Log.d("here", "here")

        // Retrieve Values

        val bundle=intent.extras
        var numPlayers: Int = defaultPlayers
        var lifetotal: Int = defaultLifetotal
        if(bundle!=null)
        {
            numPlayers = bundle.getInt(NUM_PLAYERS)
            lifetotal = bundle.getInt(LIFETOTAL)
        }



        Log.d("here", numPlayers.toString())
        Log.d("here", lifetotal.toString())

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

        fragmentTransaction.add(R.id.playing_activity, fragment)
        fragmentTransaction.commit()

    }
}
