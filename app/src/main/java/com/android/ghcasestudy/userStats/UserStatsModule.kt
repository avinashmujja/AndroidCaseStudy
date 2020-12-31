
package com.android.ghcasestudy.userStats

/*import com.android.ghcasestudy.factory.ViewModelKey*/
import com.android.ghcasestudy.userStats.repository.UserFollowersRepository
import com.android.ghcasestudy.userStats.repository.UserFollowersRepositoryImpl
import com.android.ghcasestudy.userStats.repository.UserFollowingRepository
import com.android.ghcasestudy.userStats.repository.UserFollowingRepositoryImpl
import com.android.ghcasestudy.userStats.usecases.UserDetailsUsecase
import com.android.ghcasestudy.userStats.usecases.UserDetailsUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class UserStatsModule {

    /*@Binds
    @IntoMap
    @ViewModelKey(UserStatsViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserStatsViewModel): ViewModel*/

    @Binds
    abstract fun bindUserDetailsUsecase(userDetailsUsecaseImpl:
                                        UserDetailsUsecaseImpl
    ) : UserDetailsUsecase

    @Binds
    abstract fun bindUserFollowingRepository(
        userFollowingRepositoryImpl: UserFollowingRepositoryImpl
    ) : UserFollowingRepository

    @Binds
    abstract fun bindUserFollowersRepository(
        userFollowersRepositoryImpl: UserFollowersRepositoryImpl
    ) : UserFollowersRepository

}
