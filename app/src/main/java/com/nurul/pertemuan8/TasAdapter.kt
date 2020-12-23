package com.nurul.pertemuan8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nurul.pertemuan8.Database.Tas
import kotlinx.android.synthetic.main.adapter_tas.view.*

class TasAdapter (private val AllTas: ArrayList<Tas>, private val listener: OnAdapterListener) : RecyclerView.Adapter<TasAdapter.TasViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasViewHolder {
        return TasViewHolder(
            LayoutInflater.from(parent.context).inflate( R.layout.adapter_tas, parent, false)
        )
    }

    override fun getItemCount() = AllTas.size

    override fun onBindViewHolder(holder: TasViewHolder, position: Int) {
        val tas = AllTas[position]
        holder.view.text_type.text = tas.type
        holder.view.text_type.setOnClickListener {
            listener.onClick(tas)
        }
        holder.view.icon_delete.setOnClickListener {
            listener.onDelete(tas)
        }
    }

    class TasViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    fun setData(list: List<Tas>) {
        AllTas.clear()
        AllTas.addAll(list)
        notifyDataSetChanged()
    }

    interface OnAdapterListener {
        fun onClick(tas: Tas)
        fun onDelete(tas: Tas)
    }
}