package com.kotlin.ttnpdev.controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.kotlin.ttnpdev.R
import com.kotlin.ttnpdev.fragments.CarInterestFragment
import com.kotlin.ttnpdev.fragments.ExponentFragment
import com.kotlin.ttnpdev.fragments.FactorialFragment
import com.kotlin.ttnpdev.fragments.MultiplyTableFragment

class MainActivity : AppCompatActivity() , View.OnClickListener {

    private val tiles = arrayOf<String>(
        "let's learn about fragments",
        "fragment, factorial",
        "fragment, multiply table",
        "fragment, exponent",
        "fragment, car interest"
    )

    private lateinit var textViewTitle : TextView

    private lateinit var bottomFragmentFactorial : AppCompatButton
    private lateinit var bottomFragmentMultiplyTable : AppCompatButton
    private lateinit var bottomFragmentExponent : AppCompatButton
    private lateinit var bottomFragmentCalculateInterest : AppCompatButton
    private lateinit var bottomRemovesFragments : AppCompatButton

    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction

    private fun initialMainActivityWidgets() {
        textViewTitle = findViewById(R.id.textViewTitle)
        bottomFragmentFactorial = findViewById(R.id.bottomFragmentFactorial)
        bottomFragmentMultiplyTable = findViewById(R.id.bottomFragmentMultiplyTable)
        bottomFragmentExponent = findViewById(R.id.bottomFragmentExponent)
        bottomFragmentCalculateInterest = findViewById(R.id.bottomFragmentCalculateInterest)
        bottomRemovesFragments = findViewById(R.id.bottomRemovesFragments)
    }

    private fun reloadTitle () {
        textViewTitle.text = tiles[0]
    }

    private fun loadFragment(fragment: Fragment) { // argument is class Fragment. So any class inherit fragment clas can pass
        // create a FragmentManager
        fragmentManager = supportFragmentManager
        // create a FragmentTransaction to begin the transaction and replace the Fragment
        fragmentTransaction = fragmentManager.beginTransaction()
        // replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.fragmentContainerView,fragment)
        // save the changes
        fragmentTransaction.commit()
    }

    private fun removesFragments() {
        // Create and commit a new transaction
        fragmentManager.commit {
            setReorderingAllowed(true)
            // Replace whatever is in the fragment_container view with this fragment
            replace<Fragment>(R.id.fragmentContainerView)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        initialMainActivityWidgets()
        reloadTitle()
        bottomFragmentFactorial.setOnClickListener(this) ; bottomFragmentMultiplyTable.setOnClickListener(this)
        bottomFragmentExponent.setOnClickListener(this) ; bottomFragmentCalculateInterest.setOnClickListener(this)
        bottomRemovesFragments.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            bottomFragmentFactorial.id -> {
                textViewTitle.text = tiles[1]
                loadFragment(FactorialFragment())
            }
            bottomFragmentMultiplyTable.id -> {
                textViewTitle.text = tiles[2]
                loadFragment(MultiplyTableFragment())
            }
            bottomFragmentExponent.id -> {
                textViewTitle.text = tiles[3]
                loadFragment(ExponentFragment())
            }
            bottomFragmentCalculateInterest.id -> {
                textViewTitle.text = tiles[4]
                loadFragment(CarInterestFragment())
            }
            bottomRemovesFragments.id -> {
                reloadTitle()
                removesFragments()
            }
        }
    }
}