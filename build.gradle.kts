import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.4.5" apply false
    id("io.spring.dependency-management") version "1.0.11.RELEASE" apply true
    kotlin("jvm") version "1.5.0-RC" apply false
    kotlin("plugin.spring") version "1.5.0-RC" apply false
}

allprojects {
    group = "com.gorlah.demo"
}

subprojects {

    apply {
        plugin("io.spring.dependency-management")
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
    }

    dependencyManagement {
        imports {
            mavenBom("org.testcontainers:testcontainers-bom:1.15.3")
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = JavaVersion.VERSION_11.toString()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
