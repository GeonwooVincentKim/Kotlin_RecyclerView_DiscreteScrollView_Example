package com.example.discretescrollviewexample

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter : RecyclerView.Adapter<SliderItemViewHolder>() {
//    private var titles = arrayOf(
//        "Chapter One",
//        "Chapter Two",
//        "Chapter Three",
//        "Chapter Four",
//        "Chapter Five",
//        "Chapter Six",
//        "Chapter Seven",
//        "Chapter Eight"
//    )
//
//    private var details = arrayOf(
//        "Item one details",
//        "Item two details",
//        "Item three details",
//        "Item four details",
//        "Item five details",
//        "Item six details",
//        "Item seven details",
//        "Item eight details"
//    )
//
//    private var images = intArrayOf(
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo,
//        R.drawable.ic_kotlin_logo
//    )

    private val data: ArrayList<String> = ArrayList();
    var callback: Callback? = null
    val clickListener = View.OnClickListener { v -> v?.let { callback?.onItemClicked(it) } }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderItemViewHolder {
//        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_layout, parent, false)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_slider_item, parent, false)
        itemView.setOnClickListener(clickListener)

        return SliderItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SliderItemViewHolder, position: Int) {
//        holder.itemTitle.text = titles[position]
//        holder.itemDetail.text = details[position]
//        holder.itemImage.setImageResource(images[position])
        holder.tvItem?.text = data[position]
    }

    override fun getItemCount(): Int {
//        return titles.size
        return data.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDetail: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image)
            itemTitle = itemView.findViewById(R.id.item_title)
            itemDetail = itemView.findViewById(R.id.item_detail)

//            itemView.setOnClickListener {
//                val position: Int = absoluteAdapterPosition
//                Toast.makeText(
//                    itemView.context,
//                    "you clicked on ${titles[position]}",
//                    Toast.LENGTH_LONG
//                ).show()
//
////                val padding: Int = ScreenUtils.getScreenWidth(this) / 2
//            }

//            itemView.setOnClickListener(object: View.OnClickListener{
//                override fun onClick(v: View?) {
//                    v?.let { callback?.onI}
//                }
//
//            })

            itemView.setOnClickListener(clickListener)
        }
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