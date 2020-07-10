
package com.practice.calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.MutableState
import androidx.compose.state
import androidx.ui.core.Modifier
import androidx.ui.core.setContent
import androidx.ui.core.tag
import androidx.ui.foundation.*
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.MaterialTheme
import androidx.ui.unit.dp
import androidx.ui.unit.sp
import com.practice.calculator.data.CalculatorData
import com.practice.calculator.ui.Config.expressFontSize
import com.practice.calculator.ui.Config.numpadFontSize
import com.practice.calculator.ui.Config.resultFontSize



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TAG RESULT", "Start")

        setContent {
            Calculator()
        }
    }
}

@Composable
fun Calculator() {
    val expressionState: MutableState<String> = state { "" }
    val resultState: MutableState<String> = state { "" }
    ConstraintLayout(
        constraintSet = ConstraintSet {
            tag("toTheRight").apply {
                right constrainTo parent.right
            }
        },
        modifier = Modifier.padding(20.dp).fillMaxHeight()
    ) {
        Column() {
            Box(modifier = Modifier.fillMaxWidth(), gravity = ContentGravity.CenterEnd) {
                ResultScreen(expressionState, resultState)
            }

            Spacer(modifier = Modifier.preferredHeight(36.dp))

            Box(gravity = ContentGravity.BottomCenter) {
                NumPad(expressionState, resultState)
            }
        }
    }
}

@Composable
fun ResultScreen(expression: MutableState<String>, resultState: MutableState<String>) {
    ConstraintLayout(constraintSet = ConstraintSet {
        val exp = tag("expression")
        exp.apply {
            right constrainTo parent.right
            top constrainTo parent.top
        }
        val result = tag("expression")
        tag("result").apply {
            top constrainTo exp.bottom
            right constrainTo parent.right
        }
    }) {
        Text(
            text = expressionProcessor(expression.value),
            style = MaterialTheme.typography.subtitle1,
            softWrap = true,
            modifier = Modifier.tag("expression"),
            fontSize = expressFontSize,
            color = Color(0xFF93969A)
        )

        Spacer(modifier = Modifier.preferredHeight(16.dp))

        Text(
            text = resultState.value,
            modifier = Modifier.tag("result"),
            fontSize = resultFontSize
        )

        Spacer(modifier = Modifier.preferredHeight(32.dp))
    }
}

@Composable
fun NumPad(expression: MutableState<String>, resultState: MutableState<String>) {
    var currentNumber = ""
    val btnModifier = Modifier
//        .preferredHeight(70.dp)
//        .preferredWidth(70.dp)
    val btnShape = RoundedCornerShape(30.dp)
    val btnModifier2 = Modifier
    var modifing = false
//        .preferredHeight(50.dp)
//        .preferredWidth(70.dp)

    fun onClick(c: String, isOperator: Boolean = false) {

        if (c == "=") { // "=" was clicked
            if(currentNumber != "") CalculatorData.expression.add(currentNumber)
            currentNumber = ""
            modifing = true
            return
        }

        if(modifing){// if "=" was clicked we can add number to previous num
            Log.d("debug","Go into modifired")
            if(CalculatorData.expression.isNotEmpty()){
                currentNumber = CalculatorData.expression[CalculatorData.expression.lastIndex]
                CalculatorData.expression.removeAt(CalculatorData.expression.lastIndex)
            }

            modifing = false // turn the "flag" off
        }

        expression.value += c

        if (isOperator) {
            if(currentNumber != "") CalculatorData.expression.add(currentNumber)
            if(c != "") CalculatorData.expression.add(c.trim())
            currentNumber = ""
        } else {
            currentNumber += c
        }

        Log.d("debug",currentNumber)
        Log.d("debug",CalculatorData.expression.toString() )
        Log.d("debug",modifing.toString())
    }

    fun clearData(){
        Log.d("debug",currentNumber)
        Log.d("debug",CalculatorData.expression.toString() )
        Log.d("debug",modifing.toString())

        expression.value = ""
        resultState.value = ""
        CalculatorData.expression.clear()
        currentNumber = ""
    }

    /** First row */
    ConstraintLayout(
        constraintSet = ConstraintSet {
            val text1 = tag("tag1").apply {
                left constrainTo parent.left
            }

            val text2 = tag("tag2").apply {
                left constrainTo text1.right
            }

            val text3 = tag("tag3").apply {
                left constrainTo text2.right
            }

            val text4 = tag("tag4").apply {
                right constrainTo parent.right
            }

            createHorizontalChain(
                *arrayOf(text1, text2, text3, text4),
                chainStyle = ConstraintSetBuilderScope.ChainStyle.SpreadInside
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                onClick("e")
                Log.d("TAG RESULT", expression.value)
            },
            modifier = btnModifier2.tag("tag1"),
            shape = btnShape,
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            border = Border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            Text(text = "e", fontSize = 20.sp)
        }
        Button(
            onClick = { onClick("π") },
            modifier = btnModifier2.tag("tag2"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "π", fontSize = 20.sp)
        }
        Button(
            onClick = { onClick("^", true) },
            modifier = btnModifier2.tag("tag3"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "^", fontSize = 20.sp)
        }
        Button(
            onClick = { onClick("deg") },
            modifier = btnModifier2.tag("tag4"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "deg", fontSize = 20.sp)
        }
    }

    Spacer(modifier = Modifier.preferredHeight(16.dp))

    /**Second row*/
    ConstraintLayout(
        constraintSet = ConstraintSet {
            val text1 = tag("tag1").apply {
                left constrainTo parent.left
            }

            val text2 = tag("tag2").apply {
                left constrainTo text1.right
            }

            val text3 = tag("tag3").apply {
                left constrainTo text2.right
            }

            val text4 = tag("tag4").apply {
                right constrainTo parent.right
            }

            createHorizontalChain(
                *arrayOf(text1, text2, text3, text4),
                chainStyle = ConstraintSetBuilderScope.ChainStyle.SpreadInside
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                clearData()
            },
            modifier = btnModifier.tag("tag1"),
            shape = btnShape,
            backgroundColor = Color(0xFFF8ECED),
            contentColor = Color(0xFFF64E57),
            border = Border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            Text(text = "C", fontSize = numpadFontSize)
        }
        Button(
            onClick = {
                onClick("(", true)
            },
            modifier = btnModifier.tag("tag2"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "(", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick(")", true) },
            modifier = btnModifier.tag("tag3"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = ")", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick(" / ", true) },
            modifier = btnModifier.tag("tag4"),
            backgroundColor = Color(0xFFFF9500),
            contentColor = Color.White,
            shape = btnShape
        ) {
            Text(text = "/", fontSize = numpadFontSize)
        }
    }

    Spacer(modifier = Modifier.preferredHeight(16.dp))

    /**Third row*/
    ConstraintLayout(
        constraintSet = ConstraintSet {
            val text1 = tag("tag1").apply {
                left constrainTo parent.left
            }

            val text2 = tag("tag2").apply {
                left constrainTo text1.right
            }

            val text3 = tag("tag3").apply {
                left constrainTo text2.right
            }

            val text4 = tag("tag4").apply {
                right constrainTo parent.right
            }

            createHorizontalChain(
                *arrayOf(text1, text2, text3, text4),
                chainStyle = ConstraintSetBuilderScope.ChainStyle.SpreadInside
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { onClick("7") },
            modifier = btnModifier.tag("tag1"),
            shape = btnShape,
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            border = Border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            Text(text = "7", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick("8") },
            modifier = btnModifier.tag("tag2"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "8", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick("9") },
            modifier = btnModifier.tag("tag3"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "9", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick(" x ", true) },
            modifier = btnModifier.tag("tag4"),
            backgroundColor = Color(0xFFFF9500),
            contentColor = Color.White,
            shape = btnShape
        ) {
            Text(text = "x", fontSize = numpadFontSize)
        }
    }

    Spacer(modifier = Modifier.preferredHeight(16.dp))

    /**Forth row*/
    ConstraintLayout(
        constraintSet = ConstraintSet {
            val text1 = tag("tag1").apply {
                left constrainTo parent.left
            }

            val text2 = tag("tag2").apply {
                left constrainTo text1.right
            }

            val text3 = tag("tag3").apply {
                left constrainTo text2.right
            }

            val text4 = tag("tag4").apply {
                right constrainTo parent.right
            }

            createHorizontalChain(
                *arrayOf(text1, text2, text3, text4),
                chainStyle = ConstraintSetBuilderScope.ChainStyle.SpreadInside
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { onClick("4") },
            modifier = btnModifier.tag("tag1"),
            shape = btnShape,
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            border = Border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            Text(text = "4", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick("5") },
            modifier = btnModifier.tag("tag2"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "5", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick("6") },
            modifier = btnModifier.tag("tag3"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "6", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick(" - ", true) },
            modifier = btnModifier.tag("tag4"),
            backgroundColor = Color(0xFFFF9500),
            contentColor = Color.White,
            shape = btnShape
        ) {
            Text(text = "-", fontSize = numpadFontSize)
        }
    }

    Spacer(modifier = Modifier.preferredHeight(16.dp))

    /** Forth row*/
    ConstraintLayout(
        constraintSet = ConstraintSet {
            val text1 = tag("tag1").apply {
                left constrainTo parent.left
            }

            val text2 = tag("tag2").apply {
                left constrainTo text1.right
            }

            val text3 = tag("tag3").apply {
                left constrainTo text2.right
            }

            val text4 = tag("tag4").apply {
                right constrainTo parent.right
            }

            val spacer = tag("spacer").apply {
                top constrainTo text1.bottom
            }

            val text5 = tag("tag5").apply {
                top constrainTo spacer.bottom
                bottom constrainTo tag("tag6").bottom
                left constrainTo parent.left
                right constrainTo text2.right
                width = spread
            }

            tag("tag6").apply {
                left constrainTo text3.left
                right constrainTo text3.right
                top constrainTo text5.top
            }

            tag("tag7").apply {
                right constrainTo parent.right
                top constrainTo text5.top
            }

            createHorizontalChain(
                *arrayOf(text1, text2, text3, text4),
                chainStyle = ConstraintSetBuilderScope.ChainStyle.SpreadInside
            )
        },
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Button(
            onClick = {
                onClick("1")
            },
            modifier = btnModifier.tag("tag1"),
            shape = btnShape,
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            border = Border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            Text(text = "1", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick("2") },
            modifier = btnModifier.tag("tag2"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "2", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick("3") },
            modifier = btnModifier.tag("tag3"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = "3", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick(" + ", true) },
            modifier = btnModifier.tag("tag4"),
            backgroundColor = Color(0xFFFF9500),
            contentColor = Color.White,
            shape = btnShape
        ) {
            Text(text = "+", fontSize = numpadFontSize)
        }

        /** Fifth row */
        Spacer(modifier = Modifier.preferredHeight(16.dp).tag("spacer"))

        Button(
            onClick = { onClick("0") },
            modifier = Modifier.tag("tag5"),
            shape = btnShape,
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            border = Border(0.dp, Color.Transparent),
            elevation = 0.dp
        ) {
            Text(text = "0", fontSize = numpadFontSize)
        }
        Button(
            onClick = { onClick(".") },
            modifier = btnModifier.tag("tag6"),
            backgroundColor = Color(0xFFE9F0F4),
            contentColor = Color(0xFF4C5159),
            shape = btnShape
        ) {
            Text(text = ".", fontSize = numpadFontSize)
        }
        Button(
            onClick = {
                onClick("=", true)
                resultState.value = compute()
                Log.d("DATA", CalculatorData.expression.toString())
            },
            modifier = btnModifier.tag("tag7"),
            backgroundColor = Color(0xFF2EC973),
            contentColor = Color.White,
            shape = btnShape
        ) {
            Text(text = "=", fontSize = numpadFontSize)
        }
    }

    Spacer(modifier = Modifier.preferredHeight(16.dp))
}