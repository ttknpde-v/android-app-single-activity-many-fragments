package com.kotlin.ttnpdev.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.ttnpdev.R
import com.kotlin.ttnpdev.objects.MyMaths

class multiply_table_row(private val table: Int) : RecyclerView.Adapter<multiply_table_row.MyViewHolder>() {

    // private var multiplyTable: ArrayList<String>? = null
    private var multiplyTable: Array<String>? = null

    init {
        /*multiplyTable = ArrayList()
        multiplyTable!!.add("1 x 2 = 2")
        multiplyTable!!.add("1 x 3 = 6")*/
        multiplyTable = MyMaths.getMultiplyTable2(table)
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        /* prepare id in item and we use take them for taking some value */
        var textViewResult: TextView = view.findViewById(R.id.textViewResult)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val itemView: View = LayoutInflater
            .from(p0.context)
            .inflate(R.layout.multiply_table_row, p0, false)
        return MyViewHolder(itemView) /* return My inner class store id from item xml */
    }

    override fun getItemCount(): Int {
        return multiplyTable!!.size
    }

    override fun onBindViewHolder(p0: MyViewHolder, p1: Int) {
        p0.textViewResult.text = multiplyTable!![p1]
    }
}