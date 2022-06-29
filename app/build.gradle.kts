plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "gunixun.dictionary"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        javaCompileOptions {
            annotationProcessorOptions {
                argument("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.detailsScreen))
    implementation(project(Modules.historyScreen))
    implementation(project(Modules.translationScreen))
    implementation(project(Modules.model))
    implementation(project(Modules.repository))
    implementation(project(Modules.utils))

    // Retrofit
    implementation(Retrofit.CORE)
    implementation(Retrofit.CONVERTER_GSON)

    //koin
    implementation(Koin.ANDROID)

    //Room
    implementation(Room.KTX)
    kapt(Room.COMPILER)

    implementation(Kotlin.CORE)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINTLAYOUT)
}