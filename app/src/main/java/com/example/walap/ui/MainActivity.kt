package com.example.walap.ui

import android.os.Bundle
import com.example.walap.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Walap)
        setContentView(R.layout.activity_main)
    }
}