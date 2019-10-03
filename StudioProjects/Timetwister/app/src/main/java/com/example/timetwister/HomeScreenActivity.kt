package com.example.timetwister

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

class HomeScreenActivity : AppCompatActivity() {

    private val PLAYING = "PLAYING"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragment = HomeScreenFrag()
        fragmentTransaction.add(R.id.main_activity, fragment)
        fragmentTransaction.commit()
    }
}
