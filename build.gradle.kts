plugins {
    id("application")
    id("com.github.spotbugs") version "6.4.8"
    id("com.dua3.gradle.jdkprovider") version "0.1.0"
    id("org.beryx.jlink") version "3.2.0"
    id("com.github.ben-manes.versions") version "0.53.0"
}

application {
    applicationName = "FxBrowser"
    mainModule = "fxbrowser"
    mainClass = "com.dua3.fxbrowser.FxBrowser"
}

jdk {
    version = "25"
    javaFxBundled = true
}

jlink {
    javaHome = jdk.jdkHome
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("com.dua3.utility:utility-bom:20.1.0"))
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.25.3"))
    implementation(platform("org.kordamp.ikonli:ikonli-bom:12.4.0"))

    implementation("com.dua3.utility:utility")
    implementation("com.dua3.utility:utility-fx")
    implementation("com.dua3.utility:utility-fx-controls")
    implementation("com.dua3.utility:utility-fx-icons")
    implementation("com.dua3.utility:utility-fx-icons-ikonli")

    implementation("org.apache.logging.log4j:log4j-core")

    implementation("org.kordamp.ikonli:ikonli-fontawesome-pack")
}
