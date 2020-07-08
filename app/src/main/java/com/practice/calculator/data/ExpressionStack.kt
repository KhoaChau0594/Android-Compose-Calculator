package com.practice.calculator.data

import java.util.*
import kotlin.collections.ArrayList

class ExpressionStack(expression: ArrayList<String>) {
    var expression: ArrayList<String>? = null
    var stack: Stack<ArrayList<ExpressionStack>>? = null

    init {
        create(expression)
    }

    fun create(expression: ArrayList<String>){

    }
}
