package com.example.discretescrollviewexample

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.preference.PreferenceManager
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var tvSelectedItem: TextView
    private val recyclerViewExample: RecyclerView by lazy {
        findViewById(R.id.recyclerViewExample)
    }

//    lateinit var binding : ActivityMainBinding

    private val data = (1..20).toList().map { it.toString() } as ArrayList<String>

//    fun findIndex(arr: ArrayList<String>, item: Int): Int {
//        for(i in arr){
//            if(arr[i] == item){
//                return i
//            }
//        }
//
//        return -1
//    }

    private var lastPosition: Int = 0
    val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(this)
    val fragment01: ResultPage.Companion = ResultPage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTvSelectedItem()
        setHorizontalPicker()
    }

    private fun setTvSelectedItem() {
        tvSelectedItem = findViewById(R.id.tv_selected_item)
    }

    private fun setHorizontalPicker() {
        // Setting the padding such that the items will appear in the middle of the screen
        val padding: Int = ScreenUtils.getScreenWidth(this) / 2 - ScreenUtils.dpToPx(this, 40)
        recyclerViewExample.setPadding(padding, 0, padding, 0)
//
//        val getPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
//        lastPosition = getPreferences.getInt("lastPos", 0)
//        recyclerViewExample.scrollToPosition(lastPosition)
//
//        recyclerViewExample.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                lastPosition = linearLayoutManager.findFirstVisibleItemPosition()
//            }
//        })

//        val tvSelectedItem: TextView = findViewById(R.id.tv_selected_item)

        // Setting layout manager
        recyclerViewExample.layoutManager = SliderLayoutClass(this).apply {
            callback = object : SliderLayoutClass.OnItemSelectedListener {
                override fun onItemSelected(layoutPosition: Int) {
                    tvSelectedItem.text = data[layoutPosition]
                }
            }
        }

        // Setting Adapter
        recyclerViewExample.adapter = RecyclerAdapter().apply {
            setData(data)
            callback = object : RecyclerAdapter.Callback {
                override fun onItemClicked(view: View) {
                    recyclerViewExample.smoothScrollToPosition(
                        recyclerViewExample.getChildLayoutPosition(
                            view
                        )
                    )
                }
            }
        }

        val backButton: TextView = findViewById(R.id.backButton)
        backButton.setOnClickListener {

        }

        val getPreferences: SharedPreferences =
            getSharedPreferences("lastPosition", Context.MODE_PRIVATE)
//        val getPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        lastPosition = getPreferences.getInt("lastPos", 0)
        tvSelectedItem.text = getPreferences.getString("tvSelectedItem", lastPosition.toString())
        Log.d("Testing...", lastPosition.toString())
        recyclerViewExample.scrollToPosition(lastPosition)

        val e: SharedPreferences.Editor = getPreferences.edit()

        recyclerViewExample.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                lastPosition =
                    (recyclerViewExample.layoutManager as SliderLayoutClass).findFirstCompletelyVisibleItemPosition()
            }
        })

//        binding = ActivityMainBinding.inflate(layoutInflater)


//        val homeIntent = Intent(this@MainActivity, SomePage::class.java)

        val okButton: TextView = findViewById(R.id.okButton)
        okButton.setOnClickListener {
            e.putInt("lastPos", lastPosition)
            e.putString("tvSelectedItem", tvSelectedItem.text.toString())
            e.apply()

//            val bundle = Bundle()
//            bundle.putInt("lastPos", lastPosition)
//            bundle.putString("tvSelectedItem", tvSelectedItem.text.toString())
////
//            val fragmentNumber1 = ResultPage()
//            fragmentNumber1.arguments = bundle

            Log.d("Check", lastPosition.toString())
            Log.d("Check2", tvSelectedItem.text.toString())

//            Log.d("FindIndex -> ", data[lastPosition])

//            val transaction = supportFragmentManager.beginTransaction()
//            transaction.add(R.id.frameLayout, fragmentNumber1)
//            transaction.commit()
//            supportFragmentManager
//                .beginTransaction()
//                .replace(R.id.frameLayout, fragmentNumber1)
//                .commit()
//            transaction.add(R.id.frameLayout, fragmentNumber1)
//            transaction.commit()
//            startActivity(homeIntent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val getPreferences: SharedPreferences =
            getSharedPreferences("lastPosition", Context.MODE_PRIVATE)

//        val getPreferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(baseContext)
        val e: SharedPreferences.Editor = getPreferences.edit()
        e.putInt("lastPos", lastPosition)
        e.putString("tvSelectedItem", tvSelectedItem.text.toString())
        e.apply()
    }
}