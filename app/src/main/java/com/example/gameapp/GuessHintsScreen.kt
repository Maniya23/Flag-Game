package com.example.gameapp

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessHintsScreen(
    modifier: Modifier,
    countries: List<Country>,
    isChecked: Boolean,
) {
    // Store context
    val context = LocalContext.current

    //store Alerts, number of incorrect characters and answers visible state
    var incorrectNumber by remember { mutableIntStateOf(0) }
    var showSuccessAlert by remember { mutableStateOf(false) }
    var showNext by remember { mutableStateOf(false) }
    var showErrorAlert by remember { mutableStateOf(false) }
    var showAnswer by remember { mutableStateOf(false) }

    var time by remember { mutableLongStateOf(10) }

    // State to keep random country
    var randomCountry by remember { mutableStateOf(countries.random()) }

    // Store users input and result
    var userInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    //Get the flag path from drawables
    val flagPng = painterResource(id = getFlagResourceId(randomCountry.code, context))

    // Assign dashes to the characters
    fun getDashedString(): String {
        var dashedString = "";
        randomCountry.name.forEach { char ->
            val isIncluded = userInput.lowercase().contains(char.lowercaseChar())
            if (isIncluded) {
                dashedString += char;
            } else {
                dashedString += "_";
            }
        }
        return dashedString;
    }

    // Handle submit
    fun handleSubmit() {
        result = getDashedString();
        if (result == randomCountry.name) {
            showSuccessAlert = true;
            showNext = true;
        } else {
            showAnswer = true;
            showErrorAlert = true;
            showNext = true;
            Log.d("GuessHintsScreen", "Incorrect guess")
        }
    }

    // Increment wrong users inputs and show error
    fun incorrectUserInput() {
        incorrectNumber++
//        Log.d("Incorrect number", incorrectNumber.toString())

        if (incorrectNumber==3){
            showErrorAlert = true;
        }
    }

    //Start of display
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color(0xFFD6EAF8))
            .padding(16.dp),
    ) {
        Text(
            text = "Guess Hints",
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600)),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            if (isChecked){
                Text(text = "Time remaining : $time s")

//                // referred from Kotlin Documentation & GeeksforGeeks referenced at the end
//                object : CountDownTimer(10000, 1000) {
//
//                    override fun onTick(millisUntilFinished: Long) {
//                        time = millisUntilFinished;
//                        time/=1000;
//                    }
//
//
//                    override fun onFinish() {
//                        showErrorAlert = true;
//                        showNext = true;
//                    }
//                }.start()
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = flagPng,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(200.dp)
                    .height(160.dp),
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        // Text box and function to handle when wrong character input
        TextField(
            value = userInput,
            onValueChange = { userInput = it
                if (!randomCountry.name.lowercase().contains(it.lowercase())){
                    incorrectUserInput();
                }},
            label = { Text("Enter your guess:") },
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(20.dp))
        AppButton(
            label =
            if (showNext)
                "Next"
            else
                "Submit",
            onClick = {
                if (showNext){
                    showAnswer = false;
                    randomCountry = countries.random();
                    showNext = false;
                    userInput = "";
                    result = "";
                    incorrectNumber = 0;

                } else {
                    handleSubmit();
                }
            },
            enabled = true,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))

        // show success alert when showSuccessAlert is true
        if (showSuccessAlert) {
            AlertDialog(
                onDismissRequest = { showSuccessAlert = false },
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
                        onClick = {
                            showSuccessAlert = false
                            showNext = true;
                            userInput = "";
                            result = "";
                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White, containerColor = Color.Green
                        ),
                    ) {
                        Text("OK")
                    }
                }
            )
        }

        // show error alert when showErrorAlert is true
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
                        onClick = {
                            showErrorAlert = false
                            showNext = true;

                        },
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.White, containerColor = Color.Red
                        ),
                    ) {
                        Text("OK")
                    }
                }
            )
        }
        Row {
            // if answer is wrong show correct answer
            if (showAnswer){
                Text(
                    randomCountry.name,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.Blue
                )
            }
        }

        // When show next button is active, refresh variables
        if (showNext) {
            userInput = "";
            result = "";
            incorrectNumber = 0;
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (result === "")
                    getDashedString()
                else
                    result,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 26.sp,
                        letterSpacing = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
            )
        }
    }
}

@Preview(name = "GuessHintsScreen")
@Composable
private fun PreviewGuessHintsScreen() {
    GuessHintsScreen(
        modifier = Modifier,
        countries = listOf(Country(name = "Sri Lanka", code = "lk")),
        isChecked = false
    )
}

/*
* Ayushpan. CountDownTimer in Android using Kotlin. GeeksforGeeks. Available from https://www.geeksforgeeks.org/countdowntimer-in-android-using-kotlin/ [Accessed on 02 April 2024]
*
* Google developers. CountDownTimer. Developers Android. Available from https://developer.android.com/reference/kotlin/android/os/CountDownTimer [Accessed on 02 April 2024]
* */