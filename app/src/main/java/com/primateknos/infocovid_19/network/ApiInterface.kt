package com.primateknos.infocovid_19.network

import com.primateknos.infocovid_19.model.DataCovidData
import com.primateknos.infocovid_19.model.DataCovidResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("timeline/ID")
    fun getTimeline() : Call<List<DataCovidData>>
}