
package com.android.sbcasestudy.usersList

import androidx.lifecycle.ViewModel
import com.android.sbcasestudy.base.factory.ViewModelBuilder
import com.android.sbcasestudy.base.factory.ViewModelKey
import com.android.sbcasestudy.usersList.view.UsersListActivity
import com.android.sbcasestudy.usersList.vm.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserListModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userActivity(): UsersListActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserListViewModel): ViewModel
}
