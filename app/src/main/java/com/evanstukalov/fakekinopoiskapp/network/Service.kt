package com.evanstukalov.fakekinopoiskapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface ApiService {
    @GET("films.json")
    suspend fun getFilms(): NetworkFilmsContainer
}

//// Create Moshi instance
//private val moshi = Moshi.Builder()
//        .add(KotlinJsonAdapterFactory())
//        .build()

// Create logging interceptor and set log level
private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

// Create OkHttp client and add interceptor
private val okHttpClient = OkHttpClient.Builder().addInterceptor(logging)



// Create Retrofit instance
object RetrofitInstance {

    val api: ApiService by lazy {
        Retrofit.Builder()
                .baseUrl("https://s3-eu-west-1.amazonaws.com/sequeniatesttask/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(okHttpClient.build())
                .build()
                .create(ApiService::class.java)
    }
}

