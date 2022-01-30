package com.desuzed.testusersapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.lifecycleScope

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val testTextView = findViewById<TextView>(R.id.testTextView)
        val repo = App.instance.getRepo()
        lifecycleScope.launchWhenCreated {
            val user = repo.getCachedUsers()[0]
            testTextView.text = "${user.firstName}, ${user.lastName}"
        }


    }
}