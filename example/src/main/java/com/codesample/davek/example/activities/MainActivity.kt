package com.codesample.davek.example.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.codesample.davek.example.R
import com.codesample.davek.example.fragments.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.main_content, MainFragment())
        transaction.commit()
    }
}
