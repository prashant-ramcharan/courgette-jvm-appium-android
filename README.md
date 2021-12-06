# Courgette-JVM with Appium (Android)

An example project showing how to use Courgette-JVM with Appium to test Android native applications.

## System Requirements

* Java
* Windows / MacOS
* [Appium](https://appium.io/docs/en/about-appium/getting-started/?lang=en)
* [Android Studio](https://developer.android.com/studio) with Pixel 4a and Nexus 6 emulators

## Test Execution

There are 2 scenarios in this project and Courgette will run both scenarios in parallel across 2 different android emulators.


https://user-images.githubusercontent.com/2563149/144915983-a93e150e-0cb3-428d-a07d-eadd7377d154.mp4


You must set the `ANDROID_HOME` [environment](https://developer.android.com/studio/command-line/variables) variable and point this to the Android SDK.

Using Gradle from the command line

````gradle
./gradlew runAndroidTestsInParallel
````

Pass the `ANDROID_HOME` environment variable at runtime.
````gradle
ANDROID_HOME=path-to-android-sdk ./gradlew runAndroidTestsInParallel
````

Using JUnit in the IDE
````java
runners/AndroidTestRunner.java
````
