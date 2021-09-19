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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun TenSegmentRing(
    modifier: Modifier = Modifier,
    value: Int,
    colorOn: Color = LocalDigitalTimeDisplayColors.current.segmentOn,
    colorOff: Color = LocalDigitalTimeDisplayColors.current.segmentOff
) {
    val sectionCount = 10
    val colors = (0 until sectionCount).map {
        animateColorAsState(
            if (value >= 10 && (sectionCount - 1 - (value % 10) >= it))
                colorOn
            else if (value < 10 && (sectionCount - 1 - (value % 10) < it))
                colorOn
            else
                colorOff
        )
    }

    Canvas(modifier) {
        val outerRadius = size.minDimension / 2f
        val innerRadius = outerRadius / 1.5f
        val spacerPercent = 0.25f
        val sectionArchInRads = PI * 2 * (1 - spacerPercent) / sectionCount
        val spacerArchInRads = PI * 2 * spacerPercent / sectionCount
        var nextStartAngle = (PI * 3 / 2) + (spacerArchInRads / 2)

        repeat((0 until sectionCount).count()) {
            ringSegment(
                color = colors[it].value,
                outerRadius = outerRadius,
                innerRadius = innerRadius,
                startAngle = nextStartAngle,
                sweepAngle = sectionArchInRads
            )
            nextStartAngle += sectionArchInRads + spacerArchInRads
        }
    }
}

fun DrawScope.ringSegment(
    color: Color,
    outerRadius: Float,
    innerRadius: Float,
    startAngle: Double,
    sweepAngle: Double
) = drawPath(
    color = color,
    path = Path().apply {
        moveTo(
            x = size.center.x + (innerRadius * cos(startAngle).toFloat()),
            y = size.center.y + (innerRadius * sin(startAngle).toFloat())
        )
        arcToRad(
            rect = Rect(size.center, outerRadius),
            startAngleRadians = startAngle.toFloat(),
            sweepAngleRadians = sweepAngle.toFloat(),
            forceMoveTo = false
        )
        lineTo(
            x = size.center.x + (innerRadius * cos(startAngle + sweepAngle).toFloat()),
            y = size.center.y + (innerRadius * sin(startAngle + sweepAngle).toFloat())
        )
        arcToRad(
            rect = Rect(size.center, innerRadius),
            startAngleRadians = startAngle.plus(sweepAngle).toFloat(),
            sweepAngleRadians = -sweepAngle.toFloat(),
            forceMoveTo = false
        )
    }
)

@Preview
@Composable
fun IncNineSegmentRingPreview() {
    var value by remember { mutableStateOf(19) }
    Column(
        modifier = Modifier
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TenSegmentRing(Modifier.size(64.dp), value)
        Spacer(modifier = Modifier.size(12.dp))
        Button(
            onClick = { value = if (value == 0) 19 else value - 1 }
        ) {
            Text(text = "$value")
        }
    }
}
