package ru.pseudonimb.uc.screens.main

import android.content.Context
import android.graphics.drawable.Drawable
import android.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Resource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kodein.di.compose.rememberDI
import org.kodein.di.instance
import ru.pseudonimb.uc.R
import ultimateclicker.composeapp.generated.resources.Res
import ultimateclicker.composeapp.generated.resources.compose_multiplatform
import ultimateclicker.composeapp.generated.resources.upgrade

@Composable
@Preview
fun MainScreen() {
    val vm: MainViewModel by rememberDI { instance() }
    val state by vm.mainState.collectAsState()

    val sharedPref = getDefaultSharedPreferences(LocalContext.current)
    var number by remember { mutableIntStateOf(state.number) }
    var numberModifier by remember { mutableIntStateOf(state.numberModifier) }

    number = sharedPref.getInt("number", 0)
    numberModifier = sharedPref.getInt("numberModifier", 1)

    MaterialTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(text = "Здесь короткое описание / название детали или продукта с картинкой")
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //Кнопка прибавления
                        IconButton(
                            onClick = {
                                number++
                                with (sharedPref.edit()) {
                                    putInt("number", number)
                                    apply()
                                }
                            },
                            enabled = number < state.NUMBER_CONST * numberModifier
                        ) {
                            Icon(
                                Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.weight(0.7f)
                        ) {
                            ShowProgress(number * numberModifier, numberModifier)
                        }

                        //Кнопка улучшения
                        IconButton(
                            onClick = {
                                number = 0
                                numberModifier *= 2
                                with (sharedPref.edit()) {
                                    putInt("numberModifier", numberModifier)
                                    apply()
                                }
                            },
                            enabled = number >= state.NUMBER_CONST * numberModifier
                        ) {
                            Icon(
                                painter = painterResource(resource = Res.drawable.upgrade),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                    var finalLevelCount = numberModifier
                    var finalLevel = 0

                    while (finalLevelCount > 1) {
                        finalLevelCount /= 2
                        finalLevel++
                    }

                    Text(text= "level " + (finalLevel+1), modifier = Modifier.padding(horizontal = 40.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun ShowProgress(score: Int, numberModifier: Int) {

    val gradient = Brush.linearGradient(
        listOf(
            Color(0xFFF95075),
            Color(0xFFBE6BE5)
        )
    )

    val progressFactor by remember(score) {
        mutableFloatStateOf(score * 0.1f / numberModifier)
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth().height(60.dp).border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFF95075),
                        Color(0xFFBE6BE5)
                    )
                ),
                shape = RoundedCornerShape(50.dp)
            )
            .clip(
                RoundedCornerShape(
                    topStartPercent = 50,
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                    bottomStartPercent = 50
                )
            )
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically
    ) {
        //Прогрессбар-полоска
        Button(
            contentPadding = PaddingValues(1.dp),
            onClick = {

            },
            modifier = Modifier
                .fillMaxWidth(progressFactor / numberModifier)
                .background(brush = gradient),
            enabled = false,
            elevation = null,
            colors = buttonColors(
                containerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            )
        ) {
            Text(
                text = score.toString(),
                maxLines = 1,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(23.dp))
                    .fillMaxHeight(0.87f)
                    .fillMaxWidth()
                    .padding(vertical = 11.dp),
                color = Color(0xFFFFFFFF),
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
        }
    }
}