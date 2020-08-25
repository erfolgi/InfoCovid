package com.primateknos.infocovid_19.sosialisasi

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jsibbold.zoomage.ZoomageView
import com.primateknos.infocovid_19.R
import kotlinx.android.synthetic.main.item_sosialisasi.view.*

class SosialisasiAdapter  (private val context: Context?, private val items: MutableList<Int>)
    : RecyclerView.Adapter<SosialisasiAdapter.SosialisasiHolder>(){
    lateinit var mInflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SosialisasiHolder {
        Log.e("RoomListFragment", "createHolder")
        mInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemRow = mInflater.inflate(R.layout.item_sosialisasi, parent, false)
        return SosialisasiHolder(itemRow)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: SosialisasiHolder, position: Int) {
        holder.itemImage.setImageResource(items[position])
    }

    class SosialisasiHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage: ZoomageView = itemView.findViewById<ZoomageView>(R.id.item_image)
    }
}