package com.example.gameapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val timer = 10000;
            var isChecked by remember { mutableStateOf(false) }

            // Read JSON file on app create
            val countries = readCountriesFromJson("countries.json", context)

            // Update switch on home screen
            fun onChangeCheck(){
                isChecked = !isChecked // Change the current state of switch
//                Log.d("onchange","$isChecked")
            }

            //App Navigation
            NavHost(navController = navController, startDestination = "HomeScreen") {
                composable("HomeScreen") { HomeScreen(
                    modifier = Modifier,
                    navController,
                    isChecked,
                    onChangeCheck = ::onChangeCheck) }

                composable("GuessCountryScreen") {
                    GuessCountryScreen(
                        modifier = Modifier,
                        countries,
                        timer,
                        isChecked,

                    )
                }
                composable("GuessHintScreen") {
                  GuessHintsScreen(
                      modifier = Modifier,
                      countries,
                      isChecked,
                  )
                }
                composable("GuessFlagScreen") {
                    GuessFlagScreen(
                        modifier = Modifier,
                        countries,
                        isChecked,
                    )
                }
                composable("AdvancedLevel") {
                    AdvancedLevel(
                        modifier = Modifier,
                        countries,
                        isChecked,
                    )
                }
            }
        }
    }
}