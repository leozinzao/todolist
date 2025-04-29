plugins {
    id 'com.android.application'
}

android {
    compileSdk 33

    defaultConfig {
        applicationId "com.example.todolist"
        minSdk 21
        targetSdk 33
        versionCode 1
        versionName "1.0"

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
                targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {

    // Dependência para recursos de AndroidX básicos
    implementation "androidx.core:core-ktx:1.10.1"

    // Dependência para Room Database (runtime)
    implementation "androidx.room:room-runtime:2.5.2"

    // Dependência para o compilador de anotações do Room (para projetos em Java)
    annotationProcessor "androidx.room:room-compiler:2.5.2"
}