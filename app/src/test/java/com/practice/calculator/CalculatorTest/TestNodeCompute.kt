package com.practice.calculator.CalculatorTest

import com.practice.calculator.data.ExpressionNode
import org.junit.Assert
import org.junit.Test

class TestNodeCompute {
    private fun test(expected: Double, expression: ArrayList<String>) {
        val node = ExpressionNode(expression = expression)
        val res = node.compute()
        println("result: $res")
        println("expected: $expected")
        Assert.assertEquals(expected, res, 0.0)
    }

    @Test
    fun test1() {
        test(
            3.0,
            arrayListOf("1", "+", "2")
        )
    }

    @Test
    fun test2() {
        test(
            15.0,
            arrayListOf("12", "+", "3")
        )
    }

    @Test
    fun test3() {
        test(
            24.0,
            arrayListOf("12", "+", "3", "x", "4")
        )
    }

    @Test
    fun test4() {
        test(
            40.0,
            arrayListOf( "12", "x", "3", "+", "4")
        )
    }
}