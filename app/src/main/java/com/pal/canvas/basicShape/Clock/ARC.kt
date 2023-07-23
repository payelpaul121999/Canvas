import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin


@Composable
fun DrawArcWithTriangle() {
    Canvas(
        modifier = Modifier.fillMaxSize()

    ) {


        // Optionally, you can add a stroke to the triangle


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
            topLeft = Offset(centerX - radius, centerY - radius),
            size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
            style = Stroke(20f)
        )

        // Calculate the coordinates of the triangle's vertices at -90 degree position
        val angleInRadians = Math.toRadians((-100).toDouble()).toFloat()
        val x1 = centerX + radius * cos(angleInRadians).toFloat()
        val y1 = centerY + radius * sin(angleInRadians).toFloat()
        val x2 = x1 + 80f // Adjust the size of the triangle as needed
        val y2 = y1
        val x3 = centerX
        val y3 = centerY - radius + 80f // Adjust the distance between the arc and the triangle

        // Create a Path object to define the triangle
        val trianglePath = Path()
        trianglePath.moveTo(x1, y1)
        trianglePath.lineTo(x2, y2)
        trianglePath.lineTo(x3, y3)
        trianglePath.close()


        // Draw the filled triangle
        drawPath(
            path = trianglePath,
            color = Color.Blue
        )
    }
}


