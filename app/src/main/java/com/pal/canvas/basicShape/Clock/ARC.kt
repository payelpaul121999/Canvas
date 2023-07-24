


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawArcWithText() {

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        val centerX = size.width / 2f
        val centerY = size.height / 2f
        val radiusForSmall = size.width / 2f - 10.dp.toPx()
       // val radiusForSmall = size.width / 2f - 50.dp.toPx()
        val startAngle = -180f
        val sweepAngle = 180f
        // Draw the arc
        drawArc(
            color = Color(0xFFE2F2ED),
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = true,
            topLeft = Offset(centerX - radiusForSmall, centerY - radiusForSmall),
            size = Size(radiusForSmall * 2, radiusForSmall * 2),
        )

        // Draw the text inside the arc
        val textFirstItem = "TO RETAIN PLATINUM"
        val fontSize = 24.sp // Set the desired font size here


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
            android.graphics.Paint().apply {
                textSize = 18.dp.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
                color = Color(0xFFFF0000).toArgb()
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
                textSize = 14.dp.toPx()
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
                textSize = 18.dp.toPx()
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
                textSize = 16.dp.toPx()
                textAlign = android.graphics.Paint.Align.CENTER
                color = Color(0xFF9D9D9D).toArgb()
            }
        )
    }
}




