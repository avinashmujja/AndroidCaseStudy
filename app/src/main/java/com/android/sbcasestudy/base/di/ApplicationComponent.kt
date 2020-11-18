package com.android.sbcasestudy.base.di

import com.android.sbcasestudy.user.UserModule
import com.android.sbcasestudy.userDetails.UserDetailsModule
import com.android.sbcasestudy.usersList.UserListModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,
    AndroidSupportInjectionModule::class,
    UserListModule::class, UserDetailsModule::class, UserModule::class,AppModule::class, DatabaseModule::class ])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: DaggerApplication): ApplicationComponent
    }
    override fun inject(application: DaggerApplication)
}