# fxbrowser

A simple JavaFX based webbrowser that I use for testing the WebView control and also as a test project for my [gradle plugin](https://github.com/xzel23/JpmsGradlePlugin).

## Building and running the project

* clone the project
```
    git clone https://github.com/xzel23/fxbrowser.git
```

* run gradle, the `jlink` task produces a standalone application
```
    cd fxbrowser
    ./gradlew jlink
```

* after building, start the program
```
    ./build/dist/bin/fxbrowser
```
