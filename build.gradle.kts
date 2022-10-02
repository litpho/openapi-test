import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("com.atradius.openapi:atradius-spring:0.0.1-SNAPSHOT")
    }
}

plugins {
    kotlin("jvm") version "1.7.10"
    id("org.openapi.generator") version("6.2.0")
    id("org.springframework.boot")
}

repositories {
    mavenLocal()
    mavenCentral()
}

openApiGenerate {
    generatorName.set("atradius-spring")
    outputDir.set("$buildDir/generated-sources")
    inputSpec.set("$projectDir/src/main/spec/petstore.yml")
    packageName.set("my.test")
    apiPackage.set("com.atradius.openapi.test.api")
    modelPackage.set("com.atradius.openapi.test.model")
    configOptions.set(mapOf(
        "useBeanValidation" to "true",
        "useSpringBoot3" to "false"
    ))
}

val springBootVersion: String by project
val springDocVersion: String by project

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:$springBootVersion"))

    implementation("com.google.code.findbugs:jsr305:3.0.2")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.3")
    implementation("org.openapitools:jackson-databind-nullable:0.2.3")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springdoc:springdoc-openapi-kotlin:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

    testImplementation(kotlin("test"))
}

sourceSets.main {
    java.srcDir("$buildDir/generated-sources/src/main/java")
    resources.srcDir("$buildDir/generated-sources/src/main/resources")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.named("compileKotlin") {
    dependsOn("openApiGenerate")
}