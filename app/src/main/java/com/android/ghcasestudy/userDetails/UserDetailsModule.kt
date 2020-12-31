package com.android.ghcasestudy.userDetails

import androidx.lifecycle.ViewModel
import com.android.ghcasestudy.userDetails.repository.UserDetailRepository
import com.android.ghcasestudy.userDetails.repository.UserDetailRepositoryImpl
import com.android.ghcasestudy.userDetails.usecases.UserDetailUsecase
import com.android.ghcasestudy.userDetails.usecases.UserDetailUsecaseImpl
import com.android.ghcasestudy.factory.ViewModelBuilder
import com.android.ghcasestudy.factory.ViewModelKey
import com.android.ghcasestudy.userDetails.view.UserDetailsActivity
import com.android.ghcasestudy.userDetails.vm.UserDetailsViewModel
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

    @Binds
    abstract fun bindUserUsecase(userUsecaseImpl : UserDetailUsecaseImpl) : UserDetailUsecase

    @Binds
    abstract fun bindUserDetailsRepository(userDetailRepositoryImpl:
                                           UserDetailRepositoryImpl
    ) : UserDetailRepository
}