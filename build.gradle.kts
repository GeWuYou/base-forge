import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.utils.extendsFrom

plugins {
    application
    // 基本 Java 支持
    alias(libs.plugins.java)
    // Java 库开发支持
    alias(libs.plugins.java.library)
    // Maven 发布支持
    alias(libs.plugins.maven.publish)
    // Spring Boot 支持
    alias(libs.plugins.spring.boot)
    // Spring 依赖管理
    alias(libs.plugins.spring.dependency.management)
    // Protobuf 支持
    alias(libs.plugins.protobuf)
    // Kotlin 支持
    alias(libs.plugins.kotlin.jvm)
    // Kotlin Spring 支持
    alias(libs.plugins.kotlin.plugin.spring)
    // Kotlin Lombok 支持
    alias(libs.plugins.kotlin.plugin.lombok)
    // Kotlin JPA 支持
    alias(libs.plugins.kotlin.plugin.jpa)
    id("com.gewuyou.baseforge.plugin.jib")
}

// 排除根项目的 Kotlin 标准库依赖
configurations.implementation {
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
}

val configDir = "$rootDir/config/"

apply(plugin = "com.gewuyou.baseforge.plugin.task.gradle.file")


allprojects {
    group = "com.gewuyou"
}
subprojects {
    val libs = rootProject.libs
    apply(plugin = "java")
    apply(plugin = "java-library")
    // 非api模块无需发布
    if(project.name.lastIndexOf("api") == -1){
        apply(plugin = "org.springframework.boot")
        apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    }else{
        apply(plugin = "maven-publish")
    }
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.lombok")
    // 自定义插件
    apply(plugin = "com.gewuyou.baseforge.plugin.jib")
    apply(plugin = "com.gewuyou.baseforge.plugin.task.banner")

    apply(from = "$configDir/repositories.gradle")
    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    jibConfig {
        project {
            projectName = "base-forge-config"
            ports = listOf("8080")
            environment = mapOf("SPRING_PROFILES_ACTIVE" to "native")
        }

        project {
            projectName = "base-forge-discovery"
            ports = listOf("8761")
            environment = mapOf("SPRING_PROFILES_ACTIVE" to "prod")
            entrypoint = listOf(
                "/bin/sh", "-c",
                "apt-get update && apt-get install -y netcat-openbsd && " +
                        "/entrypoint.sh -d base-forge-config:8888 -c " +
                        "'java -cp $( cat /app/jib-classpath-file ) $( cat /app/jib-main-class-file )'"
            )
        }

        project {
            projectName = "base-forge-gateway"
            ports = listOf("8082")
            environment = mapOf("SPRING_PROFILES_ACTIVE" to "prod")
            entrypoint = listOf(
                "/bin/sh", "-c",
                "apt-get update && apt-get install -y netcat-openbsd && " +
                        "/entrypoint.sh -d base-forge-discovery:8761 -c " +
                        "'java -cp $( cat /app/jib-classpath-file ) $( cat /app/jib-main-class-file )'"
            )
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.MappedSuperclass")
        annotation("jakarta.persistence.Embeddable")
    }
    tasks.named<Test>("test") {
        useJUnitPlatform()
    }

    tasks.withType<Jar> {
        isEnabled = true
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    tasks.withType<KotlinCompile> {
        compilerOptions {
            freeCompilerArgs.add("-Xjsr305=strict")
            jvmTarget.set(JvmTarget.JVM_21)
        }
    }
    // 启用注解处理
    configurations {
        compileOnly.extendsFrom(annotationProcessor)
    }

    dependencies {
        // 公共依赖
        compileOnly(libs.lombok)
        annotationProcessor(libs.lombok)
        implementation(platform(libs.grpc.bom))
        implementation(platform(libs.kotlin.bom))
        implementation(platform(libs.protobuf.bom))
        implementation(platform(libs.springCloudDependencies.bom))
        implementation(platform(libs.springBootDependencies.bom))
    }
}

