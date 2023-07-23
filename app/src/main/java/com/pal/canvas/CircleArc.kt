package com.pal.canvas

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/*final code*/
@Composable
fun Semicircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radius = size.width / 2f - 10.dp.toPx()
        val radiusForSmall = size.width / 2f - 50.dp.toPx()
        val startAngle = -180f
        val sweepAngle = 180f
        drawArc(
            color = Color(0xFFD2EAE2),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(20f)
        )
        drawArc(
            color = Color(0xFF429587),
            startAngle = startAngle,
            sweepAngle = 105f,
            useCenter = false,
            topLeft = Offset(centerX - radius, centerY - radius),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(36f, cap = StrokeCap.Round)
        )
        drawArc(
            color = Color(0xFFE2F2ED),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(centerX - radiusForSmall, centerY - radiusForSmall),
            size = androidx.compose.ui.geometry.Size(radiusForSmall * 2, radiusForSmall * 2),
        )
        drawArc(
            color = Color(0xFFD2EAE2),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(centerX - radiusForSmall, centerY - radiusForSmall),
            size = androidx.compose.ui.geometry.Size(radiusForSmall * 2, radiusForSmall * 2),
            style = Stroke(8f, cap = StrokeCap.Round)
        )
        // Draw the 5 dot points on the arc
        val dotRadius = 35f
        val angleStep = sweepAngle / 4
        for (i in 0..4) {
            val angle = startAngle + angleStep * i
            val x = center.x + radius * cos(Math.toRadians(angle.toDouble())).toFloat()
            val y = center.y + radius * sin(Math.toRadians(angle.toDouble())).toFloat()

            drawCircle(
                color = Color(0xFF899289),
                center = Offset(x, y),
                radius = dotRadius
            )


        }

    }
}



