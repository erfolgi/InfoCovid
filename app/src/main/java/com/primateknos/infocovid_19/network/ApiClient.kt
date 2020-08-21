package com.primateknos.infocovid_19.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import okhttp3.OkHttpClient



class ApiClient{
    companion object {//"https://infoharga.akbarraihanm.com/"

        var okHttpClient = OkHttpClient.Builder()
            .cache(null)
            .build()

        fun getClient() : Retrofit{
            val baseURL = "https://covid19-api.org/api/"
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getClient2() : Retrofit{
            val baseURL = "https://covid19-api.org/api/"
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}