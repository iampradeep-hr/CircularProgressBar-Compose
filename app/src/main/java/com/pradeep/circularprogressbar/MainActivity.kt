package com.pradeep.circularprogressbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pradeep.circularprogressbar.ui.theme.CircularProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarTheme {
                
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.Center
                ){
                    CircularProgressBar(
                        percentage = 0.8f,
                        number = 100,
                        radius = 100.dp,
                        fontSize = MaterialTheme.typography.h3.fontSize,
                        strokeWidth = 20.dp
                    )
                }
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    percentage:Float,
    number:Int,
    fontSize: TextUnit =28.sp,
    radius: Dp =50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp =8.dp,
    animDuration:Int=2000,
    animDelay:Int=0
) {

    var animationPlayed by remember{
        mutableStateOf(false)
    }

    val currPercentage = animateFloatAsState(
        targetValue = if (animationPlayed)percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    LaunchedEffect(key1 = true){
        animationPlayed=true
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.size(radius * 2f)
    ) {
        Canvas(modifier = Modifier.size(radius*2f)){
            drawArc(
                color=color,
                startAngle = -90f,
                sweepAngle = 360*currPercentage.value,
                useCenter = false,
                style = Stroke(
                    strokeWidth.toPx(),
                    cap = StrokeCap.Round
                )
            )
        }
        Text(
            modifier = Modifier.clickable {
                animationPlayed=!animationPlayed
            },
            text =(currPercentage.value*number).toInt().toString(),
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold

        )

    }

}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CircularProgressBarTheme {
      
    }
}