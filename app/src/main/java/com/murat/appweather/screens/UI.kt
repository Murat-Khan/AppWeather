package com.murat.appweather.screens

import  androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.murat.appweather.data.WeatherModel
import com.murat.appweather.ui.theme.BlueLight



@Composable
fun MainList(list: List<WeatherModel>, current:MutableState<WeatherModel>){
    LazyColumn(modifier = Modifier.fillMaxSize()){
        itemsIndexed(
           list
        ){_, item ->
            ListItem(item = item)

        }
    }
}

@Composable
fun ListItem(item:WeatherModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        backgroundColor = BlueLight,
        elevation = 0.dp,
        shape = RoundedCornerShape(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 5.dp,
                        top = 5.dp,
                        bottom = 5.dp
                    )
            ) {
                Text(text = item.time)
                Text(
                    text = item.condition,
                    color = Color.White

                )
            }

            Text(
                text = item.currentTemp.ifEmpty {"${item.maxTemp}/${item.minTemp}"},
                color = Color.White,
                style = TextStyle(fontSize = 25.sp)

            )
            
            AsyncImage( model = "https:${item.icon}",
                contentDescription = "im5",
                modifier = Modifier
                    .padding(
                        end = 8.dp
                    )
                    .size(35.dp))
        }
    }
}