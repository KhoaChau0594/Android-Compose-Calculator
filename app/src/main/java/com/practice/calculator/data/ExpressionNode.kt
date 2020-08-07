package com.practice.calculator.data

import com.practice.calculator.delete
import com.practice.calculator.findBiggestParenthesis
import com.practice.calculator.fractionGenerate
import kotlin.math.exp
import kotlin.math.pow


class ExpressionNode(
    expression: ArrayList<String>
) {
    var expression = expression
    var operator: String? = null
    var left: ExpressionNode? = null
    var right: ExpressionNode? = null


    init {
        var temp = findBiggestParenthesis(expression, 0)
        while(temp.first == 0 && temp.second == expression.size) {
            expression.removeAt(0)
            expression.removeAt(expression.lastIndex)
            temp = findBiggestParenthesis(expression, 0)
        }

        if (!expression.isEmpty()) {

            var parentheses: Pair<Int, Int> = Pair(0, 0)
            var isParentheses: Boolean = false
            var index = 0
            var nearestOperator = -1
            while (index < expression.size) {
                if (CalculatorData.secondOperator.indexOf(expression[index]) != -1 ||
                    CalculatorData.firstOperator.indexOf(expression[index]) != -1
                ) {
                    nearestOperator = index
                }

                if (expression[index] == "(") {
                    parentheses = findBiggestParenthesis(expression, index)
                    index = parentheses.second
                    isParentheses = true
                }

                if (isParentheses) {
                    if (nearestOperator > -1 && nearestOperator < parentheses.first) {
                        left = ExpressionNode(
                            ArrayList(
                                expression.subList(
                                    0,
                                    nearestOperator
                                )
                            )
                        ) // delete ()

                        right = ExpressionNode(
                            ArrayList(
                                expression.subList(
                                    parentheses.first,
                                    expression.size
                                )
                            )
                        )

                        operator = expression[nearestOperator]
                        // delete

                        isParentheses = false

                        break
                    } else {
                        left = ExpressionNode(
                            ArrayList(
                                expression.subList(
                                    parentheses.first + 1,
                                    parentheses.second - 1
                                )
                            )
                        ) // delete ()
                        right = ExpressionNode(
                            ArrayList(
                                expression.subList(
                                    parentheses.second + 1,
                                    expression.size
                                )
                            )
                        )
                        operator = expression[index]
                        // delete
//                        expression.delete(parentheses.first, parentheses.second)

                        isParentheses = false

                        break // We only have 2 node so if the while loop do once again we got error =))
                    }
                }
                index++
            }
        }
    }

    fun computeGenerate(): Double{
        if(left != null || right != null){
            val left = left?.computeGenerate() ?: 0.0
            val right = right?.computeGenerate() ?: 0.0
            val operator = operator ?: ""
            return com.practice.calculator.compute(operator, left, right)
        }
        return compute() ?: 0.0
    }

    fun compute(): Double {
        var result: Double = 0.0
        var i = 0
        if (expression.size > 1) {
            val polishNotation = fractionGenerate(expression)
            while (i <= polishNotation.lastIndex) {
                val cValue = polishNotation[i]
                val firstOperator = CalculatorData.firstOperator.indexOf(cValue)
                val secondOperator = CalculatorData.secondOperator.indexOf(cValue)
                if (firstOperator != -1 || secondOperator != -1) {
                    // "3" + "4"
                    val first = if (polishNotation[i - 2] == "π") CalculatorData.PI else polishNotation[i - 2].toDouble()
                    val second = if (polishNotation[i - 1] == "π") CalculatorData.PI else polishNotation[i - 1].toDouble()

                    when (cValue) {
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
                    polishNotation.delete(i - 2, i)
                    polishNotation.add(i - 2, result.toString())
                    i = 0
                } else {
                    i++
                }
            }
            return result
        } else if (expression.size == 1) {
            return if (expression[0] == "π") CalculatorData.PI else expression[0].toDouble()
        }
        return 0.0
    }

    fun isEmpty(): Boolean {
        if (expression.isEmpty() && operator.isNullOrEmpty()) {
            return true
        }
        return false
    }
}

