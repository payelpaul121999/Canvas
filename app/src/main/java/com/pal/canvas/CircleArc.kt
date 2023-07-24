package com.pal.canvas

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.withRotation
import com.pal.canvas.basicShape.LineType
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin

/*final code*/
@Composable
fun Semicircle() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
       // val radius = size.width / 2f - 10.dp.toPx()
        // val radiusForSmall = size.width / 2f - 50.dp.toPx()
        val radius = size.width / 2f - 10.dp.toPx()

        val radiusForSmall = size.width / 2f - 40.dp.toPx()
        val radiusForSmallNumber = size.width / 2f - 37.dp.toPx()
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
        val angleStep2 = sweepAngle / 5
        for (i in 0..5){
            val angle = startAngle + angleStep2 * i
            drawContext.canvas.nativeCanvas.apply {
                val x1 = center.x + radiusForSmallNumber * cos(Math.toRadians(angle.toDouble())).toFloat()
                val y1 = center.y + radiusForSmallNumber * sin(Math.toRadians(angle.toDouble())).toFloat()
                drawText(abs(i).toString(),x1,y1, Paint().apply {
                    textSize = 15.dp.toPx()
                    textAlign = Paint.Align.CENTER
                })
            }
        }

        // Calculate the coordinates of the triangle's vertices at -90 degree position
        val angleInRadians = Math.toRadians((-100).toDouble()).toFloat()
        val x1 = centerX + radius * cos(angleInRadians).toFloat()
        val y1 = centerY + radius * sin(angleInRadians).toFloat()
        val x2 = x1 + 80f // Adjust the size of the triangle as needed
        val y2 = y1
        val x3 = centerX
        val y3 = centerY - radius + 100f
        // Adjust the distance between the arc and the triangle

        // Create a Path object to define the triangle
        val trianglePath = Path()
        trianglePath.moveTo(x1, y1)
        trianglePath.close()
        trianglePath.lineTo(x3, y3)
        trianglePath.lineTo(x2, y2)



        // Draw the filled triangle
        drawPath(
            path = trianglePath,
            color = Color.Blue
        )
        // Draw the text inside the arc
        val textFirstItem = "TO RETAIN PLATINUM"
        val fontSize = 20.sp
        // Set the desired font size here


        // Calculate the center position of the arc
        val arcCenterX = centerX
        val arcCenterYFirstItem = centerY - (radiusForSmall / 2) - 120f


        val textX = arcCenterX - (25f / 2)
        val textY = arcCenterYFirstItem + (fontSize.toPx() / 2)

        // Draw the text on the canvas First Item
        drawContext.canvas.nativeCanvas.drawText(
            textFirstItem,
            textX + 20f,
            textY,
            Paint().apply {
                textSize = 14.dp.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
                color = Color(0xFF2F2F2F).toArgb()

            }
        )
        // Draw the text on the canvas 2nd Item
        val textSecItem = "You require the following or more:"
        val arcCenterY2ndItem = centerY - (radiusForSmall / 2) - 60f
        val text2Y = arcCenterY2ndItem + (fontSize.toPx() / 2)
        drawContext.canvas.nativeCanvas.drawText(
            textSecItem,
            textX,
            text2Y ,
            android.graphics.Paint().apply {
                textSize = 11.dp.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
                color = Color(0xFF70968D).toArgb()
            }
        )

        // Draw the text on the canvas 2nd Item
        val text3rdItem = "Nights: 10 or Spends: ₹ 50,000"

        val arcCenterY3rdItem = centerY - (radiusForSmall / 4) - 50f
        val text3Y = arcCenterY3rdItem + (fontSize.toPx() / 2)
        drawContext.canvas.nativeCanvas.drawText(
            text3rdItem,
            textX,
            text3Y ,
            android.graphics.Paint().apply {
                textSize = 14.dp.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
                color = Color(0xFF2F2F2F).toArgb()
            }
        )


        // Draw the text on the canvas 2nd Item
        val text4thItem = "by 31st December 2023"

        val arcCenterY4thItem = centerY - (radiusForSmall / 10) - 20f
        val text4Y = arcCenterY4thItem + (fontSize.toPx() / 2)
        drawContext.canvas.nativeCanvas.drawText(
            text4thItem,
            textX,
            text4Y ,
            android.graphics.Paint().apply {
                textSize = 12.dp.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
                color = Color(0xFF9D9D9D).toArgb()
            }
        )

    }
}





/*drawContext.canvas.nativeCanvas.apply{
    val textRadius = radius - 5.dp.toPx()
    val x = textRadius * cos(60f) + center.x
    val y = textRadius * sin(60f) + center.y

    withRotation(
        degrees = 20f  * (180f / PI.toFloat()) + 90f,
        pivotX = x,
        pivotY = y
    ){
        drawPath(
            path = trianglePath,
            color = Color.Blue
        )
    }
}*/



