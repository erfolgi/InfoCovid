package com.primateknos.infocovid_19.sosialisasi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import androidx.recyclerview.widget.SnapHelper
import com.primateknos.infocovid_19.R
import kotlinx.android.synthetic.main.fragment_sosialisasi.view.*


class SosialisasiFragment : Fragment() {

    private val images =
        arrayOf<Int>(R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,R.drawable.a7,R.drawable.a8,
    R.drawable.a9,R.drawable.a10,R.drawable.a11,R.drawable.a12,R.drawable.a13,R.drawable.a14,R.drawable.a15,R.drawable.a16,R.drawable.a17,
            R.drawable.a18)

    lateinit var adapter:SosialisasiAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v=inflater.inflate(R.layout.fragment_sosialisasi, container, false)
        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        v.sosialisasiRV.layoutManager=layoutManager
        adapter= SosialisasiAdapter(context, images.toMutableList())
        v.sosialisasiRV.adapter=adapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(v.sosialisasiRV)

        v.close_dialog.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }

        v.next_dialog.setOnClickListener {
            if(layoutManager.findFirstCompletelyVisibleItemPosition()<adapter.itemCount){
                layoutManager.scrollToPosition(layoutManager.findFirstCompletelyVisibleItemPosition()+1)

            }

        }
        v.prev_dialog.setOnClickListener {
            if(layoutManager.findFirstCompletelyVisibleItemPosition()>0){
                layoutManager.scrollToPosition(layoutManager.findFirstCompletelyVisibleItemPosition()-1)
            }
        }
        v.sosialisasiRV.addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visiblePosition: Int = layoutManager.findFirstCompletelyVisibleItemPosition()
                if (visiblePosition==0) {
                    v.prev_dialog.visibility=View.INVISIBLE
                }else{
                    v.prev_dialog.visibility=View.VISIBLE
                }

                if (visiblePosition>=adapter.itemCount-1) {
                    v.next_dialog.visibility=View.INVISIBLE
                }else{
                    v.next_dialog.visibility=View.VISIBLE
                }
            }
        })
        return v
    }

}