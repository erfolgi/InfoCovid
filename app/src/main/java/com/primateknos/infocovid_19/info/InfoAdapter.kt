package com.primateknos.infocovid_19.info

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.jsibbold.zoomage.ZoomageView
import com.primateknos.infocovid_19.R
import com.primateknos.infocovid_19.sosialisasi.SosialisasiAdapter

class InfoAdapter (private val context: Context?, private val items: MutableList<Int>)
    : RecyclerView.Adapter<InfoAdapter.InfoHolder>(){
    lateinit var mInflater: LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InfoHolder {
        Log.e("RoomListFragment", "createHolder")
        mInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemRow = mInflater.inflate(R.layout.item_info, parent, false)
        return InfoHolder(itemRow)
    }
    override fun getItemCount(): Int {
        return items.size
    }
    override fun onBindViewHolder(holder: InfoHolder, position: Int) {
        holder.itemImage.setImageResource(items[position])
    }

    class InfoHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView = itemView.findViewById<ImageView>(R.id.info_image)
    }
}