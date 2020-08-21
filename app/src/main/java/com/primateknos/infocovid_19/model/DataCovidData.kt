package com.primateknos.infocovid_19.model

import com.google.gson.annotations.SerializedName

data class DataCovidData (
    @SerializedName("country") val country : String,
    @SerializedName("last_update") val last_update : String,
    @SerializedName("cases") val cases : Int,
    @SerializedName("deaths") val deaths : Int,
    @SerializedName("recovered") val recovered : Int

)