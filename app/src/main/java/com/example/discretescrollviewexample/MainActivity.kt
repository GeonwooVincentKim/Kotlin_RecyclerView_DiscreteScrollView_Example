package com.example.discretescrollviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yarolegovich.discretescrollview.DiscreteScrollView

class MainActivity : AppCompatActivity() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>? = null

    private val recyclerViewExample: RecyclerView by lazy {
        findViewById(R.id.recyclerViewExample)
    }

//    private val picker: DiscreteScrollView by lazy {
//        findViewById(R.id.picker)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        picker
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewExample.layoutManager = layoutManager
//        recyclerViewExample.addOnScrollListener(
//        picker.layoutManager = layoutManager
//        picker.scrollToPosition(R.id.transition_position)
//        picker.setSlideOnFling(true)

        adapter = RecyclerAdapter()
        recyclerViewExample.adapter = adapter


        val padding: Int = ScreenUtils.getScreenWidth(this) / 2
        recyclerViewExample.setPadding(padding, 0, padding, 0)
//        picker.adapter = adapter
    }
}