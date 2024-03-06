package com.kotlin.ttnpdev.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.kotlin.ttnpdev.R

class CarInterestFragment : Fragment(), View.OnClickListener {

    private lateinit var view: View // it is importance for access activity

    // basic input
    private lateinit var editTextTotalPrice: EditText
    private lateinit var editTextDownPayment: EditText
    private lateinit var editTextInterestPerYear: EditText
    private lateinit var editTextLoadTerm: EditText
    private lateinit var button: AppCompatButton

    // these text views use for result
    private lateinit var textViewTotalLoan: TextView
    private lateinit var textViewInterest: TextView
    private lateinit var textViewTotalInterest: TextView
    private lateinit var textViewTotalCost: TextView
    private lateinit var textViewInstallmentPerMonth: TextView

    private fun initialCarInterestFragmentWidgets() {

        editTextTotalPrice = view.findViewById(R.id.editTextTotalPrice)
        editTextDownPayment = view.findViewById(R.id.editTextDownPayment)
        editTextInterestPerYear = view.findViewById(R.id.editTextInterestPerYear)
        editTextLoadTerm = view.findViewById(R.id.editTextLoadTerm)

        textViewTotalLoan = view.findViewById(R.id.textViewTotalLoan)
        textViewInterest = view.findViewById(R.id.textViewInterest)
        textViewTotalInterest = view.findViewById(R.id.textViewTotalInterest)
        textViewTotalCost = view.findViewById(R.id.textViewTotalCost)
        textViewInstallmentPerMonth = view.findViewById(R.id.textViewInstallmentPerMonth)

        button = view.findViewById(R.id.button)
        button.setOnClickListener(this)

    }

    private fun validateInput(
        totalPrice: String, downPayment: String,
        interestPerYear: String, loadTerm: String
    ): Boolean {
        // only case will return true
        return totalPrice.trim().isNotEmpty() && downPayment.trim().isNotEmpty() && interestPerYear.trim()
            .isNotEmpty() && loadTerm.trim().isNotEmpty()
    }

    companion object {

        var totalPrice : Double = 0.0
        var downPayment : Double = 0.0
        var interest : Float = 0.0F
        var loanTerm : Int = 0
        // var interestPerYear : Float = 0.0F

        fun totalLoanAmount() : String = "Total Loan amount = ${String.format("%.1f",totalPrice-downPayment)} ฿"
        fun interestPerYear() : String = "Interest (Per Year) $interest %"
        fun interestXLoanTerm() : String {
            val interestCost = (totalPrice-downPayment) * (interest/100)
            val resultCostPerYear = interestCost * loanTerm
            return "Interest x Loan Term ${String.format("%.1f",interestCost)} x $loanTerm = ${String.format("%.1f",resultCostPerYear)} ฿"
        }
        fun totalCost() : String {
            val totalCost = (totalPrice-downPayment) + ( ((totalPrice-downPayment)*(interest/100)) * loanTerm )
            return "Total Cost = ${String.format("%.1f",totalCost)} ฿"
        }
        fun installmentPerMonth() : String {
            val installment = ((totalPrice-downPayment) + ( ((totalPrice-downPayment)*(interest/100)) * loanTerm) )
            // println(installment)
            val vat = (installment * 0.07).toFloat()
            // println(vat)
            val installmentPerMonth = ((installment + vat) / (loanTerm*12))
            return "Installment (Per month) ${String.format("%.1f",installmentPerMonth)}  ฿"
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        view =
            inflater.inflate(R.layout.car_interest_fragment, container, false) // Inflate the layout for this fragment
        initialCarInterestFragmentWidgets()
        return view
    }

    override fun onClick(p0: View?) {
        val totalPriceString = editTextTotalPrice.text.toString()
        val downPaymentString = editTextDownPayment.text.toString()
        val interestPerYearString = editTextInterestPerYear.text.toString()
        val loadTermString = editTextLoadTerm.text.toString()
        val check = validateInput(totalPriceString, downPaymentString, interestPerYearString, loadTermString)

        if (check) {

            val totalPriceDouble = editTextTotalPrice.text.toString().toDouble()
            val downPaymentDouble = editTextDownPayment.text.toString().toDouble()
            val interestPerYearFloat = editTextInterestPerYear.text.toString().toFloat()
            val loadTermInt = editTextLoadTerm.text.toString().toInt()

            totalPrice = totalPriceDouble
            interest = interestPerYearFloat
            downPayment = downPaymentDouble
            loanTerm = loadTermInt

            textViewTotalLoan.text = totalLoanAmount()
            textViewInterest.text = interestPerYear()
            textViewTotalInterest.text = interestXLoanTerm()
            textViewTotalCost.text = totalCost()
            textViewInstallmentPerMonth.text = installmentPerMonth()

        } else {
            textViewTotalLoan.text = ""
            textViewInterest.text = ""
            textViewTotalInterest.text = ""
            textViewTotalCost.text = ""
            textViewInstallmentPerMonth.text = ""
        }
    }

}

/*
fun main() {
    CarInterestFragment.totalPrice = 379000.0
    CarInterestFragment.interest = 2.69F
    CarInterestFragment.downPayment = 30000.0
    CarInterestFragment.loanTerm = 5

    println(CarInterestFragment.totalLoanAmount())
    println(CarInterestFragment.interestPerYear())
    println(CarInterestFragment.interestXLoanTerm())
    println(CarInterestFragment.totalCost())
    println(CarInterestFragment.installmentPerMonth())
}
*/
