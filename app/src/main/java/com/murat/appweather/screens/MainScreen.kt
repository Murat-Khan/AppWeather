package com.murat.appweather.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.accessibility.AccessibilityViewCommand.MoveWindowArguments
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.murat.appweather.R
import com.murat.appweather.data.WeatherModel
import com.murat.appweather.ui.theme.BlueLight
import kotlinx.coroutines.launch
import org.json.JSONArray


@Composable
fun MainCard(currentDay: MutableState<WeatherModel>) {
    Column(
        modifier = Modifier
            .padding(5.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = BlueLight,
            elevation = 0.dp,
            shape = RoundedCornerShape(10.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = currentDay.value.time,
                        style = TextStyle(fontSize = 16.sp),
                        color = Color.White,
                        modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                    )
                    AsyncImage(
                        model = "https:${currentDay.value.icon}",
                        contentDescription = "coil_image",
                        modifier = Modifier
                            .padding(
                                top = 2.dp,
                                end = 8.dp
                            )
                            .size(35.dp)
                    )
                }
                Text(
                    text = currentDay.value.city,
                    style = TextStyle(fontSize = 24.sp),
                    color = Color.White,
                )
                Text(
                    text = currentDay.value.currentTemp.toFloat().toString(),
                    style = TextStyle(fontSize = 65.sp),
                    color = Color.White,
                )
                Text(
                    text = currentDay.value.condition,
                    style = TextStyle(fontSize = 18.sp),
                    color = Color.White,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(
                        onClick = { }
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = "ic_search",
                            tint = Color.White
                        )
                    }

                    Text(
                        text = "${currentDay.value.maxTemp.toFloat()}C/${currentDay.value.minTemp.toFloat()}C",
                        style = TextStyle(fontSize = 18.sp),
                        color = Color.White,
                    )

                    IconButton(
                        onClick = { }
                    ) {

                        Icon(
                            painter = painterResource(id = R.drawable.ic_sync),
                            contentDescription = "ic_sync",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(days: MutableState<List<WeatherModel>>, currentDay: MutableState<WeatherModel>) {
    val tabList = listOf("HOURS", "DAYS")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .clip(RoundedCornerShape(5.dp))

    ) {

        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPos ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState = pagerState,
                        tabPositions = tabPos
                    )
                )
            },
            backgroundColor = BlueLight,
            contentColor = Color.White

        ) {
            tabList.forEachIndexed { index, text ->

                Tab(
                    selected = false,
                    onClick = {

                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)

                        }

                    },
                    text = {
                        Text(text = text)
                    }
                )
            }
        }
        HorizontalPager(
            count = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)

        ) { index ->
            
            MainList(list = days.value, current = currentDay)



        }
    }
}

private fun getWeatherByHours(hours: String): List<WeatherModel>{
    if (hours.isEmpty()) return emptyList()
    val hoursArray=JSONArray(hours)
    val list = ArrayList<WeatherModel>()
    for (i in 0 until hoursArray.length()){

    }

return list
}
