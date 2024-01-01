import org.gradle.internal.impldep.com.esotericsoftware.minlog.Log
import java.io.File

object Versions {
    const val compose = "1.5.0"
    const val compose_paging = "1.0.0-alpha17"
    const val constraint_layout = "1.0.1"
    const val accompanist = "0.25.1"
    const val nav_version = "2.5.2"
    const val appCompat = "1.5.1"
    const val material  = "1.6.1"
    const val lifecycle = "2.6.2"
    const val hilt = "2.47"
    const val hiltCompose = "1.0.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.11.0"
    const val moshi = "1.15.0"
    const val converterMoshi = "2.9.0"
    const val coroutines = "1.5.2"
    const val coil = "2.2.2"
    const val gson = "2.9.1"
    const val palette = "1.0.0"
    const val icon_extended = "1.4.0-alpha02"
    const val browser = "1.5.0-alpha01"
    const val room_version = "2.6.0-alpha01"
    const val annotation = "1.7.0"
}

object AppMetaData {
    const val id = "com.sample.tmdb"
    const val targetSdkVersion = 34
    const val compileSdkVersion = 34
    const val minSdkVersion = 21
}

/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"
    const val COMMON = ":common"
    const val DATA = ":core:data"
    const val DOMAIN = ":core:domain"
    const val FEATURE_FEED = ":features:feature-feed"
    const val FEATURE_BOOKMARK = ":features:feature-bookmark"
    const val FEATURE_PAGING = ":features:feature-paging"
    const val FEATURE_DETAIL = ":features:feature-detail"
    const val FEATURE_CREDIT = ":features:feature-credit"
}

object Deps {
    // Compose
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val navigationCompose = "androidx.navigation:navigation-compose:${Versions.nav_version}"
    const val composeConstraintLayout = "androidx.constraintlayout:constraintlayout-compose:${Versions.constraint_layout}"
    const val iconExtended = "androidx.compose.material:material-icons-extended:${Versions.icon_extended}"

    // Paging Compose
    const val composePaging = "androidx.paging:paging-compose:${Versions.compose_paging}"

    // Accompanist
    const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"

    // UI
    const val material = "com.google.android.material:material:${Versions.material}"
    const val palette = "androidx.palette:palette-ktx:${Versions.palette}"
    const val browser = "androidx.browser:browser:${Versions.browser}"
    const val annotation = "androidx.annotation:annotation:${Versions.annotation}"

    // Core
    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleSavedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"

    // Hilt
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hilt_compose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltCompose}"

    // Networking
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshi}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // Moshi
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    // Coroutines
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    // Gson
    const val gson =  "com.google.code.gson:gson:${Versions.gson}"

    // Room
    const val room = "androidx.room:room-runtime:${Versions.room_version}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room_version}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room_version}"
}