package com.practice.calculator.CalculatorTest

import com.practice.calculator.data.ExpressionTree
import org.junit.Test

class TestExpressionTree {
    @Test
    fun test() {
        var tree = ExpressionTree(arrayListOf("12", "+", "3", "x", "4"))
    }

    @Test
    fun test2() {
        val tree = ExpressionTree(
            arrayListOf("C", "+", "(", "D", "-", "G", ")", "-", "H")//"(", "A", "x", "B", ")", "x",
        )
        println(tree)
    }

    @Test
    fun test3() {
        val tree = ExpressionTree(
            arrayListOf("4", "x", "(", "8", "-", "7", ")")
        )
        println(tree)
    }

    @Test
    fun read_test() {
        val tree = ExpressionTree(
            arrayListOf("(","(", "A", "x", "B", ")", ")","x", "C", "+", "(", "D", "-", "G", ")", "/", "(", "T", "-", "Y", ")")//
        )
        tree.read()
    }

    @Test
    fun compute_test() {

    }
}