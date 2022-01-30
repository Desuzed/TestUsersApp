package com.desuzed.testusersapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.desuzed.testusersapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val testTextView = findViewById<TextView>(R.id.testTextView)
//        val repo = App.instance.getRepo()
//        lifecycleScope.launchWhenCreated {
//            val result = repo.getUsersFromApi()
//            Log.i("TAG", "onCreate: ${result.first?.get(0)}")
//            testTextView.text = "${result.first?.get(0)?.firstName}, ${result.first?.get(0)?.lastName}"
//        }


    }
}