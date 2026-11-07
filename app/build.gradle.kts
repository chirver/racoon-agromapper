plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.racoonair.mapper"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.racoonair.mapper"
        minSdk = 26
        // 29 a propósito: mismo comportamiento que el APK compilado a mano (firma v1, sin
        // restricciones de Android 11+ que no aportan nada a esta app de campo).
        targetSdk = 29
        versionCode = 9
        versionName = "0.7"
        ndk { abiFilters += listOf("arm64-v8a", "armeabi-v7a") }
    }

    buildTypes {
        release {
            isMinifyEnabled = false // ARSDK usa JNI por nombre: no ofuscar
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    packaging {
        jniLibs { useLegacyPackaging = true }
    }
    lint {
        abortOnError = false
        disable += listOf("ExpiredTargetSdkVersion", "OldTargetApi")
    }
}

dependencies {
    // ARSDK3 3.14 (Parrot, BSD-3): clases Java + librerías nativas en src/main/jniLibs
    implementation(files("libs/arsdk-3.14-classes.jar"))
}
