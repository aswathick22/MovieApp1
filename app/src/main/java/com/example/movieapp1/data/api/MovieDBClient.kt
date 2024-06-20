package com.example.movieapp1.data.api

import okhttp3.OkHttpClient
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object MovieDBClient {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "b2ebc78e9f909b2e78017975bf8d9c19"
    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342/2LqaLgk4Z226KkgPJuiOQ58wvrm.jpg"
    private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val movieDBInterface : MovieDBInterface =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create(MovieDBInterface::class.java)
}