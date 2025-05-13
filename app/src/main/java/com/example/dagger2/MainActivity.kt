package com.example.dagger2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import com.example.dagger2.ui.theme.Dagger2Theme
import com.example.mathview.MathFormulaView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Dagger2Theme {

            }
        }
    }
}

@Composable
fun App() {
    MathFormulaView(
        latex = "\\frac{a}{b} = \\sqrt{x^2 + y^2}",
        fontSize = 20f,
        textColor = "#000000",
        displayMode = true
    )
}