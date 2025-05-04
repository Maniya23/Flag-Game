package com.example.gameapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.json.JSONObject

data class Country(val code: String, val name: String)

// Function to read JSON file and parse countries
fun readCountriesFromJson(fileName: String, context: android.content.Context): List<Country> {
    val jsonString =
        context.resources.openRawResource(R.raw.countries).bufferedReader().use { it.readText() }
    val json = JSONObject(jsonString)
    val countries = mutableListOf<Country>()
    json.keys().forEach { code ->
        val name = json.getString(code)
        countries.add(Country(code, name))
    }
    return countries
}


//Get List of Countries Widget
@Composable
fun CountryUtil() {
    val context = LocalContext.current
    val countries = readCountriesFromJson("countries.json", context)
    LazyColumn(

        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(countries) { country ->
            CountryItem(country, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(name = "CountryList")
@Composable
private fun PreviewCountryList() {
    CountryUtil()
}