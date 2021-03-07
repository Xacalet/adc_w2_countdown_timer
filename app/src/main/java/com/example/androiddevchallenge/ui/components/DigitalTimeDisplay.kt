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
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DigitalTimeDisplay(
    millisUntilFinished: Long,
) {
    val (minuteDozens, minuteUnits) = (millisUntilFinished / 1000 / 60)
        .coerceAtMost(99)
        .let { Pair(it.toInt() / 10, it.toInt() % 10) }
    val (secondDozens, secondUnits) = (millisUntilFinished / 1000 % 60)
        .let { Pair(it.toInt() / 10, it.toInt() % 10) }
    val tenthsOfSecond = (millisUntilFinished / 100 % 20).toInt()

    Row {

        SevenSegment(
            modifier = Modifier
                .height(72.dp)
                .width(40.dp),
            number = minuteDozens
        )
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(
            modifier = Modifier
                .height(72.dp)
                .width(40.dp),
            number = minuteUnits
        )
        Spacer(modifier = Modifier.size(4.dp))
        ColonSegment(
            modifier = Modifier
                .height(72.dp)
                .width(8.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(
            modifier = Modifier
                .height(72.dp)
                .width(40.dp),
            number = secondDozens
        )
        Spacer(modifier = Modifier.size(4.dp))
        SevenSegment(
            modifier = Modifier
                .height(72.dp)
                .width(40.dp),
            number = secondUnits
        )
        Spacer(modifier = Modifier.size(12.dp))
        TenSegmentRing(
            modifier = Modifier.size(72.dp),
            value = tenthsOfSecond
        )
    }
}

@Preview
@Composable
fun DigitalTimeDisplayPreview() {
    Box(Modifier.background(Color.Black)) {
        DigitalTimeDisplay(163800L)
    }
}
