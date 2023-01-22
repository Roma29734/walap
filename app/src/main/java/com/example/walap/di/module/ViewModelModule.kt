package com.example.walap.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walap.ui.screen.categories.CategoriesViewModel
import com.example.walap.ui.screen.nav.NavViewModel
import com.example.walap.ui.screen.oneCategories.OneCategoriesViewModel
import com.example.walap.ui.screen.random.RandomViewModel
import com.example.walap.ui.screen.search.SearchViewModel
import com.example.walap.ui.screen.start.StartViewModel
import com.example.walap.ui.screen.topPhoto.TopPhotoViewModel
import com.example.walap.ui.viewModel.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TopPhotoViewModel::class)
    abstract fun bindTopPhotoViewModel(imagesListViewModel: TopPhotoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RandomViewModel::class)
    abstract fun bindRandomViewModel(imagesListViewModel: RandomViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(imagesListViewModel: SearchViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NavViewModel::class)
    abstract fun bindNavViewModel(imagesListViewModel: NavViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    abstract fun bindStartViewModel(imagesListViewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoriesViewModel::class)
    abstract fun bindCategoriesViewModel(imagesListViewModel: CategoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OneCategoriesViewModel::class)
    abstract fun bindOneCategoriesViewModel(imagesListViewModel: OneCategoriesViewModel): ViewModel
}

@MustBeDocumented
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)