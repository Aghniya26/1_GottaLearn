package com.example.gottalearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListerner {
    private val exampleList= generateDummyList(20)
    private val adapter=ExampleAdapter(exampleList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       val recyclerView : RecyclerView = findViewById(R.id.recycle_view)
        recyclerView.adapter=adapter
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

    }
    fun insertItem(view: View){
        val index= Random.nextInt(8)
        val newItem=ExampleItem(
            R.drawable.ic_bookmark_yellow,
            "New item at Position $index",
            "line2"

        )
        exampleList.add(index, newItem)
        adapter.notifyItemInserted(index)
    }
    fun removeItem(view: View){
        val index= Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position Clicked", Toast.LENGTH_SHORT).show()
        val clickedItem=exampleList[position]
        clickedItem.text1="Clicked"
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList (size: Int): ArrayList<ExampleItem>{
        val list=ArrayList<ExampleItem>()

        for (i in 0 until size){
            val drawable= when (i % 4){
                0 -> R.drawable.ic_bookmark_green
                1 -> R.drawable.ic_bookmark_orange
                2 -> R.drawable.ic_bookmark_yellow
                else -> R.drawable.ic_bookmark_red
            }
            val text1=when(i % 3){
                0->"APSI"
                1->"PBB"
                else->"PPL1"
            }
            val item = ExampleItem(drawable, text1, text2 = "$i d 2h")
            list+=item

        }
        return list
    }
}