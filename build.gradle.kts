buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.71")
    }
}

val jUnitVersion = "5.3.2"
val mockKVersion = "1.9.3"

plugins {
    kotlin("jvm") version "1.3.71"
    `java-library`
    id("com.github.johnrengelman.shadow") version "5.0.0"
    application
}

tasks.withType<Jar> {
    archiveBaseName.set("ktor-sample-service")
    manifest {
        attributes["Main-Class"] = "example.JsonApplicationKt"
    }
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
            events("passed", "failed", "skipped")
        }
    }
}

repositories {
    mavenLocal()
    maven {
        url = uri("http://maven.codelibs.org")
    }
    jcenter()
}

fun DependencyHandler.ktor(name: String) =
        create(group = "io.ktor", name = name, version = "1.3.1")

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(ktor("ktor-server-netty"))
    implementation(ktor("ktor-locations"))
    implementation(ktor("ktor-server-core"))
    implementation(ktor("ktor-gson"))
    implementation(group = "com.github.ajalt", name = "clikt", version = "1.3.0")
    implementation("de.nielsfalk.ktor:ktor-swagger:0.5.0")

    /* Junit Platform */
    testImplementation(kotlin("test", "1.3.71"))
    testImplementation(kotlin("test-junit", "1.3.71"))
    testImplementation(ktor("ktor-server-test-host"))
    testImplementation(ktor("ktor-gson"))
    testImplementation("com.winterbe:expekt:0.5.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    testImplementation("io.mockk:mockk:$mockKVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

application {
    mainClassName = "example.JsonApplicationKt"
}
