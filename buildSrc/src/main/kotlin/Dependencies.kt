object Modules {
    const val app = ":app"
    const val core = ":core"

    const val historyScreen = ":history"
    const val detailsScreen = ":details"
    const val translationScreen = ":translation"

    const val repository = ":repository"
    const val model = ":model"
    const val utils = ":utils"
}

object Versions {
    //Design
    const val CONSTRAINTLAYOUT = "2.1.4"
    const val APPCOMPAT = "1.4.2"
    const val MATERIAL = "1.6.1"

    //Kotlin
    const val KOTLIN_CORE = "1.8.0"
    const val COROUTINES_CORE = "1.6.3"

    const val RETROFIT = "2.9.0"
    const val ADAPTER_COROUTINES = "1.0.0"

    const val KOIN = "3.2.0-beta-1"

    const val COIL = "1.4.0"

    const val ROOM = "2.4.2"

}

object Design {
    const val APPCOMPAT = "androidx.appcompat:appcompat:${Versions.APPCOMPAT}"
    const val MATERIAL = "com.google.android.material:material:${Versions.MATERIAL}"
    const val CONSTRAINTLAYOUT =
        "androidx.constraintlayout:constraintlayout:${Versions.CONSTRAINTLAYOUT}"
}

object Kotlin {
    const val CORE = "androidx.core:core-ktx:${Versions.KOTLIN_CORE}"
    const val COROUTINES_CORE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES_CORE}"
}

object Retrofit {
    const val CORE = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"
}

object Koin {
    const val ANDROID = "io.insert-koin:koin-android:${Versions.KOIN}"
}

object Coil {
    const val CORE = "io.coil-kt:coil:${Versions.COIL}"
}

object Room {
    const val COMPILER = "androidx.room:room-compiler:${Versions.ROOM}"
    const val KTX = "androidx.room:room-ktx:${Versions.ROOM}"
}