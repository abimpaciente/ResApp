package com.example.resball.di

import com.example.resball.rest.Repository
import com.example.resball.rest.RepositoryImpl
import com.example.resball.rest.RequestApi
import com.example.resball.rest.RequestInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().build()

    @Provides
    fun providesRequestInterceptor(): RequestInterceptor =
        RequestInterceptor()

    @Provides
    fun provideHttpLoggingInterceptor(
    ): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        myRequestInterceptor: RequestInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(myRequestInterceptor)
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideExcuserApi(okHttpClient: OkHttpClient, moshi: Moshi): RequestApi =
        Retrofit.Builder()
            .baseUrl(RequestApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(RequestApi::class.java)

    @Provides
    fun providesDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesRepository(apiService: RequestApi): Repository =
        RepositoryImpl(apiService)
}