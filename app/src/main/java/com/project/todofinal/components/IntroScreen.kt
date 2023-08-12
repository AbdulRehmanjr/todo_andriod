package com.project.todofinal.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.todofinal.R


@Preview(showBackground = true)
@Composable
fun IntroScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // set the home page image
            Image(
                painter =  painterResource(id = R.drawable.homepage),
                contentDescription = null,
                modifier = Modifier
                    .height(100.dp)
                    .width(300.dp)
                    .padding(16.dp)
            )

            Row {
                Column {
                    Image(
                        painter =  painterResource(id = R.drawable.logo),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(16.dp,16.dp,0.dp,0.dp)
                    )
                }
                Column {
                    Text(
                        text = "TODO APP",
                        color = Color.Red,
                        fontSize = 50.sp,
                        fontWeight= FontWeight.W900,
                        fontFamily = FontFamily.Cursive,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                }
            }

            Button(
                onClick = {},
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(16.dp)
                    .shadow(elevation = 7.dp, shape = MaterialTheme.shapes.large),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Favorite",
                    tint = Color.White
                )
                Text(
                    text = "Lets Go!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}

