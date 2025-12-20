# fxbrowser

A minimal JavaFX based webbrowser that I use for testing the WebView control.

## Building and running the project

* clone the project

```
    git clone https://github.com/xzel23/fxbrowser.git
```

* run gradle, the `run` task compiles and runs the application

```
    cd fxbrowser
    ./gradlew run
```

* run gradle, the `jpackge` task produces an appliction installer in the build/jpackage folder (on Windows, you need to 
  install the WiX toolset first)

```
    cd fxbrowser
    ./gradlew jpackage
```
