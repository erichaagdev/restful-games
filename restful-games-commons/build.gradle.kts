plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks {
    bootJar { enabled = false }
    jar { enabled = true }
}