plugins {
    id(Build.androidApplication)
    id(Kotlin.plugin)
    id(DaggerHilt.plugin)
    id(Kotlin.kaptPlugin)
    id(Firebase.plugin)
}

android {
    namespace = "mx.com.lgonzalez.pruebatecnica"
    compileSdk = 34

    defaultConfig {
        applicationId = "mx.com.lgonzalez.pruebatecnica"
        minSdk = 23
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion =  Kotlin.extensionVersion
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.lifecycleRuntimeKtx)

    implementation(Compose.activityCompose)
    implementation(platform(Compose.composeBom))
    implementation(Compose.composeUi)
    implementation(Compose.composeGraphics)
    implementation(Compose.composeToolingPreview)
    implementation(Compose.composeMaterial3)
    implementation(Compose.composeLifecycleViewModel)
    implementation(Compose.composeNavigation)
    implementation(Compose.composeLifecycle)
    implementation(Compose.composeHiltNavigation)
    debugImplementation(Compose.composeUiTooling)
    debugImplementation(Compose.composeTestManifest)
    implementation(DaggerHilt.hiltAndroid)
    kapt(DaggerHilt.hiltCompiler)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.gsonConverter)
    implementation(platform(Retrofit.okHttpBom))
    implementation(Retrofit.okHttp)
    implementation(Retrofit.okHttpLoggingInterceptor)

    implementation(Maps.mapsCompose)
    implementation(Maps.playServicesMaps)

    implementation(Coil.coilCompose)

    kapt(Room.roomCompiler)
    implementation(Room.roomKtx)
    implementation(Room.roomRuntime)

    implementation(Location.playServicesLocation)

    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.firestore)

    testImplementation(Testing.junit4)
    androidTestImplementation(Testing.extJunit)
    androidTestImplementation(Testing.espresso)
    androidTestImplementation(platform(Testing.composeBomTesting))
    androidTestImplementation(Testing.composeUiTesting)

}