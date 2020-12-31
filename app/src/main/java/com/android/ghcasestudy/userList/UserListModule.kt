
package com.android.ghcasestudy.userList

import androidx.lifecycle.ViewModel
import com.android.ghcasestudy.userList.repository.UserListRepositoryImpl
import com.android.ghcasestudy.userList.usecases.UsersListUsecase
import com.android.ghcasestudy.userList.usecases.UsersListUsecaseImpl
import com.android.ghcasestudy.factory.ViewModelBuilder
import com.android.ghcasestudy.factory.ViewModelKey
import com.android.ghcasestudy.userList.repository.UserListRepository
import com.android.ghcasestudy.userList.view.UserListActivity
import com.android.ghcasestudy.userList.vm.UserListViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class UserListModule {

    @ContributesAndroidInjector(modules = [ViewModelBuilder::class])
    internal abstract fun userActivity(): UserListActivity

    @Binds
    @IntoMap
    @ViewModelKey(UserListViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserListViewModel): ViewModel

    @Binds
    abstract fun bindUserListUsecase(usersListUsecaseImpl:
                                     UsersListUsecaseImpl
    ) : UsersListUsecase

    @Binds
    abstract fun bindUserListRepository(userListRepositoryImpl:
                                        UserListRepositoryImpl
    ) : UserListRepository
}
