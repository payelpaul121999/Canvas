package com.pal.canvas.basicShape

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlin.math.cos
import kotlin.math.sin



data class Dot(val x: Float, val y: Float)

@Composable
fun MyApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),

    ) {
        // Use the ViewModel to get the star points
        val viewModel: MyViewModel = viewModel()
        val starPoints = viewModel.starPoints

        DrawDotsInArc(starPoints)
    }
}

@Composable
fun DrawDotsInArc(starPoints: List<Offset>) {
    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val dotRadius = 8f

        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.width / 3

        val angleStep = 360f / starPoints.size

        starPoints.forEachIndexed { index, point ->
            val angle = angleStep * index
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble())).toFloat()

            drawCircle(
                color = Color.LightGray,
                center = Offset(x, y),
                radius = dotRadius
            )
        }
    }
}

class MyViewModel : ViewModel() {
    val starPoints = listOf(
        Offset(0f, 0f),
        Offset(100f, 50f),
        Offset(150f, 150f),
        Offset(50f, 150f),
        Offset(0f, 50f)
    )
}





