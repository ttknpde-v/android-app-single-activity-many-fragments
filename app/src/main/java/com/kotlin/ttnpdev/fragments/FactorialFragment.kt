package com.kotlin.ttnpdev.fragments

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.kotlin.ttnpdev.R
import com.kotlin.ttnpdev.objects.MyMaths

class FactorialFragment  : Fragment() , View.OnClickListener {
    // Late initialization or (lateinit) means variable is empty but not nullable (use with var together)
    private lateinit var textInputLayout: TextInputLayout
    private lateinit var textInputEdit: TextInputEditText
    private lateinit var button: AppCompatButton
    private lateinit var textViewResult: TextView
    private lateinit var view: View // it is importance for access activity

    init {
        Log.d("CLASS","${this@FactorialFragment}")
    }

    private fun initialWidgets() {
        textInputLayout = view.findViewById(R.id.textInputLayoutNumber)
        textInputEdit = view.findViewById(R.id.textInputEditTextNumber)
        button = view.findViewById(R.id.button)
        // Actions after clicked
        button.setOnClickListener(this)
    }

    private fun validateInput (input : String) : Int {
        val colorGreen = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled))  , // Enabled
            intArrayOf(Color.GREEN)  // The color for the Enabled state
        )
        val colorRed = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled))  , // Enabled
            intArrayOf(Color.RED)  // The color for the Enabled state
        )
        return if (input.trim().isNotEmpty()) {

            textInputLayout.hintTextColor = colorGreen
            textInputLayout.hint = input
            input.toInt()

        } else {
            textInputLayout.hint = "Integer shouldn't be empty"
            textInputLayout.hintTextColor = colorRed
            0
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.factorial_fragment, container , false) // Inflate the layout for this fragment
        initialWidgets()
        return view
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(p0: View?) {
        val input = textInputEdit.text.toString()
        val numberInput = validateInput(input)

        textViewResult = view.findViewById(R.id.textViewResult)
        textViewResult.text = "$numberInput! = ${MyMaths.getFactorial(numberInput)}"
    }
}