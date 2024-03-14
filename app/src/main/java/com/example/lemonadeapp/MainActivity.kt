package com.example.lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonadeapp.ui.theme.LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}
@Composable
fun WelcomeScreen(textLabelResourceId: Int,
                  drawableResourceId: Int,
                  contentDescriptionResourceId: Int,
                  onImageClick: () -> Unit,
                  modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Button(
            onClick = onImageClick
        ) {
            Image(
                painter = painterResource(id = drawableResourceId),
                contentDescription = stringResource(id = contentDescriptionResourceId)
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = stringResource(id = textLabelResourceId)
        )
    }

}


@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    when (currentStep) {
        1 -> {
            WelcomeScreen(
                textLabelResourceId = R.string.textlemon_tree,
                drawableResourceId = R.drawable.lemon_tree,
                contentDescriptionResourceId = R.string.lemon_tree,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                })
        }

        2 -> {
            WelcomeScreen(
                textLabelResourceId = R.string.textlemon_squeeze,
                drawableResourceId = R.drawable.lemon_squeeze,
                contentDescriptionResourceId = R.string.lemon,
                onImageClick = {
                    squeezeCount--
                    if(squeezeCount == 0) {
                        currentStep = 3
                    }

                })
        }
        3 -> {
            WelcomeScreen(
                textLabelResourceId = R.string.textdrink_lemon,
                drawableResourceId = R.drawable.lemon_drink,
                contentDescriptionResourceId = R.string.glass_lem,
                onImageClick = { currentStep = 4 })
        }
        4 -> {
            WelcomeScreen(
                textLabelResourceId = R.string.textrestart_lemon,
                drawableResourceId = R.drawable.lemon_restart,
                contentDescriptionResourceId = R.string.empty_glass,
                onImageClick = {
                    currentStep = 1
                })
        }
    }
}








