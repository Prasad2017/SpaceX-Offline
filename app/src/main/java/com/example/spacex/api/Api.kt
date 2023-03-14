package com.example.spacex.api

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Api {

    private const val BASE_URL = "https://api.spacexdata.com/"
    @Provides
    @Singleton
    fun getClient(): ApiInterface {

        val logging = HttpLoggingInterceptor()
        // set your desired log level
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC).level =
            HttpLoggingInterceptor.Level.BODY

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val httpClient = OkHttpClient.Builder()
            .connectTimeout(50, TimeUnit.MINUTES)
            .writeTimeout(50, TimeUnit.MINUTES)
            .readTimeout(50, TimeUnit.MINUTES)
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient)
            .build().create(ApiInterface::class.java)
    }
}