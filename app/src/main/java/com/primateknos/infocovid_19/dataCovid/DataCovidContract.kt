package com.primateknos.infocovid_19.dataCovid

import com.primateknos.infocovid_19.model.DataCovidData

interface DataCovidContract {
    fun onLoadSuccess(x: List<DataCovidData>)
    fun onLoadFailed()
}