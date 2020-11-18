package com.android.sbcasestudy.user

import androidx.lifecycle.ViewModel
import com.android.sbcasestudy.base.factory.ViewModelBuilder
import com.android.sbcasestudy.base.factory.ViewModelKey
import com.android.sbcasestudy.user.view.UserActivity
import com.android.sbcasestudy.user.vm.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserModule {
    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userActivity(): UserActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserViewModel): ViewModel
}