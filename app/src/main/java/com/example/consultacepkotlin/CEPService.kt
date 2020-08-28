package com.example.consultacepkotlin

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.consultacepkotlin.CEP as CEP

class CEPService(mainActivity: MainActivity) {
    private var context: Context? = null
    private var service: EndPoint? = null

    fun CEPService(context: String, param: SimpleCallback<CEP?>) {
        this.context = context
        initialize()
    }

    private fun initialize() {
        val g = GsonBuilder().registerTypeAdapter(CEP::class.java, CEPDeserializer()).create()
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient = OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(service?.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(g))
            .build()
        service = retrofit.create<EndPoint>(EndPoint::class.java)
        val service: EndPoint =
            retrofit.create<EndPoint>(EndPoint::class.java)
    }

}