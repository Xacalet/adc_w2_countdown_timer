/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screen

import android.content.Context
import android.os.CountDownTimer
import android.os.Vibrator
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.components.ButtonPanel
import com.example.androiddevchallenge.ui.components.DigitalTimeDisplay
import com.example.androiddevchallenge.ui.components.TimeSelectorButton
import java.util.concurrent.TimeUnit

@Composable
@Preview
fun DigitalTimeDisplayScreen() {
    var millisLeft by rememberSaveable { mutableStateOf(TimeUnit.MINUTES.toMillis(1)) }
    var millisInFuture by rememberSaveable { mutableStateOf(TimeUnit.MINUTES.toMillis(1)) }
    var isCountDownRunning by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    DisposableEffect(isCountDownRunning) {

        val countdownTimer = object : CountDownTimer(
            if (millisLeft == 0L) millisInFuture else millisLeft,
            100L
        ) {
            override fun onTick(millisUntilFinished: Long) {
                millisLeft = millisUntilFinished
            }

            override fun onFinish() {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                val pattern = longArrayOf(0, 300, 500, 300, 500, 300)
                vibrator?.vibrate(pattern, -1)
                isCountDownRunning = false
                millisLeft = 0
            }
        }

        if (isCountDownRunning) {
            countdownTimer.start()
        } else {
            countdownTimer.cancel()
        }

        onDispose {
            countdownTimer.cancel()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        DigitalTimeDisplay(
            millisUntilFinished = millisLeft
        )
        Spacer(modifier = Modifier.size(32.dp))

        ButtonPanel(
            isRunning = isCountDownRunning,
            onClickTimeSelector = { timePeriod ->
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                vibrator?.vibrate(10)
                when (timePeriod) {
                    TimeSelectorButton.Minutes25 -> TimeUnit.MINUTES.toMillis(25)
                    TimeSelectorButton.Minute1 -> TimeUnit.MINUTES.toMillis(1)
                    TimeSelectorButton.Seconds10 -> TimeUnit.SECONDS.toMillis(10)
                }.let { millis ->
                    isCountDownRunning = false
                    millisLeft = millis
                    millisInFuture = millis
                }
            },
            onClickControlButton = {
                val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator?
                vibrator?.vibrate(10)
                isCountDownRunning = !isCountDownRunning
            }
        )
    }
}
