package com.mj.deliveryapp.di

import com.google.firebase.ktx.BuildConfig
import com.mj.deliveryapp.data.network.MapApiService
import com.mj.deliveryapp.data.url.Url
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideMapApiService(retrofit: Retrofit) : MapApiService {
    return retrofit.create(MapApiService::class.java)
}

fun provideMapRetrofit(
    okHttpClient: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Url.TMAP_URL)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

fun provideGsonConvertFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun buildOkHttpClient(): OkHttpClient {
    val interceptor = HttpLoggingInterceptor()
    if(BuildConfig.DEBUG) {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    } else {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()
}

