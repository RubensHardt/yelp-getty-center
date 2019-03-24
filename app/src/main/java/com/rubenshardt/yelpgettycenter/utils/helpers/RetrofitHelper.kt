package com.rubenshardt.yelpgettycenter.utils.helpers

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rubenshardt.yelpgettycenter.BuildConfig
import com.rubenshardt.yelpgettycenter.utils.constants.ApiConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private var retrofit: Retrofit? = null
    private var useAuth = true

    fun getRetrofit(useAuth: Boolean = true): Retrofit {
        val retrofit = this.retrofit
        return if (retrofit != null && RetrofitHelper.useAuth == useAuth) {
            retrofit
        } else {
            RetrofitHelper.useAuth = useAuth
            rebuildRetrofit()
        }
    }

    private fun rebuildRetrofit(): Retrofit {
        val client = buildClient()
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            .client(client)
            .build()
    }

    private fun buildClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        if (useAuth) {
            addTokenInterceptor(builder)
        }
        addLoggingInterceptor(builder)
        return builder.build()
    }

    private fun addLoggingInterceptor(builder: OkHttpClient.Builder) {
        if (BuildConfig.DEBUG) {
            val httpLoggingInterceptor = HttpLoggingInterceptor { message -> Log.d("HttpLoggingInterceptor", message) }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            builder.addNetworkInterceptor(httpLoggingInterceptor)
        }
    }

    private fun addTokenInterceptor(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        val tokenInterceptor = Interceptor { chain ->
            var newRequest = chain.request()
            val accessToken = "Bearer ${ApiConstants.APP_KEY}"
            newRequest = newRequest.newBuilder().addHeader("Authorization", accessToken).build()
            chain.proceed(newRequest)
        }
        builder.addNetworkInterceptor(tokenInterceptor)
        return builder
    }

    private fun buildGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
}