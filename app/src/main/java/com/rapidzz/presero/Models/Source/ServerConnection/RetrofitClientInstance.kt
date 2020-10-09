package com.rapidzz.presero.Models.Source.ServerConnection

import android.content.Context

import com.rapidzz.presero.Utils.GeneralUtils.SessionManager
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class RetrofitClientInstance(ctx: Context) {
    private var retrofit: Retrofit? = null
    private val httpClient = OkHttpClient.Builder()
    var context: Context

    val BASE_URL = "https://www.mashghol.com/preseroapis/"

    init {
        context = ctx
        if (retrofit == null) {
            initRetrofit()
        }
    }

    fun initRetrofit() {
        var retrofitBuilder = retrofit2.Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        httpClient.callTimeout(120, TimeUnit.SECONDS).connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)

        val authToken = SessionManager(context).getAuthToken()
        if (authToken!!.isNotEmpty()) {
            val interceptor = AuthenticationInterceptor(
                authToken!!,
                context
            )
            httpClient.addInterceptor(interceptor)
            if (androidx.multidex.BuildConfig.DEBUG) {
                val loggingIntercepter = getLoggingInterceptor()
                loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
                httpClient.addInterceptor(loggingIntercepter)
            }

            retrofitBuilder.client(httpClient.build())
        } else {
            val loggingIntercepter = getLoggingInterceptor()
            loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
            httpClient.addInterceptor(loggingIntercepter)

            retrofitBuilder.client(httpClient.build())
        }
        retrofitBuilder.client(httpClient.build())



        retrofit = retrofitBuilder.build()
    }

    fun getService(): ApiService {
        return retrofit!!.create<ApiService>(ApiService::class.java!!)
    }


    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingIntercepter = HttpLoggingInterceptor()
        return loggingIntercepter
    }


    fun getRetrofit(): Retrofit? {
        return retrofit
    }


    companion object {
        var singleInstance: RetrofitClientInstance? = null

        fun getInstance(context: Context): RetrofitClientInstance? {
            if (singleInstance == null)
                singleInstance =
                    RetrofitClientInstance(
                        context
                    )

            return singleInstance
        }
    }


    class AuthenticationInterceptor internal constructor(
        private val authToken: String,
        private val context: Context
    ) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()

            val builder = original.newBuilder().header("Authorization", "Bearer " + authToken)
                .header("Accept", "application/json")

            val request = builder.build()

            //return chain.proceed(request)
            val response = chain.proceed(request)

            return response
        }
    }


}