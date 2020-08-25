package com.primateknos.infocovid_19.materi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ExpandableListView
import androidx.recyclerview.widget.RecyclerView
import com.primateknos.infocovid_19.R
import com.primateknos.perlindungankonsumen.listMateri.ExpandListModel
import com.primateknos.perlindungankonsumen.listMateri.ListModel

class ListActivity : AppCompatActivity() {
    lateinit var RV: RecyclerView
    lateinit var matadapter: ListAdapter
    private var items: MutableList<ListModel> = mutableListOf()
    lateinit var item: ListModel
    var data1 = arrayOf(
        arrayOf("ISI KEPPRES RI NO. 11 TAHUN 2O2O", "KEPPRES RI NO. 11 TAHUN 2O2O", "0")

    )//Nama Produk, Jumlah Produk(isi dengan 0 dulu), Harga, Total(isi dengan 0 dulu)
    //urutkan seperti di ItemActivity

    var data2 = arrayOf(
        arrayOf("PENDAHULUAN", "PMK RI NO. 9 TAHUN 2020", "0"),
        arrayOf("BAB I : KETENTUAN UMUM", "PMK RI NO. 9 TAHUN 2020", "2"),
        arrayOf("BAB II : PENETAPAN PEMBATASAN SOSIAL BERSKALA BESAR", "PMK RI NO. 9 TAHUN 2020", "2"),
        arrayOf("BAB III : PELAKSANAAN PEMBATASAN SOSIAL BERSKALA BESAR", "PMK RI NO. 9 TAHUN 2020", "6"),
        arrayOf("BAB IV : PENCATATAN DAN PELAPORAN", "PMK RI NO. 9 TAHUN 2020", "9"),
        arrayOf("BAB V : PEMBINAAN DAN PENGAWASAN", "PMK RI NO. 9 TAHUN 2020", "9"),
        arrayOf("BAB VI : KETENTUAN PENUTUP", "PMK RI NO. 9 TAHUN 2020", "11"),
        arrayOf("PEDOMAN PSBB : A. PENDAHULUAN", "PMK RI NO. 9 TAHUN 2020", "13"),
        arrayOf("PEDOMAN PSBB : B. KRITERIA PENETAPAN PEMBATASAN SOSIAL BERSKALA BESAR", "PMK RI NO. 9 TAHUN 2020", "15"),
        arrayOf("PEDOMAN PSBB : C. TATA CARA PENETAPAN PEMBATASAN SOSIAL BERSKALA BESAR", "PMK RI NO. 9 TAHUN 2020", "16"),
        arrayOf("PEDOMAN PSBB : D. PELAKSANAAN PEMBATASAN SOSIAL BERSKALA BESAR", "PMK RI NO. 9 TAHUN 2020", "20"),
        arrayOf("PEDOMAN PSBB : E. PENUTUP", "PMK RI NO. 9 TAHUN 2020", "27")
    )

    var data3 = arrayOf(
        arrayOf("ISI PP RI NO. 21 TAHUN 2020", "PP RI NO. 21 TAHUN 2020", "0"),
        arrayOf("PENJELASAN PP RI NO. 21 TAHUN 2020", "PP RI NO. 21 TAHUN 2020", "5")
    )


    var data4 = arrayOf(
        arrayOf("PENDAHULUAN", "KMK RI NO. HK.01.07/MENKES/328/2020", "0"),
        arrayOf("BAB I : PENDAHULUAN", "KMK RI NO. HK.01.07/MENKES/328/2020", "3"),
        arrayOf("BAB II : PENCEGAHAN DAN PENGENDALIAN COVID-19 DI LINGKUNGAN KERJA PERKANTORAN DAN INDUSTRI", "KMK RI NO. HK.01.07/MENKES/328/2020", "7"),
        arrayOf("BAB III : KOORDINASI ANTARA TEMPAT KERJA DENGAN PEMERINTAH DAERAH DALAM PENANGANAN COVID-19", "KMK RI NO. HK.01.07/MENKES/328/2020", "21"),
        arrayOf("BAB IV : PENUTUP", "KMK RI NO. HK.01.07/MENKES/328/2020", "23")
    )

    var header = arrayOf(
        "KEPUTUSAN PRESIDEN REPUBLIK INDONESIA NOMOR 1 1TAHUN 2O2O"
        ,"PERATURAN MENTERI KESEHATAN REPUBLIK INDONESIA NOMOR 9 TAHUN 2020"
        ,"PERATURAN PEMERINTAH REPUBLIK INDONESIA NOMOR 21 TAHUN 2O2O"
        ,"KEPUTUSAN MENTERI KESEHATAN REPUBLIK INDONESIA NOMOR HK.01.07/MENKES/328/2020"
    )
    lateinit var listDataHeader: List<String>
    lateinit var listDataChild: HashMap<String, List<ExpandListModel>>
    var listAdapter: ExpandableListAdapter? = null
    lateinit var expListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_list)
        var a:MutableList<ExpandListModel> = mutableListOf()
        var b: ExpandListModel
        for (i in data1.indices) {
            b= ExpandListModel(data1[i] [0],data1[i][1],data1[i][2])
            a.add(b)
        }
        listDataHeader= header.toList()
        listDataChild = HashMap()
        listDataChild[listDataHeader[0]]=a

        var c:MutableList<ExpandListModel> = mutableListOf()
        var d: ExpandListModel
        for (i in data2.indices) {
            d= ExpandListModel(data2[i] [0],data2[i][1],data2[i][2])
            c.add(d)
        }
        listDataChild[listDataHeader[1]]=c

        var e:MutableList<ExpandListModel> = mutableListOf()
        var f: ExpandListModel
        for (i in data3.indices) {
            f= ExpandListModel(data3[i] [0],data3[i][1],data3[i][2])
            e.add(f)
        }
        listDataChild[listDataHeader[2]]=e

        var g:MutableList<ExpandListModel> = mutableListOf()
        var h: ExpandListModel
        for (i in data4.indices) {
            h= ExpandListModel(data4[i] [0],data4[i][1],data4[i][2])
            g.add(h)
        }
        listDataChild[listDataHeader[3]]=g
        expListView = findViewById(R.id.list_item)
        listAdapter = ExpandableListAdapter(this, listDataHeader, listDataChild)
        expListView.setAdapter(listAdapter)
        expListView.expandGroup(0)
        expListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
            /* You must make use of the View v, find the view by id and extract the text as below*/
            Log.d("test_onclick", "group : $groupPosition /child : $childPosition/id : $id")
            val startActivityIntent = Intent(this, DetailActivity::class.java)


            when (groupPosition) {
                0 -> {
                    startActivityIntent.putExtra(DetailActivity.extrapdf, "keppres_nomor_11_tahun_2020.pdf")
                    startActivityIntent.putExtra(DetailActivity.extrafile, data1[childPosition][2])
                    startActivityIntent.putExtra(DetailActivity.extratitle, data1[childPosition][1])
                }
                1 -> {
                    startActivityIntent.putExtra(DetailActivity.extrapdf, "pmk_no_9_Th_2020.pdf")
                    startActivityIntent.putExtra(DetailActivity.extrafile, data2[childPosition][2])
                    startActivityIntent.putExtra(DetailActivity.extratitle, data2[childPosition][1])
                }
                2 -> {
                    startActivityIntent.putExtra(DetailActivity.extrapdf, "pp_no_21_tahun_2020.pdf")
                    startActivityIntent.putExtra(DetailActivity.extrafile, data3[childPosition][2])
                    startActivityIntent.putExtra(DetailActivity.extratitle, data3[childPosition][1])
                }
                3 -> {
                    startActivityIntent.putExtra(DetailActivity.extrapdf, "kmk_no__hk_01_07-menkes-328-2020.pdf")
                    startActivityIntent.putExtra(DetailActivity.extrafile, data4[childPosition][2])
                    startActivityIntent.putExtra(DetailActivity.extratitle, data4[childPosition][1])
                }
                // i missed this
            }

            startActivity(startActivityIntent)


            true  // i missed this
        }

//        RV=findViewById(R.id.rv_materi)
//        RV.setHasFixedSize(true)
//        RV.layoutManager = LinearLayoutManager(this)
//        matadapter = ListAdapter(this,items)
//        RV.adapter = matadapter
    }
}