package com.example.discretescrollviewexample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/* RecyclerView.Adapter - Get the `RecyclerView.ViewHolder` Type from `SliderItemViewHolder` */
class RecyclerAdapter : RecyclerView.Adapter<SliderItemViewHolder>() {
    private val data: ArrayList<String> = ArrayList();
    var callback: Callback? = null
    private val clickListener = View.OnClickListener { v -> v?.let { callback?.onItemClicked(it) } }

//    var indexInitializerItem = -1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_slider_item, parent, false)
        itemView.setOnClickListener(clickListener)

        return SliderItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SliderItemViewHolder, position: Int) {
//        holder.tvItem?.text = data[0]
        holder.tvItem?.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: ArrayList<String>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    interface Callback {
        fun onItemClicked(view: View)
    }
}