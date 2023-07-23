package com.pal.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun StarInRoundShape() {


    Canvas(
        modifier = Modifier.fillMaxSize(),
    ) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radius = size.width / 2f - 10.dp.toPx()
        val radiusForSmall = size.width / 2f - 50.dp.toPx()
        val startAngle = -180f
        val sweepAngle = 180f
        // Draw the arc
        drawArc(
            color = Color(0xFFD2EAE2),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = androidx.compose.ui.geometry.Offset(centerX - radiusForSmall, centerY - radiusForSmall),
            size = androidx.compose.ui.geometry.Size(radiusForSmall * 2, radiusForSmall * 2),
            style = Stroke(20f, cap = StrokeCap.Round)
        )

        val starCenterX = centerX
        val starCenterY = centerY
        val starOuterRadius = radiusForSmall
        val starInnerRadius = radiusForSmall
        val starPoints = calculateStarPoints(
            starCenterX,
            starCenterY,
            points = 5,
            innerRadius = starInnerRadius,
            outerRadius = starOuterRadius,
            rotationAngle = 0f
        )

        val dotRadius = 30f // Adjust the dot size as needed

        starPoints.forEach { point ->
            drawCircle(
                color = Color.LightGray,
                center = androidx.compose.ui.geometry.Offset(point.x, point.y),
                radius = dotRadius
            )
        }
    }
}

private fun calculateStarPoints(
    centerX: Float,
    centerY: Float,
    points: Int,
    innerRadius: Float,
    outerRadius: Float,
    rotationAngle: Float = 0f
): List<androidx.compose.ui.geometry.Offset> {
    val angleIncrement = Math.PI * 2 / points
    val starPoints = mutableListOf<androidx.compose.ui.geometry.Offset>()

    var angle = -Math.PI / 2 + rotationAngle.toDouble()
    for (i in 0 until points * 2) {
        val radius = if (i % 2 == 0) outerRadius else innerRadius
        val x = centerX + (radius * cos(angle)).toFloat()
        val y = centerY + (radius * sin(angle)).toFloat()
        starPoints.add(androidx.compose.ui.geometry.Offset(x, y))
        angle += angleIncrement
    }

    return starPoints
}
