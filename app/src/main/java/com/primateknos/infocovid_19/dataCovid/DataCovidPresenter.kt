package com.primateknos.infocovid_19.dataCovid

import android.util.Log
import com.primateknos.infocovid_19.model.DataCovidData
import com.primateknos.infocovid_19.model.DataCovidResponse
import com.primateknos.infocovid_19.network.ApiClient
import com.primateknos.infocovid_19.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataCovidPresenter (private val contract: DataCovidContract) {
    fun getTimeline(){
        val apiInterface : ApiInterface = ApiClient.getClient().create(ApiInterface::class.java)

        val call=apiInterface.getTimeline()
        call.enqueue(object : Callback<List<DataCovidData>> {
            override fun onResponse(call: Call<List<DataCovidData>>, response: Response<List<DataCovidData>>) {
                try {
                    val body: List<DataCovidData>? = response.body()
                    Log.d("DataCovidian",body.toString())
                    if(body!!.isNotEmpty()){
                        Log.d("DataCovidian", body[0].country)
                        contract.onLoadSuccess(body)
                    }else{

                    }
                }catch (e : Exception){
                    Log.d("DataCovidian",e.toString())
                    contract.onLoadFailed()
                }
            }

            override fun onFailure(call: Call<List<DataCovidData>>, t: Throwable) {
                Log.d("DataCovidian",t.toString())
                contract.onLoadFailed()
            }
        })
    }
}