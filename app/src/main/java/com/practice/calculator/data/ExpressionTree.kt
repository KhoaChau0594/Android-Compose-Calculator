package com.practice.calculator.data

import android.graphics.Paint

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
        var result = 0.0



        return result
    }
}