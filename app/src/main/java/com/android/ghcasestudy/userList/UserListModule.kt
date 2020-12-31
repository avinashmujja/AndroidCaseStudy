package com.android.ghcasestudy.userList

/*import com.android.ghcasestudy.factory.ViewModelKey*/
import com.android.ghcasestudy.userList.repository.UserListRepository
import com.android.ghcasestudy.userList.repository.UserListRepositoryImpl
import com.android.ghcasestudy.userList.usecases.UsersListUsecase
import com.android.ghcasestudy.userList.usecases.UsersListUsecaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class UserListModule {

    /*@Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserListViewModel): ViewModel*/

    @Binds
    abstract fun bindUserListUsecase(usersListUsecaseImpl:
                                     UsersListUsecaseImpl
    ) : UsersListUsecase

    @Binds
    abstract fun bindUserListRepository(userListRepositoryImpl:
                                        UserListRepositoryImpl
    ) : UserListRepository
}
