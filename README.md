# Digital Countdown Timer

<!--- Replace <OWNER> with your Github Username and <REPOSITORY> with the name of your repository. -->
<!--- You can find both of these in the url bar when you open your repository in github. -->
![Workflow result](https://github.com/Xacalet/adc_w2_countdown_timer/workflows/Check/badge.svg)


## :scroll: Description
<!--- Describe your app in one or two sentences -->
A simple Android app fully built in Jetpack Compose that holds a countdown timer for the following time periods: 20 minutes, 1 minute and 10 seconds.


## :bulb: Motivation and Context
<!--- Optionally point readers to interesting parts of your submission. -->
<!--- What are you especially proud of? -->
The timer is built using the following display components:
- 7-segment components to display minutes and seconds.
- A 10-segment ring display to keep track of the tenths of second.
- A colon segment to separate minutes from seconds.

Once a countdown has started, the remaining time is constantly passed to the display components so user is aware of how much time is left before the countdown ends:
- Minutes and seconds are split into dozens and units, feeding their corresponding four digit 7-segment components.
  - Every 7-segment component reads its input and illuminates each component based on this value.
- For the tenths component, it not only received the expected unit, but also the units of second. This allow the component to make a sort of reversed animation based on the parity of the provided second:
  - If the second is odd, on the pass of each tenth, components will turn on in clockwise direction.
  - If the second is pair,  on the pass of each tenth, components will start turn off in clockwise direction.
    The illumination (or not) of each of these components is implemented by setting a bright color or a dark color to every part of all the display components, based on the provided value to each of these. The composable function `animateColorAsState` has been used to make smooth this transition between on and off states.
### How it works?
- Selecting a period of time stops the previous countdown and sets the timer to the clicked value.
- Running countdown can be paused by clicking on the Pause button and resumed clicking on the Resume button.
- Once the timer reached the end, the device vibrates three times to notify the user that the countdown is over.


## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="/results/screenshot_1.png" width="260">&emsp;<img src="/results/screenshot_2.png" width="260">

## License
```
Copyright 2020 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```