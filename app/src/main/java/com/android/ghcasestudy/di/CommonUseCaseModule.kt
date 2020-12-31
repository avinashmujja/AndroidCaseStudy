package com.android.ghcasestudy.di

import com.android.ghcasestudy.common.usecases.ModifyItemsUseCase
import com.android.ghcasestudy.common.usecases.ModifyitemUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class CommonUseCaseModule {

    @Binds
    abstract fun bindModifyItemsUseCase(modifyitemUseCaseImpl:
                                        ModifyitemUseCaseImpl
    ) : ModifyItemsUseCase
}