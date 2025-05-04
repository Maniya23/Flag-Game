package com.example.gameapp

import android.os.CountDownTimer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay


@Composable
fun GuessCountryScreen(
    modifier: Modifier,
    countries: List<Country>,
    timer: Int,
    isChecked: Boolean,
) {

    //Create state to keep users selected country
    var selectedCountry by remember { mutableStateOf<Country?>(null) }
    var showSuccessAlert by remember { mutableStateOf(false) }
    var showNext by remember { mutableStateOf(false) }
    var showErrorAlert by remember { mutableStateOf(false) }
    // Get a random country from country list
    var randomCountry by remember { mutableStateOf(countries.random()) }
    
    var time by remember { mutableLongStateOf(10) }

    // Get current context
    val context = LocalContext.current

    //Get the flag path from drawables
    val randomCountryFlag = painterResource(id = getFlagResourceId(randomCountry.code, context))

    // Start of display
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD6EAF8))
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Guess the country",
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600)),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(10.dp))
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

        //  show correct country if showNext is enabled and selected country is wrong
        if (showNext && selectedCountry!=randomCountry){
//            isChecked=false;
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = randomCountry.name,
                style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(400)),
                textAlign = TextAlign.Center,
                color = Color.Blue,
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = randomCountryFlag,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(200.dp)
                    .height(160.dp),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        // Country list
        Row(modifier = Modifier.height(370.dp)) {
            LazyColumn {
                items(countries) { country ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { selectedCountry = country }) {
                        Text(text = country.name, modifier = Modifier.padding(16.dp))
                        if (selectedCountry == country) {
                            Icon(Icons.Default.Check, contentDescription = "Selected")
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))

        //Submit button
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            AppButton(
                label =
                if (showNext)
                    "Next"
                else
                    "Submit",
                onClick = {
                    //Get new random on next button click
                    if (showNext) {
                        randomCountry = countries.random()
                        selectedCountry = null;
                        showNext = false

                    // Handle submit action with selectedCountry
                    } else if (selectedCountry?.code === randomCountry.code) {
                        showSuccessAlert = true
                        showNext = true
                    } else {
                        showErrorAlert = true
                        showNext = true
                    }
                },
                enabled = selectedCountry != null, // Disable button if nothing is selected
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // show success alert when showSuccessAlert is true
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

        // show error alert when showErrorAlert is true
        if (showErrorAlert) {
            AlertDialog(onDismissRequest = { showErrorAlert = false },
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
                })
        }
    }
}

@Preview(name = "GuessCountryScreen")
@Composable
private fun PreviewGuessCountryScreen() {
    GuessCountryScreen(
        modifier = Modifier,
        countries = listOf(Country(name = "Sri Lanka", code = "lk")),
        timer = 0,
        isChecked = false,
    )
}

/*
* Ayushpan. CountDownTimer in Android using Kotlin. GeeksforGeeks. Available from https://www.geeksforgeeks.org/countdowntimer-in-android-using-kotlin/ [Accessed on 02 April 2024]
*
* Google developers. CountDownTimer. Developers Android. Available from https://developer.android.com/reference/kotlin/android/os/CountDownTimer [Accessed on 02 April 2024]
* */