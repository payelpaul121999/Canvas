import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.cos
import kotlin.math.sin

/*@Composable
fun ArcView(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(300.dp)) {
        drawArc(
            color = Color.Cyan,
            -180f,
            180f,
            useCenter = true,
            style = Stroke(10.dp.toPx(), cap = StrokeCap.Round)
        )
    }
}*/

@Composable
fun ArcComposable(modifier: Modifier) {
    Canvas(
        modifier = Modifier
            .size(size = 390.dp)
            .border(color = Color.Magenta, width = 2.dp)
    ) {
        drawArc(
            brush = Brush.horizontalGradient(colors = listOf(Color.Yellow, Color.Green)),
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = true,
            topLeft = Offset(x = 10.dp.toPx(), y = 10.dp.toPx()),
            style = Stroke(width = 8.dp.toPx())
        )
    }
}