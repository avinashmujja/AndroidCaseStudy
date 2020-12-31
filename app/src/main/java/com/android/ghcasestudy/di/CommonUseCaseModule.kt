package com.android.ghcasestudy.di

import com.android.ghcasestudy.common.usecases.ModifyItemsUseCase
import com.android.ghcasestudy.common.usecases.ModifyitemUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CommonUseCaseModule {

    @Binds
    abstract fun bindModifyItemsUseCase(modifyitemUseCaseImpl:
                                        ModifyitemUseCaseImpl
    ) : ModifyItemsUseCase
}