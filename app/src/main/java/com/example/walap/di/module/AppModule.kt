package com.example.walap.di.module

import dagger.Module

@Module(
    includes = [
        ViewModelModule::class,
        RemoteModule::class,
    ]
)
class AppModule