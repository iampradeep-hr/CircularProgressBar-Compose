package com.pradeep.circularprogressbar

import android.content.Context
import android.os.Bundle
import android.view.animation.Transformation
import android.widget.ImageView.ScaleType
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.Coil
import coil.ImageLoader
import coil.compose.*
import coil.request.ImageRequest
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.pradeep.circularprogressbar.ui.theme.CircularProgressBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CircularProgressBarTheme {
                Box(modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center){

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