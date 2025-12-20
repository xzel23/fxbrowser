plugins {
    id("application")
    id("com.github.spotbugs") version "6.4.8"
    id("com.dua3.gradle.jdkprovider") version "0.1.0"
    id("org.beryx.jlink") version "3.2.0"
    id("com.github.ben-manes.versions") version "0.53.0"
}

jdk {
    javaFxBundled = true
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass = "com.dua3.fxbrowser.FxBrowser"
}

val junitVersion = "5.13.1"
val dua3UtilityVersion = "20.1.0"
val ikonliVersion = "12.4.0"
val log4jVersion = "2.25.1"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation(platform("com.dua3.utility:utility-bom:${dua3UtilityVersion}"))
    implementation("com.dua3.utility:utility")
    implementation("com.dua3.utility:utility-fx")
    implementation("com.dua3.utility:utility-fx-controls")
    implementation("com.dua3.utility:utility-fx-icons")
    implementation("com.dua3.utility:utility-fx-icons-ikonli")

    implementation("org.kordamp.ikonli:ikonli-core:${ikonliVersion}")
    implementation("org.kordamp.ikonli:ikonli-javafx:${ikonliVersion}")
    implementation("org.kordamp.ikonli:ikonli-fontawesome-pack:${ikonliVersion}")

    implementation(platform("org.apache.logging.log4j:log4j-bom:${log4jVersion}"))
    implementation("org.apache.logging.log4j:log4j-core")
}

jlink {
    javaHome = jdk.jdkHome

    jpackage {
        installerName = "install FxBrowser"
    }
}
