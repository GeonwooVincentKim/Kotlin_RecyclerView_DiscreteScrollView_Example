package com.example.discretescrollviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yarolegovich.discretescrollview.DiscreteScrollView

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private lateinit var rvHorizontalPicker: RecyclerView
    private lateinit var tvSelectedItem: TextView

    private val recyclerViewExample: RecyclerView by lazy {
        findViewById(R.id.recyclerViewExample)
    }

//    private val picker: DiscreteScrollView by lazy {
//        findViewById(R.id.picker)
//    }
    private val data = (1..20).toList().map { it.toString() } as ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTvSelectedItem()
        setHorizontalPicker()
//        picker
//        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerViewExample.layoutManager = layoutManager
////        recyclerViewExample.addOnScrollListener(
////        picker.layoutManager = layoutManager
////        picker.scrollToPosition(R.id.transition_position)
////        picker.setSlideOnFling(true)
//
//        adapter = RecyclerAdapter()
//        recyclerViewExample.adapter = adapter
//
//
//        val padding: Int = ScreenUtils.getScreenWidth(this) / 3
//        recyclerViewExample.setPadding(padding, 0, padding, 0)
//        picker.adapter = adapter

//        val position: Int = recyclerViewExample.getChildLayoutPosition()
//        recyclerViewExample.smoothScrollToPosition(position)
    }

    private fun setTvSelectedItem() {
        tvSelectedItem = findViewById(R.id.tv_selected_item)
    }

    private fun setHorizontalPicker() {
        rvHorizontalPicker = findViewById(R.id.recyclerViewExample)

        // Setting the padding such that the items will appear in the middle of the screen
        val padding: Int = ScreenUtils.getScreenWidth(this)/2 - ScreenUtils.dpToPx(this, 40)
        rvHorizontalPicker.setPadding(padding, 0, padding, 0)

        // Setting layout manager
        rvHorizontalPicker.layoutManager = SliderLayoutClass(this).apply {
            callback = object : SliderLayoutClass.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {
                    tvSelectedItem.setText(data[layoutPosition])
                }
            }
        }

        // Setting Adapter
        rvHorizontalPicker.adapter = RecyclerAdapter().apply {
            setData(data)
            callback = object : RecyclerAdapter.Callback {
                override fun onItemClicked(view: View) {
                    rvHorizontalPicker.smoothScrollToPosition(rvHorizontalPicker.getChildLayoutPosition(view))
                }
            }
        }
    }
}