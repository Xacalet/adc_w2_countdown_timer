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

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.segmentOff
import com.example.androiddevchallenge.ui.theme.segmentOn

@Composable
fun ButtonPanel(
    modifier: Modifier = Modifier,
    isRunning: Boolean,
    onClickTimeSelector: (TimeSelectorButton) -> Unit,
    onClickControlButton: () -> Unit
) {
    Row(modifier.animateContentSize()) {
        TimeSelectButton(4.dp, "25\'", TimeSelectorButton.Minutes25, onClickTimeSelector)
        Spacer(modifier = Modifier.size(8.dp))
        TimeSelectButton(8.dp, "1\'", TimeSelectorButton.Minute1, onClickTimeSelector)
        Spacer(modifier = Modifier.size(8.dp))
        TimeSelectButton(14.dp, "10\"", TimeSelectorButton.Seconds10, onClickTimeSelector)
        Spacer(modifier = Modifier.size(32.dp))
        Button(
            modifier = Modifier.size(64.dp),
            onClick = onClickControlButton,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = segmentOff
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            Crossfade(targetState = isRunning) { isNowRunning ->
                if (isNowRunning) {
                    Icon(
                        imageVector = Icons.Filled.Pause,
                        modifier = Modifier.size(32.dp),
                        tint = segmentOn,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        modifier = Modifier.size(32.dp),
                        tint = segmentOn,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
fun TimeSelectButton(
    borderDistance: Dp,
    text: String,
    timePeriod: TimeSelectorButton,
    onclick: (TimeSelectorButton) -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .clickable { onclick(timePeriod) }
            .background(segmentOff)
            .padding(borderDistance)
            .border(2.dp, segmentOn, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text, color = segmentOn)
    }
}

@Composable
@Preview
fun ButtonPanelPreview() {
    Surface {
        ButtonPanel(Modifier, true, {}, {})
    }
}

sealed class TimeSelectorButton {
    object Minutes25 : TimeSelectorButton()
    object Minute1 : TimeSelectorButton()
    object Seconds10 : TimeSelectorButton()
}
