package com.android.ghcasestudy.di

import com.android.ghcasestudy.userDetails.UserDetailsModule
import com.android.ghcasestudy.userStats.UserStatsModule
import com.android.ghcasestudy.userList.UserListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,
    AndroidSupportInjectionModule::class,
    UserListModule::class, UserStatsModule::class,
    UserDetailsModule::class,AppModule::class,
    DatabaseModule::class, CommonUseCaseModule::class ])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: DaggerApplication): ApplicationComponent
    }
    override fun inject(application: DaggerApplication)
}