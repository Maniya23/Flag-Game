package com.example.gameapp

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CountryItem(country: Country, modifier: Modifier) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .padding(16.dp)
            .background(color = Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val flagPng = painterResource(id = getFlagResourceId(country.code, context = context))
        Image(
            painter = flagPng,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .width(50.dp),
            // Adjust the size of the flag image as needed
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = country.name)
    }
}

fun getFlagResourceId(countryCode: String, context: Context): Int {
    return try {
        context.resources.getIdentifier(countryCode.toLowerCase(), "drawable", context.packageName)
    } catch (e: Exception) {
        println(e)
        R.drawable.lk // Default flag resource in case the specified flag is not found
    }
}

@Preview(name = "CountryItem")
@Composable
private fun PreviewCountryItem() {
    CountryItem(Country(code = "lk", name = "Sri Lanka"), modifier = Modifier)
}