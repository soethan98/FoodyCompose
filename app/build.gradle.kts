import org.gradle.internal.impldep.com.jcraft.jsch.ConfigRepository.defaultConfig

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
    id("app.cash.sqldelight")
}



android {
    namespace = "com.soethan.foodycompose"
    compileSdk = 34

    defaultConfig {

        applicationId = "com.soethan.foodycompose"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String","API_KEY", project.properties["API_KEY"].toString())
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
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"

        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.21")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.material3:material3-android:1.2.1")
    implementation("androidx.datastore:datastore-preferences-core-jvm:1.1.1")


    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    /// Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.50")
    kapt("com.google.dagger:hilt-android-compiler:2.50")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    /// Ktor
    val ktorVersion= "2.3.6"
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")

    /// Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")


    /// Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")

    /// Image Loading
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    implementation("androidx.fragment:fragment-ktx:1.4.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // Google fonts
    implementation("androidx.compose.ui:ui-text-google-fonts:1.5.4")

    // Splash screen API
    implementation("androidx.core:core-splashscreen:1.0.1")

    /// Custom Tabs
    implementation("androidx.browser:browser:1.7.0")

    /// Error handling
    implementation("com.github.skydoves:sandwich-ktor:2.0.5")

    /// Db
    implementation("app.cash.sqldelight:android-driver:2.0.1")
    implementation("app.cash.sqldelight:coroutines-extensions:2.0.1")

    /// DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")

    /// Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("io.mockk:mockk-android:1.13.11")
    testImplementation("io.mockk:mockk:1.13.11")






//    testImplementation("org.mockito:mockito-core:5.5.0")
//    androidTe("io.mockk:mockk:1.13.11")
//    testImplementation("org.mockito.kotlin:mockito-kotlin:5.1.0")


//    androidx-compose-ui-test = { module = "androidx.compose.ui:ui-test" }
//    androidx-compose-ui-test-junit4 = { module = "androidx.compose.ui:ui-test-junit4" }
//    androidx-compose-ui-test-manifest = { module = "androidx.compose.ui:ui-test-manifest" }

//    androidx-test-core = { module = "androidx.test:core", version.ref = "androidx-test = 1.5.0" }
//    androidx-test-espresso-core = { module = "androidx.test.espresso:espresso-core", version.ref = "androidx-test-espresso" }
//    androidx-test-ext-junit = { module = "androidx.test.ext:junit", version.ref = "androidx-test-ext-junit" }
//    androidx-test-ext-truth = { module = "androidx.test.ext:truth", version.ref = "androidx-test-ext-truth" }
//    androidx-test-rules = { module = "androidx.test:rules", version.ref = "androidx-test" }
//    androidx-test-runner = "androidx.test:runner:1.5.2"
//    junit = { module = "junit:junit", version.ref = "junit" }

//    android-test = { id = "com.android.test", version.ref = "androidGradlePlugin" }

}


//androidx-test = "1.5.0"
//androidx-test-espresso = "3.5.1"
//androidx-test-ext-junit = "1.1.5"
//androidx-test-ext-truth = "1.5.0"

// Allow references to generated code
kapt {
    correctErrorTypes = true
}


sqldelight{
    databases{
        create("RecipeDatabase"){
            packageName.set("com.soethan.foodcompose.database")
        }
    }
}
//    RecipeDatabase{
//        packageName = "com.soethan"
//    }
//}
