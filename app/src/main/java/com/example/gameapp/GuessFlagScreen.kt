package com.example.gameapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GuessFlagScreen(
    modifier: Modifier = Modifier,
    countries: List<Country>
) {
    var chosenCountry by remember { mutableStateOf<Country?>(null) }

    var randomCountry by remember { mutableStateOf(countries.random()) }
    var randomCountry1 by remember { mutableStateOf(countries.random()) }
    var randomCountry2 by remember { mutableStateOf(countries.random()) }

    val context = LocalContext.current
    var randomCountryFlag = painterResource(id = getFlagResourceId(randomCountry.code, context))
    var randomCountryFlag1 = painterResource(id = getFlagResourceId(randomCountry1.code, context))
    var randomCountryFlag2 = painterResource(id = getFlagResourceId(randomCountry2.code, context))

    var showNext by remember { mutableStateOf(false) }
    var showSuccessAlert by remember { mutableStateOf(false) }
    var showErrorAlert by remember { mutableStateOf(false) }

    fun handleSubmit() {
        if (chosenCountry == randomCountry) {
            showSuccessAlert = true;
            showNext = true;
        } else {
            showErrorAlert = true
            showNext = true;
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD6EAF8)),
    ) {
        Box(modifier) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Guess the Flag",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(600)),
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column (modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Image(
                painter = randomCountryFlag,
                contentDescription = null,
                modifier = Modifier
                    .clickable { chosenCountry = randomCountry }
                    .width(200.dp)
                    .height(160.dp),
            )
            Image(
                painter = randomCountryFlag1,
                contentDescription = null,
                modifier = Modifier
                    .clickable { chosenCountry = randomCountry1 }
                    .width(200.dp)
                    .height(160.dp),
            )
            Image(
                painter = randomCountryFlag2,
                contentDescription = null,
                modifier = Modifier
                    .clickable { chosenCountry = randomCountry2 }
                    .width(200.dp)
                    .height(160.dp),
            )

            Spacer(modifier = Modifier.height(50.dp));
            Text(
                text = randomCountry.name,
                style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600)),
            )

            Spacer(modifier = Modifier.height(32.dp))
            AppButton(
                label =
                if (showNext)
                    "Next"
                else
                    "Submit",
                onClick = {
                          if (showNext){
                              randomCountry = countries.random();
                              randomCountry1 = countries.random();
                              randomCountry2 = countries.random();
                              showNext = false;
                          } else
                              handleSubmit();
                },
                enabled = chosenCountry != null, // Nothing selected button disabled
                modifier = Modifier
            )

            if (showSuccessAlert) {
                AlertDialog(onDismissRequest = { showSuccessAlert = false },
                    text = { Text(
                        text = "Correct",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight(600),
                            color = Color.Green),
                        )
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White, containerColor = Color.Green
                            ),
                            onClick = { showSuccessAlert = false },
                        ) {
                            Text("OK")
                        }
                    })
            }

            if (showErrorAlert) {
                AlertDialog(
                    onDismissRequest = { showErrorAlert = false },
                    text = { Text(
                        text = "Wrong",
                        style = TextStyle(
                            fontSize = 32.sp,
                            fontWeight = FontWeight(600),
                            color = Color.Red),
                        )
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Color.White, containerColor = Color.Red
                            ),
                            onClick = { showErrorAlert = false },
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}

@Preview(name = "GuessFlagScreen")
@Composable
private fun PreviewGuessFlagScreen() {
    GuessFlagScreen(
        modifier = Modifier,
        countries = listOf(Country(name = "Sri Lanka", code = "lk"))
    )
}