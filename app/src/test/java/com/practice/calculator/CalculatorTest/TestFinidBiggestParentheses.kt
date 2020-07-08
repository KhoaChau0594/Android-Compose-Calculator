package com.practice.calculator.CalculatorTest

import com.practice.calculator.findBiggestParenthesis
import org.junit.Assert
import org.junit.Test

class TestFinidBiggestParentheses {
    @Test
    fun test() {
        Assert.assertEquals(
            Pair(0, 4),
            findBiggestParenthesis(arrayListOf("(", "1", "+", "2", ")"), 0)
        )
    }

    @Test
    fun test2() {
        Assert.assertEquals(
            Pair(0, 12),
            findBiggestParenthesis(
                arrayListOf("(", "(", "1", "+", "2", ")", "+", "(", "1", "+", "2", ")", ")"),
                0
            )
        )
    }

    @Test
    fun test3() {
        Assert.assertEquals(
            Pair(0, 10),
            findBiggestParenthesis(
                arrayListOf("(", "(", "1", "+", "2", ")", "+", "5", "^", "2", ")"),
                0
            )
        )
    }
}