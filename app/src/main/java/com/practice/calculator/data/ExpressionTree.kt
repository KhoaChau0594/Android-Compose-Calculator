package com.practice.calculator.data

import android.graphics.Paint
import com.practice.calculator.compute

class ExpressionTree(expression: ArrayList<String>) {
    var node: ExpressionNode? = null
    init {
        create(expression)
    }

    fun create(expression: ArrayList<String>) {
        node = ExpressionNode(expression)
    }

    fun read(){
        read(node)
    }

    fun read(node: ExpressionNode?){
        if(node?.left != null){
            read(node.left)
        }
        if(node?.right != null){
            read(node.right)
        }
        println("operator: ${node?.operator};  expression: ${node?.expression}")
    }

    fun compute(): Double{
        return compute(node)
    }

    private fun compute(node: ExpressionNode?): Double{
        if(node?.left != null && node?.right != null){
            val left = node.left?.compute() ?: 0.0
            val right = node.right?.compute() ?: 0.0
            val operator = node.operator ?: ""
            return compute(operator, left, right)
        }
        return node?.compute() ?: 0.0
    }
}