package com.practice.calculator

import androidx.ui.graphics.Color
import androidx.ui.text.AnnotatedString
import androidx.ui.text.SpanStyle
import androidx.ui.text.annotatedString
import com.practice.calculator.data.CalculatorData
import com.practice.calculator.data.CalculatorData.FIRSTOPERATOR
import com.practice.calculator.data.CalculatorData.SECOND_OPERATOR
import com.practice.calculator.data.ExpressionTree
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow

sealed class Data {
    class Expression(exp: String)
    class Opera
}

fun expressionProcessor(str: String): AnnotatedString {
    return annotatedString {
        val operationArr: MutableList<Int> = ArrayList<Int>()
        str.forEachIndexed { index, c ->
            if (c == '+' || c == '-' || c == 'x' || c == '/') {
                operationArr.add(index)
            }
        }

        append(str)
        for (i in operationArr) {
            addStyle(
                style = SpanStyle(Color(0xFFFACB85)),
                start = i,
                end = i + 1
            )
        }
    }
}

fun compute(): String {
    return ExpressionTree(CalculatorData.expression).compute().toString()
}

fun polishNotationGenerate(): ArrayList<String> {
    val array = ArrayList<String>()

    return array
}

fun fractionGenerate(expression: ArrayList<String>): ArrayList<String> {
    val array = ArrayList<String>()
    var isFirstOperator = false
    var isSecondOperator = false
    val operatorStack = Stack<Boolean>() // true -> first, false -> second

    // Make this be right for the simplest cases(without parenthesis)
    for (i in expression) {
        val firstOperator = CalculatorData.firstOperator.indexOf(i)
        val secondOperator = CalculatorData.secondOperator.indexOf(i)
        // Check operator
        when {
            firstOperator != -1 -> { // "x", "/"
                if (operatorStack.isNotEmpty() && operatorStack.peek() == SECOND_OPERATOR) { // check if there is second operator in stack
                    array.add(array.lastIndex, i)
                } else {
                    array.add(i)
                }
                isFirstOperator = true
            }
            secondOperator != -1 -> { //"+", "-"
                array.add(i)
                isSecondOperator = true
                isFirstOperator = false
                if (operatorStack.isNotEmpty()) {
                    operatorStack.pop()
                }
            }
            else -> {
                if (isSecondOperator) {
                    array.add(array.lastIndex, i)

                    operatorStack.push(SECOND_OPERATOR) // save operator to stack
                    isSecondOperator = false
                } else if (isFirstOperator) {
                    if (operatorStack.isNotEmpty() && operatorStack.peek() == SECOND_OPERATOR) { // last operator is seconde operator
                        array.add(array.lastIndex - operatorStack.size, i)
                    } else {
                        array.add(array.lastIndex, i)

                        operatorStack.push(FIRSTOPERATOR) // save operator to stack
                        isFirstOperator = false
                    }
                } else {
                    array.add(i)
                }
            }
        }
    }

    return array
}


fun findBiggestParenthesis(expression: ArrayList<String>, from: Int): Pair<Int, Int> {
    var numOfOpen = 1
    var numOfClose = 0
    if (expression[from] == "(") {
        for (i in from..expression.lastIndex) {
            if (expression[i] == "(") {
                numOfOpen++
            } else if (expression[i] == ")") {
                numOfClose++
                if (numOfOpen - numOfClose <= 1) {
                    return Pair(from, i + 1)
                }
            }
        }
    }
    return Pair(-1, -1)
}

fun <E> ArrayList<E>.delete(from: Int, to: Int) {
    for (i in from..to) {
        this.removeAt(from)
    }
}

fun compute(operator: String, first: Double, second: Double):Double{
    var result = 0.0
    when (operator) {
        "+" -> {
            result = first + second
        }
        "-" -> {
            result = first - second
        }
        "x" -> {
            result = first * second
        }
        "/" -> {
            result = first / second
        }
        "^" -> {
            result = first.pow(second)
        }
    }

    return result
}