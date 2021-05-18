pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        gradlePluginPortal()
    }
}

rootProject.name = "restful-games"

include("restful-games-client")
include("restful-games-server")
include("restful-games-commons")
