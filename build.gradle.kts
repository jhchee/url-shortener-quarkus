plugins {
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.allopen") version "1.4.20"
    id("io.quarkus")
}

repositories {
    mavenLocal()
    mavenCentral()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    // to deserialize into kotlin's data class
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation ("io.quarkus:quarkus-mongodb-panache:1.13.6.Final")
    implementation ("io.quarkus:quarkus-amazon-lambda-http")

    testImplementation("io.quarkus:quarkus-junit5")
}

group = "vinchee"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}

tasks.quarkusBuild {
    nativeArgs {
        "container-build" to true
        "buildImage" to "quay.io/quarkus/ubi-quarkus-native-image:21.1.0-java11"
        "quarkus.native.java-home" to "/usr/lib/jvm/java-11-openjdk-amd64"
    }
}
