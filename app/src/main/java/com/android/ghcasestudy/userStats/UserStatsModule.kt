
package com.android.ghcasestudy.userStats

import androidx.lifecycle.ViewModel
import com.android.ghcasestudy.data.source.network.repository.*
import com.android.ghcasestudy.domain.UserDetailsUsecase
import com.android.ghcasestudy.domain.UserDetailsUsecaseImpl
import com.android.ghcasestudy.factory.ViewModelBuilder
import com.android.ghcasestudy.factory.ViewModelKey
import com.android.ghcasestudy.userStats.view.UserStatsActivity
import com.android.ghcasestudy.userStats.vm.UserStatsViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserStatsModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userActivity(): UserStatsActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserStatsViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserStatsViewModel): ViewModel

    @Binds
    abstract fun bindUserDetailsUsecase(userDetailsUsecaseImpl:
                                              UserDetailsUsecaseImpl) : UserDetailsUsecase

    @Binds
    abstract fun bindUserFollowingRepository(
        userFollowingRepositoryImpl: UserFollowingRepositoryImpl) : UserFollowingRepository

    @Binds
    abstract fun bindUserFollowersRepository(
        userFollowersRepositoryImpl: UserFollowersRepositoryImpl) : UserFollowersRepository

}
