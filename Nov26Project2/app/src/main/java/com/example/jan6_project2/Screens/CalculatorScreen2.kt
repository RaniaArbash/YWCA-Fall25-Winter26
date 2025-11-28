package com.example.jan6_project2.Screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun KeyPad2(keyClicked: (String) -> Unit) {
    // 2D list defining the button layout (4 rows x 4 columns)
    // Each inner list is one row of buttons
    val buttons = listOf(
        listOf("1", "2", "3", "+"),  // First row
        listOf("4", "5", "6", "-"),  // Second row
        listOf("7", "8", "9", "*"),  // Third row
        listOf("C", "0", "=", "/")   // Fourth row
    )

    // Column arranges button rows vertically
    Column(
        modifier = Modifier.Companion.fillMaxWidth(),
        horizontalAlignment = Alignment.Companion.CenterHorizontally
    ) {
        // Loop through each row of buttons
        buttons.forEach { row ->
            // Row arranges buttons horizontally
            Row(
                modifier = Modifier.Companion.padding(4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)  // 8dp space between buttons
            ) {
                // Loop through each button in the current row
                row.forEach { label ->
                    // Create a button for each label
                    Button(onClick = { keyClicked(label) }) {  // Call keyClicked when pressed
                        Text(label)  // Display the button's label (number or operator)
                    }
                }
            }
        }
    }
}
/**
 * Main calculator screen component that displays a text output and keypad.
 *
 * This composable function creates a complete calculator UI with:
 * - A display area showing the current calculation or result
 * - A keypad with numbers (0-9), operators (+, -, *, /), clear (C), and equals (=)
 *
 * The calculator supports basic arithmetic operations and evaluates expressions
 * when the equals button is pressed.
 */
@Composable
fun CalculatorScreen2() { //Pierrette
    // State variable to store the calculation string (e.g., "5 + 3")
    // 'remember' keeps this value alive when the screen redraws
    // 'by' allows us to use operationString directly without .value
    var operationString by remember { mutableStateOf("") }
    // Column arranges items vertically (display on top, keypad below)

    // Track if the last operation was '=' so we can start fresh on next input
    var lastOperatorWasEqual by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()        // Take up the entire screen
            .padding(16.dp), // Add space around the edges,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display text showing the current calculation
        Text("Pierrette's Calculator", fontSize = 20.sp)

        Text(
            text = operationString.ifEmpty { "0" },  // Show "0" if nothing entered yet
            fontSize = 24.sp,                         // Large text for readability
            modifier = Modifier.padding(bottom = 16.dp)  // Space below the display
        )

        // The calculator keypad with button click handling
        KeyPad2(
            keyClicked = { key ->  // This function runs when any button is pressed
                // Update the display based on which button was pressed
                when (key) {
                    "C" -> {
                        // Clear button: reset to empty
                        operationString = ""
                        lastOperatorWasEqual = false
                    }

                    "=" -> {
                        // Equals: calculate result and mark that we just calculated
                        operationString =
                            evaluateExpression(operationString)  // Equals: calculate result
                        lastOperatorWasEqual = true
                    }
                    "+", "-", "*", "/" -> {
                        // If we just pressed '=', start fresh with the result and operator
                        if (lastOperatorWasEqual) {
                            lastOperatorWasEqual = false
                            operationString = "$operationString $key "  // Keep result, add operator with trailing space
                        } else {
                            operationString = "$operationString $key "  // Add operator with spaces
                        }
                    }
                    else -> {
                        // Numbers: append without space
                        // If we just pressed '=', start a new expression instead of appending
                        if (lastOperatorWasEqual) {
                            lastOperatorWasEqual = false
                            operationString = key // Start a new expression
                        } else {
                            operationString = if(operationString == "0") key else "$operationString$key" // direct append without space
                        }
                    }
                }
            }
        )
    }
}

private fun evaluateExpression(expression: String): String {
    return try {
        // Split the expression by spaces and remove empty strings
        // "5 + 3" becomes ["5", "+", "3"]
        val tokens = expression.trim().split(" ").filter { it.isNotEmpty() }

        // Need at least 3 tokens for a valid expression (number operator number)
        if (tokens.size < 3) return expression

        // Start with the first number as our result
        var result = tokens[0].toDouble()

        // Index to track position in tokens list
        var i = 1

        // Loop through operators and operands (step by 2 each time)
        while (i < tokens.size - 1) {
            val operator = tokens[i]        // Get the operator (+, -, *, /)
            val operand = tokens[i + 1].toDouble()  // Get the next number

            // Perform the calculation based on the operator
            result = when (operator) {
                "+" -> result + operand      // Addition
                "-" -> result - operand      // Subtraction
                "*" -> result * operand      // Multiplication
                "/" -> if (operand != 0.0) result / operand else return "Error"  // Division (check for divide by zero)
                else -> return expression    // Unknown operator, return original
            }

            i += 2  // Move to next operator (skip the number we just used)
        }

        // Format the result: if it's a whole number, remove the decimal
        // 5.0 becomes "5", but 5.5 stays "5.5"
        if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()

    } catch (_: Exception) {
        // If anything goes wrong (invalid number, etc.), return "Error"
        // NOTE: The error should be handled properly here. I am just returning an error string for simplicity
        "Error"
    }
}


