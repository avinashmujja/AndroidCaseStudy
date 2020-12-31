
package com.android.ghcasestudy.userList

import androidx.lifecycle.ViewModel
import com.android.ghcasestudy.data.source.local.repository.UpdateUserListLocalRepository
import com.android.ghcasestudy.data.source.local.repository.UpdateUserListLocalRepositoryImpl
import com.android.ghcasestudy.data.source.local.repository.UserListLocalRepository
import com.android.ghcasestudy.data.source.local.repository.UserListLocalRepositoryImpl
import com.android.ghcasestudy.data.source.network.repository.UserListRepository
import com.android.ghcasestudy.data.source.network.repository.UserListRepositoryImpl
import com.android.ghcasestudy.domain.SaveUserListUsecase
import com.android.ghcasestudy.domain.SaveUserListUsecaseImpl
import com.android.ghcasestudy.domain.UsersListUsecase
import com.android.ghcasestudy.domain.UsersListUsecaseImpl
import com.android.ghcasestudy.factory.ViewModelBuilder
import com.android.ghcasestudy.factory.ViewModelKey
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
                                     UsersListUsecaseImpl) : UsersListUsecase

    @Binds
    abstract fun bindUserListLocalRepository(userListLocalRepositoryImpl:
                                             UserListLocalRepositoryImpl) : UserListLocalRepository

    @Binds
    abstract fun bindUserListRepository(userListRepositoryImpl:
                                        UserListRepositoryImpl) : UserListRepository

    @Binds
    abstract fun bindupdateUserListLocalRepository(
        updateUserListLocalRepositoryImpl: UpdateUserListLocalRepositoryImpl) :
            UpdateUserListLocalRepository

    @Binds
    abstract fun bindSaveUserListUsecase(
        saveUserListUsecaseImpl: SaveUserListUsecaseImpl) : SaveUserListUsecase
}
