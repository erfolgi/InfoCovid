package com.primateknos.infocovid_19.materi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.primateknos.infocovid_19.R
import com.primateknos.perlindungankonsumen.listMateri.ListModel

class ListAdapter (private val context: Context?, private val items: List<ListModel>)
    :  RecyclerView.Adapter<ListAdapter.MateriHolder>(){
    lateinit var mInflater: LayoutInflater
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MateriHolder {
        mInflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemRow = mInflater.inflate(R.layout.item_materi, p0, false)
        return MateriHolder(itemRow)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MateriHolder, position: Int) {
        holder.bab.text=items[position].bab
        //  holder.nama.text=items[position].nama
        holder.bind(context,items[position].file,items[position].bab+" - "+ items[position].nama)
    }
    class MateriHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bab = itemView.findViewById<TextView>(R.id.tx_bab)
        //var nama = itemView.findViewById<TextView>(R.id.tx_nama)
        fun bind(con: Context?, ID:String, title:String) {

            itemView.setOnClickListener {
                val intent = Intent (con, DetailActivity::class.java)
                intent.putExtra(DetailActivity.extrafile, ID)
                intent.putExtra(DetailActivity.extratitle, title)
                itemView.context.startActivity(intent)
            }
        }
    }
}