package com.primateknos.infocovid_19.dataCovid

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
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
        val formatDate = SimpleDateFormat("HH:mm dd MMMM yyyy ")
        val date = formatDate.format(toGMTFormat(x[0].last_update)!!)
        tx_lastUpdated.text=date
        createNewCaseGraph(x)


    }

    override fun onLoadFailed() {

    }
    @SuppressLint("SimpleDateFormat")
    private fun toGMTFormat(datime:String?): Date? {
        val datimed= datime!!.replace("T"," ")
        val timezoneID ="Etc/GMT+0"
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        formatter.timeZone = TimeZone.getTimeZone(timezoneID)
        return formatter.parse(datimed)
    }

    @SuppressLint("SimpleDateFormat")
    private fun createSumCaseGraph(x: List<DataCovidData>){
        val chart = findViewById<LineChart>(R.id.chart)

        val cases= ArrayList<Entry>()

        for ((i, data) in x.reversed().withIndex()) {
            cases.add(Entry(i.toFloat(),data.cases.toFloat()))

        }
        val dataCase = LineDataSet(cases, "Terkonfirmasi")
        dataCase.color = Color.parseColor("#2c347c")
        dataCase.setCircleColor(Color.parseColor("#2c347c"))

        val recovered= ArrayList<Entry>()
        for ((i, data) in x.reversed().withIndex()) {
            recovered.add(Entry(i.toFloat(),data.recovered.toFloat()))
        }
        val dataRecovered = LineDataSet(recovered, "Sembuh")
        dataRecovered.color = Color.parseColor("#3c928c")
        dataRecovered.setCircleColor(Color.parseColor("#3c928c"))
        dataRecovered.disableDashedLine()
        dataRecovered.setCircleColors(Color.parseColor("#3c928c"))
        dataRecovered.setColors(Color.parseColor("#3c928c"))

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

        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(dataCase)
        dataSets.add(dataRecovered)
        dataSets.add(dataDeath)

        val lineData = LineData(dataCase)
        lineData.addDataSet(dataRecovered)
        lineData.addDataSet(dataDeath)


        chart.data = lineData

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
    private fun createNewCaseGraph(x: List<DataCovidData>){
        val y = ArrayList<DataCovidData>()

        for (i in 0 until x.size-1){
            val data = DataCovidData(x[i].country,x[i].last_update,x[i].cases-x[i+1].cases,x[i].deaths-x[i+1].deaths,x[i].recovered-x[i+1].recovered)
            y.add(data)
        }
        Log.d("DataCovidian",y.toString())
        val chart = findViewById<LineChart>(R.id.chart)

        val cases= ArrayList<Entry>()

        for ((i, data) in y.reversed().withIndex()) {
            cases.add(Entry(i.toFloat(),data.cases.toFloat()))

        }
        val dataCase = LineDataSet(cases, "Kasus /Hari")
        dataCase.color = Color.parseColor("#2c347c")
        dataCase.setCircleColor(Color.parseColor("#2c347c"))

        val recovered= ArrayList<Entry>()
        for ((i, data) in y.reversed().withIndex()) {
            recovered.add(Entry(i.toFloat(),data.recovered.toFloat()))
        }
        val dataRecovered = LineDataSet(recovered, "Sembuh /Hari")
        dataRecovered.color = Color.parseColor("#3c928c")
        dataRecovered.setCircleColor(Color.parseColor("#3c928c"))
        dataRecovered.disableDashedLine()
        dataRecovered.setCircleColors(Color.parseColor("#3c928c"))
        dataRecovered.setColors(Color.parseColor("#3c928c"))

        val deaths= ArrayList<Entry>()
        for ((i, data) in y.reversed().withIndex()) {
            deaths.add(Entry(i.toFloat(),data.deaths.toFloat()))
        }
        val dataDeath = LineDataSet(deaths, "Meninggal /Hari")
        dataDeath.disableDashedLine()
        dataDeath.color = Color.parseColor("#ec6f58")
        dataDeath.setCircleColor(Color.parseColor("#ec6f58"))
        dataDeath.disableDashedLine()
        dataDeath.setCircleColors(Color.parseColor("#ec6f58"))
//        dataDeath.setDrawCircleHole(false)
        dataDeath.setColors(Color.parseColor("#ec6f58"))
        dataDeath.disableDashedHighlightLine()



        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(dataCase)
        dataSets.add(dataRecovered)
        dataSets.add(dataDeath)

        val lineData = LineData(dataCase)
        lineData.addDataSet(dataRecovered)
        lineData.addDataSet(dataDeath)
        lineData.setValueTextSize(12f)




        chart.data = lineData

        val quarters =ArrayList<String>()
        for ((i, data) in y.reversed().withIndex()) {
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
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter

        val l: Legend = chart.legend
        l.textSize=12f
        l.formSize=16f
        l.xEntrySpace = 15f; // space between the legend entries on the x-axis
        l.yEntrySpace = 15f; // space between the legend entries on the y-axis



        chart.zoom(50f,1F, chart.right.toFloat(), 0F)
        chart.invalidate() // refresh
    }
}