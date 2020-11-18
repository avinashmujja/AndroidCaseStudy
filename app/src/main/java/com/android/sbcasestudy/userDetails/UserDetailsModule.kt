
package com.android.sbcasestudy.userDetails

import androidx.lifecycle.ViewModel
import com.android.sbcasestudy.base.factory.ViewModelBuilder
import com.android.sbcasestudy.base.factory.ViewModelKey
import com.android.sbcasestudy.userDetails.view.UserDetailsActivity
import com.android.sbcasestudy.userDetails.vm.UserDetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserDetailsModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userActivity(): UserDetailsActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserDetailsViewModel): ViewModel

}
