package com.pal.canvas.basicShape.WeighPicker


import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.core.graphics.withRotation
import com.pal.canvas.basicShape.LineType
import com.pal.canvas.basicShape.ScaleStyle
import kotlin.math.*


@Composable
fun Scale(modifier: Modifier,
          style: ScaleStyle = ScaleStyle(),
          minWeight: Int = 20,
          maxWeight:Int = 250,
          initialWeight:Int = 80,
          onWeightChange:(Int) -> Unit
          ){
            val radius = style.radius
            val scaleWidth = style.scaleWidth
            var center by remember {
                mutableStateOf(Offset.Zero)
            }
            var circleCenter by remember {
                mutableStateOf(Offset.Zero)
            }
            var angle by remember {
                mutableStateOf(0f)
            }
            var dragStartedAngle by remember {
                mutableStateOf(0f)
            }
            var oldAngle by remember {
                mutableStateOf(angle)
            }
            Canvas(modifier = modifier.fillMaxSize()
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDragStart = {offset ->
                           dragStartedAngle = -atan2(circleCenter.x - offset.x,circleCenter.y - offset.y) * (180f / PI.toFloat())
                        },
                        onDragEnd = {
                                    oldAngle = angle
                        },
                        onDrag = { change, dragAmount ->
                            val touchAngle = -atan2(
                                circleCenter.x - change.position.x,
                                circleCenter.y - change.position.y
                            ) * (180f / PI.toFloat())

                            val newAngle = oldAngle + (touchAngle - dragStartedAngle)
                            angle = newAngle.coerceIn(
                                minimumValue = initialWeight - maxWeight.toFloat(),
                                maximumValue = initialWeight - minWeight.toFloat()
                            )
                            onWeightChange((initialWeight - angle).roundToInt())

                        }
                    )

                /*.pointerInput(Unit){
                detectTransformGestures {  _, pan, _, _ ->

                    val touchAngle = -atan2(
                        circleCenter.x - pan.x,
                        circleCenter.y - pan.y
                    ) * (180f / PI.toFloat())

                    val newAngle = oldAngle + (touchAngle - dragStartedAngle)
                    angle = newAngle.coerceIn(
                        minimumValue = (initialWeight - maxWeight).toFloat(),
                        maximumValue = (initialWeight - minWeight).toFloat()
                    )
                    val newWeight = initialWeight - angle
                    onWeightChange(newWeight.roundToInt())

                }*/
            }

            ){
                center = this.center
                circleCenter = Offset(center.x,scaleWidth.toPx()/2f + radius.toPx())
                val outerRadius = radius.toPx() + scaleWidth.toPx()/2f
                val innerRadius = radius.toPx() - scaleWidth.toPx()/2f
                drawContext.canvas.nativeCanvas.apply {
                    drawCircle(circleCenter.x,circleCenter.y,radius.toPx(),
                        Paint().apply {
                        strokeWidth = scaleWidth.toPx()
                        color = android.graphics.Color.WHITE
                        setStyle(Paint.Style.STROKE)
                        setShadowLayer(60f,0f,0f, android.graphics.Color.argb(50,0,0,0))


                    })
                }

                /*drawLines*/
                for (i in minWeight..maxWeight){
                    val angleInPad = (i - initialWeight + angle - 90) * (PI/180f).toFloat()
                    val lineType = when{
                        i % 10 == 0 -> LineType.TenStep
                        i % 5 == 0 -> LineType.FiveStep
                        else -> LineType.Normal
                    }
                    val lineLength = when(lineType) {
                        LineType.Normal -> style.normalLineLength.toPx()
                        LineType.FiveStep -> style.fiveStepLineLength.toPx()
                        LineType.TenStep -> style.tenStepLineLength.toPx()
                    }
                    val lineColor = when(lineType) {
                        LineType.Normal -> style.normalLineColor
                        LineType.FiveStep -> style.fiveStepLineColor
                        LineType.TenStep -> style.tenStepLineColor
                    }
                    val lineStart = Offset(
                        x = (outerRadius - lineLength) * cos(angleInPad) + circleCenter.x,
                        y = (outerRadius - lineLength) * sin(angleInPad) + circleCenter.y
                    )
                    val lineEnd = Offset(
                        x = outerRadius * cos(angleInPad) + circleCenter.x,
                        y = outerRadius * sin(angleInPad) + circleCenter.y
                    )

                    drawContext.canvas.nativeCanvas.apply {
                        if (lineType is LineType.TenStep){
                            val textRadius = outerRadius - lineLength - 5.dp.toPx() - style.textSize.toPx()
                            val x = textRadius * cos(angleInPad) + circleCenter.x
                            val y = textRadius * sin(angleInPad) + circleCenter.y
                            withRotation(
                                degrees = angleInPad  * (180f / PI.toFloat()) + 90f,
                                pivotX = x,
                                pivotY = y
                            ){
                                drawText(abs(i).toString(),x,y,Paint().apply {
                                    textSize = style.textSize.toPx()
                                    textAlign = Paint.Align.CENTER
                                })

                            }

                        }
                    }

                    drawLine(color = lineColor, start = lineStart, end = lineEnd, strokeWidth = 1.dp.toPx())
                    val middleTop = Offset(x = circleCenter.x, y = circleCenter.y - innerRadius - style.scaleIndicatorLength.toPx())
                    val bottomLeft = Offset(x=circleCenter.x - 4f, y = circleCenter.y - innerRadius)
                    val bottomRight = Offset(x=circleCenter.x + 20f, y = circleCenter.y - innerRadius)
                    val indicator = Path().apply {
                        moveTo(middleTop.x,middleTop.y)
                        lineTo(bottomLeft.x,bottomLeft.y)
                        lineTo(bottomRight.x,bottomRight.y)
                        lineTo(middleTop.x,middleTop.y)
                    }
                    drawPath(path = indicator, color = style.scaleIndicatorColor)
                }

            }
        }

@Composable
fun WeightPick(){
    var weight by remember {
                mutableStateOf(50)
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Scale(
                    style = ScaleStyle(
                        scaleWidth = 150.dp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .align(Alignment.BottomCenter)
                ) {
                    weight = it
                }
            }
}