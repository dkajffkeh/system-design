plugins {
    java
    id("org.springframework.boot") version "3.5.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
extra["springCloudVersion"] = "2021.0.5"


configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}


subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    group = "com.patrick"
    java.sourceCompatibility = JavaVersion.VERSION_17

    tasks.register("prepareKotlinBuildScriptModel") {}

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

project(":id-generator") {
    dependencies {
        implementation(project(":protocol"))
        implementation("org.springframework.boot:spring-boot-starter-webflux")
        testImplementation("io.projectreactor:reactor-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

project(":chatting-server") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

project(":connection-reporter") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-redis-reactive")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

project(":api-dog") {
    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-web")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        implementation ("org.springframework.boot:spring-boot-starter-validation")

        // ✅ Swagger / SpringDoc OpenAPI 3 의존성 추가
        implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.2.0")
    }
}

project(":cloud-gateway") {
    dependencies {
        implementation("org.springframework.cloud:spring-cloud-starter-gateway")
        implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
        implementation("org.springframework.cloud:spring-cloud-starter-loadbalancer")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}

project(":protocol") {
    dependencies {
    }

    val jar: Jar by tasks
    val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

    bootJar.enabled = false
    jar.enabled = true
}
