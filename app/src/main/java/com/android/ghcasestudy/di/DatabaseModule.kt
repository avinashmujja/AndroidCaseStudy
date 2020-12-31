package com.android.ghcasestudy.di

import android.content.Context
import androidx.room.Room
import com.android.ghcasestudy.data.source.local.DAO.UserDAO
import com.android.ghcasestudy.data.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideDb(context : Context): GithubDatabase {
        return Room
            .databaseBuilder(context, GithubDatabase::class.java, "githubapp.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    @JvmStatic
    fun provideUserDao(db: GithubDatabase): UserDAO {
        return db.usersDAO
    }


}