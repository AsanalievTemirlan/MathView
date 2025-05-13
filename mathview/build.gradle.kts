plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose) version "2.0.20"
    id("maven-publish") // Для публикации библиотеки
}

android {
    namespace = "com.example.mathview" // Namespace для библиотеки
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        targetSdk = 35 // Рекомендуется указать targetSdk


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false // Можно включить позже для оптимизации
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

    // Включение поддержки Jetpack Compose
    buildFeatures {
        compose = true
    }

    // Настройка версии компилятора Compose
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15" // Актуальная версия для Compose
    }
}

dependencies {
    // Базовые зависимости Android
    implementation(libs.androidx.appcompat)
    implementation("androidx.compose.material:material") // или нужную тебе версию

    // Зависимости Jetpack Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.ui:ui-viewinterop")


    // Зависимость для WebView
    implementation(libs.androidx.webkit)

    // Тестовые зависимости
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

// Настройка публикации библиотеки
publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "io.github.AsanalievTemirlan" // Замените на ваш GitHub-ник, например, io.github.ivanpetrov
            artifactId = "mathview"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}