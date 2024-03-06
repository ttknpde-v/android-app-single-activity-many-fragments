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

class ExponentFragment : Fragment() , View.OnClickListener {
    // Late initialization or (lateinit) means variable is empty but not nullable (use with var together)
    private lateinit var textInputLayoutBase: TextInputLayout
    private lateinit var textInputEditBase: TextInputEditText
    private lateinit var textInputLayoutExpo: TextInputLayout
    private lateinit var textInputEditExpo: TextInputEditText
    private lateinit var textViewBase: TextView
    private lateinit var textViewExpo: TextView
    private lateinit var textViewResult: TextView
    private lateinit var button: AppCompatButton
    private lateinit var view: View // it is importance for access activity

    private fun initialWidgets() {
        textInputLayoutBase = view.findViewById(R.id.textInputLayoutBase)
        textInputEditBase = view.findViewById(R.id.textInputEditTextBase)
        textInputLayoutExpo = view.findViewById(R.id.textInputLayoutExpo)
        textInputEditExpo = view.findViewById(R.id.textInputEditTextExpo)
        button = view.findViewById(R.id.button)
        // Actions after clicked
        button.setOnClickListener(this)
    }

     /*companion object { // static function for testing
         fun  validateInput (base : String , expo : String) : IntArray {
             val intArray = IntArray(2) // use InArray because element has stored 0
             if (base.trim().isNotEmpty() && expo.trim().isNotEmpty()) {
                 intArray[0] = base.toInt()
                 intArray[1] = expo.toInt()
                 // return arrayOf(base.toInt(), expo.toInt()) // keep queue
                 return intArray

             } else if (base.trim().isNotEmpty() && expo.trim().isEmpty()) {
                 // textInputLayoutExpo.hint = "Expo number shouldn't be empty"
                 // change color hint text
                 *//*val colorList = ColorStateList(
                     arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
                     intArrayOf(Color.RED)  // The color for the Enabled state
                 )*//*
                 // textInputLayoutExpo.hintTextColor = colorList
                 intArray[0] = base.toInt()
                 intArray[1] = 0
                 // return arrayOf(base.toInt(), 0) // keep queue
                 return intArray // keep queue

             } else if (base.trim().isEmpty() && expo.trim().isNotEmpty()) {
                 // textInputLayoutBase.hint = "Base number shouldn't be empty"
                 // change color hint text
                 *//*val colorList = ColorStateList(
                     arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
                     intArrayOf(Color.RED)  // The color for the Enabled state
                 )*//*
                 // textInputLayoutBase.hintTextColor = colorList
                 // println(base.trim().isEmpty())
                 intArray[0] = 0
                 intArray[1] = expo.toInt()
                 return intArray
             }

             // textInputLayoutExpo.hint = "Expo number shouldn't be empty"
             // textInputLayoutBase.hint = "Base number shouldn't be empty"
             // change color hint text
             *//*val colorList = ColorStateList(
                 arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
                 intArrayOf(Color.RED)  // The color for the Enabled state
             )*//*

             // textInputLayoutBase.hintTextColor = colorList
             // textInputLayoutExpo.hintTextColor = colorList
             intArray[0] = 0
             intArray[1] = 0
             return intArray
         }
     }*/

    private fun  validateInput (base : String , expo : String) : IntArray {
        // change color hint text
        val colorRed = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
            intArrayOf(Color.RED)
        )
        val colorGreen = ColorStateList(
            arrayOf(intArrayOf(android.R.attr.state_enabled)), // Enabled
            intArrayOf(Color.GREEN)
        )

        val warningBaseNumber = "Base number shouldn't be empty"
        val warningExpoNumber = "Expo number shouldn't be empty"

        val intArray = IntArray(2) // use InArray because element has stored 0

        if (base.trim().isNotEmpty() && expo.trim().isNotEmpty()) {

            textInputLayoutBase.hint = base
            textInputLayoutExpo.hint = expo

            textInputLayoutExpo.hintTextColor = colorGreen
            textInputLayoutBase.hintTextColor = colorGreen

            intArray[0] = base.toInt()
            intArray[1] = expo.toInt()

            return intArray

        } else if (base.trim().isNotEmpty() && expo.trim().isEmpty()) {

            textInputLayoutExpo.hint = warningExpoNumber
            textInputLayoutBase.hint = base

            textInputLayoutBase.hintTextColor = colorGreen
            textInputLayoutExpo.hintTextColor = colorRed

            intArray[0] = base.toInt()
            intArray[1] = 0
            return intArray // keep queue

        } else if (base.trim().isEmpty() && expo.trim().isNotEmpty()) {

            textInputLayoutExpo.hint = expo
            textInputLayoutBase.hint = warningExpoNumber

            textInputLayoutBase.hintTextColor = colorRed
            textInputLayoutExpo.hintTextColor = colorGreen

            intArray[0] = 0
            intArray[1] = expo.toInt()

            return intArray
        }

        // case for any input was empty
        textInputLayoutExpo.hint = warningExpoNumber
        textInputLayoutBase.hint = warningBaseNumber

        textInputLayoutBase.hintTextColor = colorRed
        textInputLayoutExpo.hintTextColor = colorRed

        intArray[0] = 0
        intArray[1] = 0

        return intArray

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.exponent_fragment, container , false) // Inflate the layout for this fragment
        initialWidgets()
        return view
    }

    override fun onClick(p0: View?) {

        val inputBase = textInputEditBase.text.toString()
        val inputExpo = textInputEditExpo.text.toString()
        val intArray = validateInput(inputBase,inputExpo)

        textViewBase = view.findViewById(R.id.textViewBase)
        textViewExpo = view.findViewById(R.id.textViewExpo)
        textViewResult = view.findViewById(R.id.textViewResult)

        textViewBase.text = "${intArray[0]}"
        val resultInt = MyMaths.getExponent(intArray[0], intArray[1])
        // Log.d("RESULT",resultInt.toString())
        var resultString = "= y"
        if (resultInt != -8888888) {
            resultString = "= $resultInt"
        }
        textViewExpo.text = "${intArray[1]}"
        textViewResult.text = resultString
    }
}

/*fun main() {
    *//*val input = ExponentFragment.validateInput("","")*//*
    *//*println("${MyMaths.getExponent(2,9)}")*//*
}*/
