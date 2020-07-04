package com.practice.calculator

import androidx.ui.graphics.Color
import androidx.ui.text.AnnotatedString
import androidx.ui.text.SpanStyle
import androidx.ui.text.annotatedString
import java.util.*
import kotlin.collections.ArrayList

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
    return polishNotationGenerate().toString()
}

fun polishNotationGenerate(): ArrayList<String> {
    var array = ArrayList<String>()
    var isParenthesis = false
    var parenthesis = Stack<Boolean>()
    var isOperation = false
    var parenthesisIndex = 0
    for (str in CalculatorData.expression) {
        if (str == "(") {
            parenthesis.add(true)
            isParenthesis = true
            parenthesisIndex = str.lastIndex
        } else if (str == ")") {
            isParenthesis = false
        } else {
            if (str == "+" || str == "-" || str == "x" || str == "/" || str == "^") {
                array.add(str)
                isOperation = true
                continue
            }

            // produce the proper index
            if (isParenthesis) {
                if (isOperation) {
                    parenthesisIndex--
                }
            }

            if (isOperation) {
                var index = 0
                if (isParenthesis) {
                    index = array.lastIndex -1
                } else {
                    index = array.lastIndex
                }
                array.add(array.last())
                array[index] = str

            } else {
                array.add(str)
            }
            parenthesisIndex++
        }
    }

    return array
}
