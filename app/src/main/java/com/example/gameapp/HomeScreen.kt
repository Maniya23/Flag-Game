package com.example.gameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlin.concurrent.timer

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    isChecked: Boolean,
    onChangeCheck: ()->Unit
) {


    // Start of display
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD6EAF8)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Start Flag Quiz Game",
                textAlign = TextAlign.Center,
                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600))
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AppButton(
            label = "Guess the Country",
            onClick = { navController.navigate("GuessCountryScreen") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            label = "Guess-Hints",
            onClick = { navController.navigate("GuessHintScreen") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            label = "Guess the Flag",
            onClick = { navController.navigate("GuessFlagScreen") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        AppButton(
            label = "Advanced Level",
            onClick = { navController.navigate("AdvancedLevel") },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        
        Switch(
            checked = isChecked,
            onCheckedChange = { onChangeCheck() }
        )
    }
}


@Preview(name = "HomeScreen")
@Composable
private fun PreviewHomeScreen() {
    val navController = rememberNavController()
    HomeScreen(
        modifier = Modifier,
        navController = navController,
        isChecked = false,
        onChangeCheck={}
    )
}