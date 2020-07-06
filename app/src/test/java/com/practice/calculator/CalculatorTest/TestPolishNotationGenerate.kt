package com.practice.calculator.CalculatorTest

import com.practice.calculator.CalculatorData
import com.practice.calculator.fractionGenerate
import com.practice.calculator.polishNotationGenerate
import org.junit.Assert
import org.junit.Test

class TestPolishNotationGenerate {
    private fun test(expressions: ArrayList<String>, expected: ArrayList<String>) {
        println("test case: $expressions")
        val result = fractionGenerate(expressions)
        println("result: $result")
        Assert.assertEquals("Oops", expected, result)
        println("-------------------------------------------------------")
    }

    @Test
    fun test1() {
        val array = arrayListOf<String>("A", "+", "B")
        test(array, arrayListOf("A", "B", "+"))
    }

    @Test
    fun test2() {
        val array = arrayListOf<String>("A", "+", "B", "+", "C", "-", "D")
        test(array, arrayListOf("A", "B", "+", "C", "+", "D", "-"))
    }

    @Test
    fun test3() {
        val array = arrayListOf<String>("A", "x", "B")
        test(array, arrayListOf("A", "B", "x"))
    }

    @Test
    fun test4() {
        val array = arrayListOf<String>("A", "x", "B", "x", "C")
        test(array, arrayListOf("A", "B", "x", "C", "x"))
    }

    @Test
    fun combine_test() {
        val array = arrayListOf<String>("A", "+", "B", "x", "C")//
        test(array, arrayListOf("A", "B", "C", "x", "+"))//
    }

    @Test
    fun combine_test2() {
        val array = arrayListOf<String>("A", "+", "B", "x", "C", "+", "D")//
        test(array, arrayListOf("A", "B", "C", "x", "+", "D", "+"))//
    }

    @Test
    fun combine_test3() {
        val array = arrayListOf<String>("A", "+", "B", "x", "C", "+", "D", "-", "E")//
        test(array, arrayListOf("A", "B", "C", "x", "+", "D", "+", "E", "-"))//
    }

    @Test
    fun combine_test4() {
        val array = arrayListOf<String>("A", "+", "B", "x", "C", "+", "D", "-", "E", "x", "F")//
        test(array, arrayListOf("A", "B", "C", "x", "+", "D", "+", "E", "F", "x", "-"))//
    }
//    @Test
//    fun test2() {
//        val array = arrayListOf<String>("(", "12", "+", "3", ")", "/", "(", "3", "x", "2", ")")
//        test(array, arrayListOf("12", "3", "+", "3", "2", "x", "/"))
//    }
//
//    @Test
//    fun test3() {
//        val array = arrayListOf<String>(
//            "(",
//            "12",
//            "+",
//            "3",
//            ")",
//            "/",
//            "(3",
//            "x",
//            "2",
//            ")",
//            "+",
//            "(",
//            "2",
//            "+",
//            "10",
//            ")"
//        )
//        test(array, arrayListOf("12", "3", "+", "3", "2", "x", "/", "2", "10", "+", "+"))
//    }
}