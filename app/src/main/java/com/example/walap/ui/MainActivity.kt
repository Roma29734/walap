package com.example.walap.ui

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.Navigation
import com.example.walap.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}