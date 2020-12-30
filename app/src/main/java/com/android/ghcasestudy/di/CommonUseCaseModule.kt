package com.android.ghcasestudy.di

import com.android.ghcasestudy.domain.ModifyItemsUseCase
import com.android.ghcasestudy.domain.ModifyitemUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class CommonUseCaseModule {

    @Binds
    abstract fun bindModifyItemsUseCase(modifyitemUseCaseImpl:
                                            ModifyitemUseCaseImpl) : ModifyItemsUseCase
}