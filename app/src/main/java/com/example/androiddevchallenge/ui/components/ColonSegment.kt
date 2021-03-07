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

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColonSegment(
    modifier: Modifier,
    colorOn: Color = LocalDigitalTimeDisplayColors.current.segmentOn,
    colorOff: Color = LocalDigitalTimeDisplayColors.current.segmentOff,
    activated: Boolean = true,
) {
    val color by animateColorAsState(if (activated) colorOn else colorOff)

    Canvas(modifier) {
        val radius: Float = size.width / 2f
        drawCircle(color, radius, center - Offset(0f, radius * 3))
        drawCircle(color, radius, center + Offset(0f, radius * 3))
    }
}

@Preview
@Composable
fun ColonSegmentPreview() {
    ColonSegment(
        modifier = Modifier
            .height(72.dp)
            .width(8.dp)
    )
}
