package com.primateknos.infocovid_19.dataCovid

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.primateknos.infocovid_19.R
import com.primateknos.infocovid_19.model.DataCovidData
import kotlinx.android.synthetic.main.activity_data_covid.*
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DataCovidActivity : AppCompatActivity(),DataCovidContract {
    lateinit var presenter : DataCovidPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_covid)
        presenter= DataCovidPresenter(this)
        presenter.getTimeline()
        Log.d("DataCovidian","Main Activity")
    }

    @SuppressLint("SimpleDateFormat")
    override fun onLoadSuccess(x: List<DataCovidData>) {
        val decim = DecimalFormat("#,###.##")

        tx_cases.text=decim.format(x[0].cases)
        tx_recovered.text=decim.format(x[0].recovered)
        tx_deaths.text=decim.format(x[0].deaths)
        val formatDate = SimpleDateFormat("E, dd/MM/yy")
        val date = formatDate.format(toGMTFormat(x[0].last_update)!!)
        tx_lastUpdated.text=date

        val chart = findViewById<LineChart>(R.id.chart)


        val cases= ArrayList<Entry>()
        //        for (i in 0..30) {
//            cases.add(Entry(30-i.toFloat(),x[i].cases.toFloat()))
//        }
        for ((i, data) in x.reversed().withIndex()) {
            cases.add(Entry(i.toFloat(),data.cases.toFloat()))

        }
        val dataCase = LineDataSet(cases, "Terkonfirmasi")
        dataCase.color = Color.parseColor("#2c347c")
        dataCase.setCircleColor(Color.parseColor("#2c347c"))
//        dataCase.setDrawCircleHole(false)



        val caseColor  = IntArray(dataCase.entryCount) { Color.parseColor("#2c347c") }
//        dataCase.colors = caseColor.toMutableList()



        val recovered= ArrayList<Entry>()
        for ((i, data) in x.reversed().withIndex()) {
            recovered.add(Entry(i.toFloat(),data.recovered.toFloat()))
        }
        val dataRecovered = LineDataSet(recovered, "Sembuh")
        dataRecovered.color = Color.parseColor("#3c928c")
        dataRecovered.setCircleColor(Color.parseColor("#3c928c"))
        dataRecovered.disableDashedLine()
        dataRecovered.setCircleColors(Color.parseColor("#3c928c"))
//        dataRecovered.setDrawCircleHole(false)
        dataRecovered.setColors(Color.parseColor("#3c928c"))
//        dataRecovered.circleRadius= 0F
//        dataRecovered.disableDashedHighlightLine()
//        dataRecovered.setColors(
//            intArrayOf(
//                Color.parseColor("#3c928c"),
//                Color.parseColor("#3c928c"),
//                Color.parseColor("#3c928c"),
//                Color.parseColor("#3c928c")
//            ), this
//        )


        val colorRecovered  = IntArray(dataRecovered.entryCount) { Color.parseColor("#3c928c") }
//        dataCase.colors = colorRecovered.toMutableList()

        val lineRecovered:LineData = LineData(dataRecovered)
        val deaths= ArrayList<Entry>()
        for ((i, data) in x.reversed().withIndex()) {
            deaths.add(Entry(i.toFloat(),data.deaths.toFloat()))
        }
        val dataDeath = LineDataSet(deaths, "Meninggal")
        dataDeath.disableDashedLine()
        dataDeath.color = Color.parseColor("#ec6f58")
        dataDeath.setCircleColor(Color.parseColor("#ec6f58"))
        dataDeath.disableDashedLine()
        dataDeath.setCircleColors(Color.parseColor("#ec6f58"))
//        dataDeath.setDrawCircleHole(false)
        dataDeath.setColors(Color.parseColor("#ec6f58"))
        dataDeath.disableDashedHighlightLine()

        val colorDeath  = IntArray(dataRecovered.entryCount) { Color.parseColor("#ec6f58") }
//        dataCase.colors = colorDeath.toMutableList()
        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(dataCase)
        dataSets.add(dataRecovered)
        dataSets.add(dataDeath)

        val lineData = LineData(dataCase)
        lineData.addDataSet(dataRecovered)
        lineData.addDataSet(dataDeath)


        chart.data = lineData
//        chart.x

        val quarters =ArrayList<String>()
        for ((i, data) in x.reversed().withIndex()) {
            val formatDate2 = SimpleDateFormat("dd/MM/yy")
            val date2 = formatDate2.format(toGMTFormat(data.last_update)!!)
            quarters.add(date2)
        }
        val formatter: ValueFormatter =
            object : ValueFormatter() {
                override fun getAxisLabel(value: Float, axis: AxisBase): String {
                    return quarters[value.toInt()]
                }
            }
        val xAxis: XAxis = chart.xAxis
        xAxis.granularity = 5f // minimum axis-step (interval) is 1

        xAxis.valueFormatter = formatter

        chart.invalidate() // refresh

    }

    override fun onLoadFailed() {

    }
    @SuppressLint("SimpleDateFormat")
    private fun toGMTFormat(datime:String?): Date? {
        val datimed= datime!!.replace("T"," ")
        val timezoneID = TimeZone.getDefault().id
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone(timezoneID)
        return formatter.parse(datimed)
    }
}