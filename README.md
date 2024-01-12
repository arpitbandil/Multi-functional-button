# Android Click Events Library

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)
[![Latest Release](https://img.shields.io/badge/Libary-AAD7D9)](https://jitpack.io/#yourusername/yourlibrary](https://jitpack.io/#arpitbandil/Multi-Functional_Button/1.0.0))

## Overview

The Android Click Events Library is a Kotlin-based library that simplifies the detection of various click events in Android applications. It provides a straightforward interface to detect single clicks, double clicks, hold start, hold end, and onHolding events.

## Sample Demo
<img src="https://github.com/arpitbandil/Multi-Functional_Button/assets/30489209/21fc6418-d531-4bac-8c68-24816ed0f1b2" width="200" alt_text="Button Sample Demo"/>


## Features

- **Single Click Detection:** Easily identify single click events on UI elements.
  
- **Double Click Detection:** Detect double clicks with customizable time thresholds.

- **Hold Start and Hold End Detection:** Identify when a user starts holding down on a view and when they release it.

- **OnHolding Event:** Receive periodic callbacks while the user is holding down on a view.

## Installation

### Gradle

Add the following to your module-level `build.gradle` file:

```gradle
dependencies {
    implementation 'com.github.arpitbandil:Multi-Functional_Button:1.0.0'
}
```
Make sure you have JitPack repository added in your build.gradle:
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
### Maven
```
<dependency>
    <groupId>com.github.arpitbandil</groupId>
    <artifactId>Multi-Functional_Button</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage
```
view.setMultiFunctionalTouchListener(object :
            MultiFunctionalTouchListener.EventListener {
            override fun onDoubleClick(v: View?) {
               //ToDo
            }

            override fun onHoldEnd(v: View?) {
                //ToDo
            }

            override fun onHoldStart(v: View?) {
                //ToDo
            }

            override fun onHolding(v: View?) {
                //ToDo
            }

            override fun onSingleClick(v: View?) {
                //ToDo
            }
        })
```

## License
This library is licensed under the MIT License - see the LICENSE file for details.
This README provides a concise yet comprehensive guide for users to understand, install, and use your library effectively.
