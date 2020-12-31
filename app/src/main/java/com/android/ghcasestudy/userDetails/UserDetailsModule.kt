package com.android.ghcasestudy.userDetails

/*import com.android.ghcasestudy.factory.ViewModelKey*/
import com.android.ghcasestudy.userDetails.repository.UserDetailRepository
import com.android.ghcasestudy.userDetails.repository.UserDetailRepositoryImpl
import com.android.ghcasestudy.userDetails.usecases.UserDetailUsecase
import com.android.ghcasestudy.userDetails.usecases.UserDetailUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class UserDetailsModule {

    /*@Binds
    @IntoMap
    @ViewModelKey(UserDetailsViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserDetailsViewModel): ViewModel*/

    @Binds
    abstract fun bindUserUsecase(
        userUsecaseImpl:
        UserDetailUsecaseImpl
    ): UserDetailUsecase

    @Binds
    abstract fun bindUserDetailsRepository(
        userDetailRepositoryImpl:
        UserDetailRepositoryImpl
    ): UserDetailRepository
}