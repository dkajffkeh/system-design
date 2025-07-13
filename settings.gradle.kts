pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
}
rootProject.name = "pat-talk"
include("id-generator")
include("protocol")
include("chatting-server")
include("connection-reporter")
include("api-dog")
include("cloud-gateway")
