package com.example.walap.di.module

import com.example.walap.ui.screen.categories.CategoriesFragment
import com.example.walap.ui.screen.detail.DetailFragment
import com.example.walap.ui.screen.home.HomeFragment
import com.example.walap.ui.screen.nav.NavFragment
import com.example.walap.ui.screen.oneCategories.OneCategoriesFragment
import com.example.walap.ui.screen.random.RandomFragment
import com.example.walap.ui.screen.search.SearchFragment
import com.example.walap.ui.screen.search.SearchViewModel
import com.example.walap.ui.screen.start.StartFragment
import com.example.walap.ui.screen.topPhoto.TopPhotoFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeStartFragment(): StartFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeTopPhotoFragment(): TopPhotoFragment

    @ContributesAndroidInjector
    abstract fun contributeRandomFragment(): RandomFragment

    @ContributesAndroidInjector
    abstract fun contributeCategoriesFragment(): CategoriesFragment

    @ContributesAndroidInjector
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun contributeNavFragment(): NavFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment

    @ContributesAndroidInjector
    abstract fun contributeOneCategoriesFragment(): OneCategoriesFragment

}