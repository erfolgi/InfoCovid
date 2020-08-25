package com.primateknos.infocovid_19.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.primateknos.infocovid_19.R
import kotlinx.android.synthetic.main.fragment_info.*
import kotlinx.android.synthetic.main.fragment_info.view.*
import kotlinx.android.synthetic.main.fragment_info.view.close_dialog
import kotlinx.android.synthetic.main.fragment_info.view.next_dialog
import kotlinx.android.synthetic.main.fragment_info.view.prev_dialog



class InfoFragment : Fragment() {
    private val images =
        arrayOf<Int>(R.drawable.no2,R.drawable.no3,R.drawable.no4,R.drawable.no5,R.drawable.no5_5,R.drawable.no6)

    private val title =
        arrayOf<String>("Laman Utama","Data Covid-19","Kebijakan Hukum","Kebijakan Hukum","Kebijakan Hukum","Sosialisasi")

    lateinit var adapter:InfoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v= inflater.inflate(R.layout.fragment_info, container, false)
        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        v.infoRV.layoutManager=layoutManager
        adapter= InfoAdapter(context, images.toMutableList())
        v.infoRV.adapter=adapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(v.infoRV)

        v.indicator.attachToRecyclerView(v.infoRV)

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
        v.infoRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {

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
        v.close_dialog.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commit()
        }
        return v
    }
}