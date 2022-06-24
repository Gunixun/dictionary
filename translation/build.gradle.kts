plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
    implementation(project(Modules.model))
    implementation(project(Modules.repository))
    implementation(project(Modules.utils))

    //koin
    implementation(Koin.ANDROID)

    implementation(Kotlin.CORE)
    implementation(Kotlin.COROUTINES_CORE)
    implementation(Design.APPCOMPAT)
    implementation(Design.MATERIAL)
    implementation(Design.CONSTRAINTLAYOUT)
}