package com.example.walap.di.module

import com.example.walap.ui.screen.home.HomeFragment
import com.example.walap.ui.screen.start.StartFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}