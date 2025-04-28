dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("gradle/libs.versions.toml")) // Certifique-se de que isto aparece apenas uma vez
        }
    }
}

rootProject.name = "toDoList"
include(":app")