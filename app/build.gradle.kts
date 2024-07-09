plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "nacholab.androidbase"
    compileSdk = 34

    defaultConfig {
        applicationId = "nacholab.androidbase"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        all {
            buildConfigField("Long", "API_TIMEOUT", "10000L") // 10 seconds
            buildConfigField("Long", "API_CACHE", "10485760L") // 10MB
            buildConfigField("String", "API_URL", "\"https://audioclips.nacholab.net/\"")
            buildConfigField("String", "DATA_STORE_NAME", "\"settings\"")
            buildConfigField("Long", "TIME_BETWEEN_MOVIE_DOWNLOADS", "3600000L") // 1 hour
//            buildConfigField("Long", "MIN_SPLASH_TIME", "1500L")
        }

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
        dataBinding = true
        buildConfig = true
    }
}

dependencies {

    // Android core
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.databinding.runtime)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    // Meta Flipper debugging tool
    implementation(libs.soloader)
    implementation(libs.flipper)
    implementation(libs.flipper.network.plugin)

    // Lottie
//    implementation(libs.lottie)

    // FlowDB


    // Room
//    implementation(libs.androidx.room.runtime)
//    kapt(libs.androidx.room.compiler)

    // DataStore
//    implementation(libs.androidx.datastore.preferences)

    // Swipe refresh layout
    implementation(libs.androidx.swiperefreshlayout)

    // Glide
//    implementation(libs.glide)

    // CardView
//    implementation(libs.androidx.cardview)
}