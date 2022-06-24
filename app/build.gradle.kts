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

    // Retrofit
    implementation(Deps.RETROFIT_DEP)
    implementation(Deps.RETROFIT_CONVERT_JSON_DEP)
    implementation(Deps.RETROFIT_RX_DEP)

    //koin
    implementation(Deps.KOIN_DEP)

    implementation(Deps.COIL_DEP)

    //Room
    implementation(Deps.ROOM_KTX_DEP)
    kapt(Deps.ROOM_COMPILER_DEP)

    implementation( "androidx.core:core-ktx:1.8.0")
    implementation( "androidx.appcompat:appcompat:1.4.2")
    implementation( "com.google.android.material:material:1.6.1")
    implementation( "androidx.constraintlayout:constraintlayout:2.1.4")
}