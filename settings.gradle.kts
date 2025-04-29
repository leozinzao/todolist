dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from("gradle/libs.versions.toml")
        }
    }
}