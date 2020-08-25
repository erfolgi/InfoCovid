package com.primateknos.infocovid_19

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentManager
import com.primateknos.infocovid_19.dataCovid.DataCovidActivity
import com.primateknos.infocovid_19.dataCovid.DataCovidContract
import com.primateknos.infocovid_19.info.InfoFragment
import com.primateknos.infocovid_19.materi.ListActivity
import com.primateknos.infocovid_19.model.DataCovidData
import com.primateknos.infocovid_19.sosialisasi.SosialisasiFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var fm: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        supportActionBar!!.hide()
//        datacovid.setBackgroundResource(R.drawable.gradation_yellow)
//        sosialisasi.setBackgroundResource(R.drawable.gradation_yellow)
//        materi.setBackgroundResource(R.drawable.gradation_yellow)

        datacovid.setOnClickListener {
            val i = Intent(this@MainActivity, DataCovidActivity::class.java)
            startActivity(i)
        }
        fm=supportFragmentManager
        sosialisasi.setOnClickListener {
            val mainFragment: SosialisasiFragment = SosialisasiFragment()

            fm.beginTransaction().add(R.id.container, mainFragment)
                .commit()
        }

        infoButton.setOnClickListener {
            val mainFragment: InfoFragment = InfoFragment()

            fm.beginTransaction().add(R.id.container, mainFragment)
                .commit()
        }

        materi.setOnClickListener {
            val i = Intent(this@MainActivity, ListActivity::class.java)
            startActivity(i)
        }
    }

}