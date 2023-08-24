plugins {
    id("com.android.application")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("jacoco-reports")
}

android {
    namespace = "br.com.challenge_world_cup_2022"
    compileSdk = 34

    defaultConfig {
        applicationId = "br.com.challenge_world_cup_2022"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":libraries:network"))
    implementation(project(":libraries:extensions"))
    implementation(project(":libraries:testing"))

    implementation(Dependencies.Main.core_ktx)
    implementation(Dependencies.Lifecycle.lifecycle_runtime)

    //Compose
    implementation(Dependencies.Compose.activity_compose)
    implementation(platform(Dependencies.Compose.compose_bom))
    implementation(Dependencies.Compose.compose_ui)
    implementation(Dependencies.Compose.compose_graphics)
    implementation(Dependencies.Compose.compose_ui_tooling_preview)
    implementation(Dependencies.Compose.compose_material3)
    implementation(Dependencies.Compose.constraintlayout_compose)

    //Material3
    implementation(Dependencies.Compose.material3)
    //Icons Material 3
    implementation(Dependencies.Compose.material_icons_extended)

    // System UI Controller - Accompanist
    implementation(Dependencies.ThirdParty.system_ui_controller)

    //WorkManager
    implementation(Dependencies.WorkManager.work_manager)

    //DataStore Preferences
    implementation(Dependencies.DataStore.data_store)

    //Coil
    implementation(Dependencies.ThirdParty.coil_compose)
    implementation(Dependencies.ThirdParty.coil_svg)

    //Room
    ksp(Dependencies.Room.room_compiler)
    implementation(Dependencies.Room.room_runtime)
    implementation(Dependencies.Room.room)

    //Network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.converter_gson)
    implementation(Dependencies.Network.okhttp3_logging_interceptor)

    //ThirdParty
    implementation(Dependencies.ThirdParty.koin)

    //Unit Test
    testImplementation(Dependencies.Testing.assertK)
    testImplementation(Dependencies.Testing.robolectric)
    testImplementation(Dependencies.Testing.mockk)
    testImplementation(Dependencies.Testing.junit)
    testImplementation(Dependencies.Testing.mockwebserver)
    testImplementation(Dependencies.Testing.junit)

    //Instrumental Unit
    androidTestImplementation(Dependencies.Testing.AutomatedTest.ext_junit)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.espresso_core)
    androidTestImplementation(platform(Dependencies.Compose.compose_bom))
    androidTestImplementation(Dependencies.Testing.Compose.ui_test_junit4)
    androidTestImplementation(Dependencies.Testing.assertK)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.mockk_android)
    androidTestImplementation(Dependencies.Testing.AutomatedTest.barista)
    debugImplementation(Dependencies.Compose.ui_tooling)
    debugImplementation(Dependencies.Compose.ui_test_manifest)

}