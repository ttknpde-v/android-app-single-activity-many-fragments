package com.kotlin.ttnpdev.objects

object MyMaths {
    fun getFactorial(number: Int) : Number {
        var factorial = 1
        if (number == 0) {
            return factorial
        }
        else if (number > 0) {
            for (i in 1 .. number) {
                factorial *= i
            }
        }
        return factorial
    }
    fun getExponent (base: Int , expo : Int) : Int {
        var exponent = 0

        if (base == 0 && expo != 0) { // 0 and > 0

            return base

        } else if (base != 0 && expo == 0) { // 2 and > 0

            return 1

        }  else if (base == 0 && expo == 0) { //  0 and 0

            return -8888888

        }

        exponent = base
        for (i in 1 until  expo) {
            exponent *= base
        }

        return exponent
    }

    /*
    fun getMultiplyTable(table : Int) : ArrayList<String> {
        val multiplyTable = ArrayList<String>()
        for (i in 1 .. 4 ) {
            multiplyTable.add("$table x $i = ${table*i}")
        }
        return multiplyTable
    }
    */

    fun getMultiplyTable2(table : Int) : Array<String> {
        val multiplyTable = Array<String>(4) {""} // initial array has size 4 and all elements be ""
        for (i in 0 .. 3 ) {
            multiplyTable[i] = "$table x ${i+1} = ${table*(i+1)}"
        }
        return multiplyTable
    }
}
/*
fun main() {
    val multiplyTable = Array<String>(4) {""}
    for (i in 0 .. 3 ) {
        multiplyTable[i] = "$i x $i = ${i*i}"
    }
    var clone: Array<String>? = null
    clone = MyMaths.getMultiplyTable2(3)
    println(clone[0])
    println(clone[1])

}*/