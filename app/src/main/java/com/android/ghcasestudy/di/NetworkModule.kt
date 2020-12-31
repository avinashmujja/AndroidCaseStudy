package com.android.ghcasestudy.di

import com.android.ghcasestudy.data.GithubAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule{

    private const val API_TIMEOUT : Long = 30
    private const val baseURL = "https://api.github.com/"

    @Singleton
    @Provides
    @JvmStatic
    fun retrofitInstance(): GithubAPI {

        val builder = OkHttpClient.Builder()
        builder.readTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        builder.writeTimeout(API_TIMEOUT, TimeUnit.SECONDS)
        builder.connectTimeout(API_TIMEOUT, TimeUnit.SECONDS)

        val requestInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .build()
            chain.proceed(newRequest)
        }
        builder.addInterceptor(requestInterceptor)

        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(GithubAPI::class.java)
    }
}
