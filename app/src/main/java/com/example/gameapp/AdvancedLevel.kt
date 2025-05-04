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
import androidx.compose.material3.TextFieldDefaults
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
fun AdvancedLevel(
    modifier: Modifier = Modifier,
    countries: List<Country>,
    isChecked: Boolean
) {

    // hold 3 random countries
    var randomCountry1 by remember { mutableStateOf(countries.random()) }
    var randomCountry2 by remember { mutableStateOf(countries.random()) }
    var randomCountry3 by remember { mutableStateOf(countries.random()) }

    // store context and flag images from drawables
    val context = LocalContext.current
    val randomCountryFlag1 = painterResource(id = getFlagResourceId(randomCountry1.code, context))
    val randomCountryFlag2 = painterResource(id = getFlagResourceId(randomCountry2.code, context))
    val randomCountryFlag3 = painterResource(id = getFlagResourceId(randomCountry3.code, context))

    // store 3 user inputs
    var userInput1 by remember { mutableStateOf("") }
    var userInput2 by remember { mutableStateOf("") }
    var userInput3 by remember { mutableStateOf("") }

    // store text field enable status
    var enableTextField1 by remember { mutableStateOf(true) }
    var enableTextField2 by remember { mutableStateOf(true) }
    var enableTextField3 by remember { mutableStateOf(true) }

    // store text field colors
    var textFieldColor1 by remember { mutableStateOf(Color.Black) }
    var textFieldColor2 by remember { mutableStateOf(Color.Black) }
    var textFieldColor3 by remember { mutableStateOf(Color.Black) }

    // Store answer display states
    var showAnswer1 by remember { mutableStateOf(false) }
    var showAnswer2 by remember { mutableStateOf(false) }
    var showAnswer3 by remember { mutableStateOf(false) }

    var time by remember { mutableLongStateOf(10) }

    val textFieldColors1 = TextFieldDefaults.textFieldColors(
        textColor = textFieldColor1
    )

    // store attempts and points
    var attemptNo by remember { mutableIntStateOf(1) }
    var points by remember { mutableIntStateOf(0) }

    // store alerts
    var showNext by remember { mutableStateOf(false) }
    var showSuccessAlert by remember { mutableStateOf(false) }
    var showErrorAlert by remember { mutableStateOf(false) }

    // Handle submit (validate users answers)
    fun handleSubmit(){

        if (attemptNo!=3){
            // Check if all countries match
            if (userInput1.lowercase() == randomCountry1.name.lowercase()
                &&
                userInput2.lowercase() == randomCountry2.name.lowercase()
                &&
                userInput3.lowercase() == randomCountry3.name.lowercase())
            {
                showSuccessAlert = true;
                showNext = true;
            }

            // check if each country matches if yes disable text field
            else {
                if (userInput1.lowercase()==randomCountry1.name.lowercase()){
                    textFieldColor1 = Color.Green;
                    enableTextField1 = false;
                }
                if (userInput2.lowercase() == randomCountry2.name.lowercase()){
                    textFieldColor2 = Color.Green;
                    enableTextField2 = false;
                }
                if (userInput3.lowercase() == randomCountry3.name.lowercase()){
                    textFieldColor3 = Color.Green;
                    enableTextField3 = false;
                }
            }
        }

        // Execute when all attempts are used
        else {
            showErrorAlert = true
            showNext = true;
        }
        attemptNo++;
    }

    // Assign all variables to default values
    fun refreshPage(){
        randomCountry1 = countries.random()
        randomCountry2 = countries.random()
        randomCountry3 = countries.random()

        userInput1 = ""
        userInput2 = ""
        userInput3 = ""

        enableTextField1 = true
        enableTextField2 = true
        enableTextField3 = true

        attemptNo = 1;

        textFieldColor1 = Color.Black;
    }

    // Calculate the users score
    fun scoreCalc(){
        if (userInput1.lowercase()==randomCountry1.name.lowercase())
            points+=1;
        if (userInput2.lowercase() == randomCountry2.name.lowercase())
            points+=1;
        if (userInput3.lowercase() == randomCountry3.name.lowercase())
            points+=1;
    }

    // Show incorrect Answers
    fun showAnswers(){
        if (userInput1.lowercase() != randomCountry1.name.lowercase())
            showAnswer1 = true;
        if (userInput2.lowercase() != randomCountry2.name.lowercase())
            showAnswer2 = true;
        if (userInput3.lowercase() != randomCountry3.name.lowercase())
            showAnswer3 = true;
    }

    // Hide all answers
    fun hideAnswers(){
        showAnswer1 = false;
        showAnswer2 = false;
        showAnswer3 = false;
    }

    // Start of display
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFD6EAF8)),
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Advanced Level",
            style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight(600)),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(30.dp))

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

        Row {
            Text(
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                text = "Your Score: $points",
                style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(400)),
                textAlign = TextAlign.Right,
            )
        }
        Row (
            modifier = Modifier.
            fillMaxWidth(),
            horizontalArrangement = Arrangement.Center

        ){
            Image(
                painter = randomCountryFlag1,
                contentDescription = null,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .padding(10.dp)
            )

            Image(
                painter = randomCountryFlag2,
                contentDescription = null,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .padding(10.dp)
            )

            Image(
                painter = randomCountryFlag3,
                contentDescription = null,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Column {
            TextField(
                value = userInput1,
                onValueChange = { userInput1 = it },
                label = { Text("Enter flag 1:") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors1,
                enabled = enableTextField1,

            )

            if (showAnswer1){
                Text(
                    text = randomCountry1.name,
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(400)),
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = userInput2,
                onValueChange = { userInput2 = it },
                label = { Text("Enter flag 2:") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enableTextField2,
            )

            if (showAnswer2){
                Text(
                    text = randomCountry2.name,
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(400)),
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                value = userInput3,
                onValueChange = { userInput3 = it },
                label = { Text("Enter flag 3:") },
                modifier = Modifier.fillMaxWidth(),
                enabled = enableTextField3,
            )

            if (showAnswer3){
                Text(
                    text = randomCountry3.name,
                    style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight(400)),
                    color = Color.Blue,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
        Spacer(modifier = Modifier.height(30.dp))

        // Show Incorrect answers
        if (showNext)
            showAnswers();

        AppButton(
            label =
            if (showNext)
                "Next"
            else
                "Submit",
            onClick = {
                      if (showNext){
                          scoreCalc();
                          refreshPage();
                          hideAnswers();
                          showNext = false;
                      } else {
                          handleSubmit();
                      }
            },
            enabled = true,
            modifier = Modifier
        )

        // show success alert when showSuccessAlert is true
        if (showSuccessAlert){
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
    }
}




@Preview(name = "AdvancedLevel")
@Composable
private fun PreviewAdvanceLevel() {
    AdvancedLevel(
        modifier = Modifier,
        countries = listOf(Country(name = "Sri Lanka", code = "lk")),
        isChecked = false,
    )
}

/*
* Ayushpan. CountDownTimer in Android using Kotlin. GeeksforGeeks. Available from https://www.geeksforgeeks.org/countdowntimer-in-android-using-kotlin/ [Accessed on 02 April 2024]
*
* Google developers. CountDownTimer. Developers Android. Available from https://developer.android.com/reference/kotlin/android/os/CountDownTimer [Accessed on 02 April 2024]
* */