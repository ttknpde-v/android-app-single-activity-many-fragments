package com.kotlin.ttnpdev.fragments

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

import com.kotlin.ttnpdev.R
import com.kotlin.ttnpdev.adapter.multiply_table_row

class MultiplyTableFragment : Fragment(), View.OnClickListener {

    private lateinit var view: View // it is importance for access activity
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: RecyclerView.Adapter<multiply_table_row.MyViewHolder>
    private lateinit var setLayoutManager: RecyclerView.LayoutManager
    private lateinit var textInputLayoutNumber: TextInputLayout
    private lateinit var textInputEditTextNumber: TextInputEditText
    private lateinit var button: AppCompatButton


    private fun initialMultiplyTableFragmentWidgets() {
        textInputLayoutNumber = view.findViewById(R.id.textInputLayoutNumber)
        textInputEditTextNumber = view.findViewById(R.id.textInputEditTextNumber)
        recyclerView = view.findViewById(R.id.recyclerViewMultiplyTable)
        button = view.findViewById(R.id.button)
        button.setOnClickListener(this)
    }

    private fun  validateInput (table : String) : Int {
        // change color hint text
        val colorRed = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
            intArrayOf(Color.RED)
        )
        val colorGreen = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
            intArrayOf(Color.GREEN)
        )

        val warningTableNumber = "Integer shouldn't be empty"

        if (table.trim().isNotEmpty()) {

            textInputLayoutNumber.hintTextColor = colorGreen
            textInputLayoutNumber.hint = table

            return table.toInt()
        }

        textInputLayoutNumber.hintTextColor = colorRed
        textInputLayoutNumber.hint = warningTableNumber
        return 0

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.multiply_table_fragment, container, false) // Inflate the layout for this fragment
        initialMultiplyTableFragmentWidgets()
        // set layout of this view
        setLayoutManager = LinearLayoutManager(view.context)
        recyclerView.layoutManager = setLayoutManager

        return view
    }


    override fun onClick(p0: View?) {
        val tableString = textInputEditTextNumber.text.toString()
        val tableNumber = validateInput(tableString)
        if (tableNumber == 0) {
            // for cleaning my adapter
            recyclerView.adapter = null
        }
        else {
            myAdapter = multiply_table_row(tableNumber)
            recyclerView.adapter = myAdapter
        }
    }
}