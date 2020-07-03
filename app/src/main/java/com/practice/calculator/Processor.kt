package com.practice.calculator

import android.util.Log
import android.widget.Toast
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

fun compute(expression: String): String {
    return ""
}

fun polishNotationGenerate(expression: String): Stack<String> {
    var operation = ArrayList<Boolean>()
    var parenthesis = ArrayList<Boolean>()

//    for(i in expression){
//        if
//    }

    return Stack()
}
