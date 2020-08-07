package com.practice.calculator.data

object CalculatorData {
    var expression = ArrayList<String>()
    val firstOperator = arrayListOf<String>("x", "/")
    val secondOperator = arrayListOf<String>("+", "-", "^")
    const val SECOND_OPERATOR = false
    const val FIRST_OPERATOR = true
    const val PI = Math.PI
}

