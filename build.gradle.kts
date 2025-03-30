
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
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
    alias(libs.plugins.kotlin.plugin.spring)
    alias(libs.plugins.kotlin.plugin.lombok)
    alias(libs.plugins.kotlin.plugin.jpa)
}

// 排除根项目的 Kotlin 标准库依赖
configurations.implementation {
    exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib")
}

val configDir = "$rootDir/config/"
apply(plugin = "com.gewuyou.gradle.plugin.task.gradle.file")

subprojects {
    apply(from = "$configDir/repositories.gradle")
    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "com.google.protobuf")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.jetbrains.kotlin.plugin.lombok")
    apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
    // 自定义插件
    apply(plugin = "com.gewuyou.gradle.plugin.jib.auto")
    apply(plugin = "com.gewuyou.gradle.plugin.task.banner")

    // 应用配置脚本
    apply(from = "$configDir/repositories.gradle")
    apply(from = "$configDir/dependencyManagement.gradle")

    group = "com.gewuyou"
    version = "0.1.0"

    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
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
        val compileOnly by getting {
            extendsFrom(configurations["annotationProcessor"])
        }
    }

    // 项目引用定义
    val baseForgeAuthentication = project(":base-forge-authentication")
    val baseForgeAuthorization = project(":base-forge-authorization")
    val baseForgeAuthorizationApi = project(":base-forge-authorization-api")
    val baseForgeDataDictionary = project(":base-forge-data-dictionary")
    val baseForgeDataDictionaryApi = project(":base-forge-data-dictionary-api")
    val baseForgeUser = project(":base-forge-user")
    val baseForgeUserApi = project(":base-forge-user-api")
    val baseForgeCommon = project(":base-forge-common")
    val baseForgeConfig = project(":base-forge-config")
    val baseForgeDiscovery = project(":base-forge-discovery")
    val baseForgeFile = project(":base-forge-file")
    val baseForgeGateway = project(":base-forge-gateway")
    val baseForgeLog = project(":base-forge-log")
    val baseForgeLogApi = project(":base-forge-log-api")
    val baseForgeScheduler = project(":base-forge-scheduler")
    val baseForgeSearch = project(":base-forge-search")
    val baseForgeNotification = project(":base-forge-notification")
    val baseForgeNotificationApi = project(":base-forge-notification-api")
    val baseForgeCodeGenerator = project(":base-forge-code-generator")
    // region 设置子模块需要的依赖
    // 设置需要 actuator 依赖的模块
    val actuatorProjects = setOf(baseForgeGateway)

    // 设置需要grpc基本依赖的模块
    val grpcBaseProjects = setOf(baseForgeCommon)

    // 设置需要grpc客户端依赖的模块
    val grpcClientProjects = setOf(
        baseForgeLogApi,
        // baseForgeAdminApi
    )

    // 设置需要grpc服务端依赖的模块
    val grpcServerProjects = setOf(
        // 9090
        // baseForgeAdmin,
        // 9091
        baseForgeLog,
        // 9093
    )

    // 设置需要公共模块依赖的模块
    val commonProjects = setOf(
        baseForgeAuthentication,
        baseForgeAuthorization,
        baseForgeAuthorizationApi,
        baseForgeDataDictionary,
        baseForgeDataDictionaryApi,
        // baseForgeAdmin,
        // baseForgeAdminApi,
        baseForgeFile,
        baseForgeLog,
        baseForgeLogApi,
        baseForgeScheduler,
        baseForgeSearch,
        baseForgeNotification,
        baseForgeNotificationApi,
        baseForgeCodeGenerator
    )

    // 设置需要启用配置提示处理的模块
    val configProcessorProjects = emptySet<Project>()

    // 设置需要导入SpringDoc依赖的模块
    val springDocProjects = setOf(
        baseForgeCommon,
        baseForgeDataDictionary,
        baseForgeNotification,
        baseForgeScheduler,
        baseForgeUser,
        baseForgeCodeGenerator
    )

    // 设置需要导入日志服务的模块
    val logProjects = emptySet<Project>()

    // 设置需要导入MybatisPlus依赖的模块
    val mybatisPlusProjects = setOf(baseForgeCommon)

    // 设置需要导入Jpa依赖的模块
    val jpaProjects = setOf(
        baseForgeUser,
        baseForgeAuthorization,
        baseForgeDataDictionary,
        baseForgeCommon,
        baseForgeCodeGenerator
    )

    // 设置需要引入redis依赖的模块
    val redisProjects = setOf(baseForgeDataDictionary)

    // 设置需要导入postgresql依赖的模块
    val postgresqlProjects = setOf(
        baseForgeCommon,
        baseForgeUser,
        baseForgeAuthorization,
        baseForgeDataDictionary,
        baseForgeCodeGenerator
    )

    // 设置需要导入尤里卡客户端依赖的模块
    val eurekaClientProjects = setOf(
        // baseForgeAdmin,
        baseForgeFile,
        baseForgeGateway,
        baseForgeLog,
        baseForgeScheduler,
        baseForgeSearch,
        baseForgeNotification,
        baseForgeUser,
        baseForgeAuthentication,
        baseForgeAuthorization,
        baseForgeDataDictionary,
        baseForgeCodeGenerator
    )

    // 设置需要导入配置中心客户端依赖的模块
    val configClientProjects = setOf(
        // baseForgeAdmin,
        baseForgeDiscovery,
        baseForgeFile,
        baseForgeGateway,
        baseForgeLog,
        baseForgeScheduler,
        baseForgeSearch,
        baseForgeNotification,
        baseForgeUser,
        baseForgeAuthentication,
        baseForgeAuthorization,
        baseForgeDataDictionary,
        baseForgeCodeGenerator
    )

    // 设置需要导入openfeign依赖的模块
    val openFeignProjects = setOf(
        baseForgeNotificationApi,
        // baseForgeAdminApi,
        baseForgeLogApi,
        baseForgeUserApi,
        baseForgeAuthorizationApi,
        baseForgeDataDictionaryApi
    )

    // 设置需要导入rabbitmq依赖的模块
    val rabbitmqProjects = setOf(
        baseForgeNotification,
        baseForgeNotificationApi
    )

    // 设置需要导入security依赖的模块
    val securityProjects = emptySet<Project>()

    // 设置需要字典服务的模块
    val dataDictionaryProjects = setOf(baseForgeAuthentication)

    // 设置需要导入授权服务的模块
    val authorizationProjects = setOf(
        baseForgeAuthentication,
        baseForgeGateway
    )

    // region base-forge-spring-boot-starter
    // 设置需要导入Async starter依赖的模块
    val asyncStarterProjects = setOf(baseForgeDataDictionary)

    // 设置需要导入webStarter依赖的模块
    val webStarterProjects = setOf(
        // baseForgeAdmin,
        baseForgeFile,
        baseForgeLog,
        baseForgeScheduler,
        baseForgeSearch,
        baseForgeNotification,
        baseForgeUser,
        baseForgeAuthentication,
        baseForgeAuthorization,
        baseForgeDataDictionary,
        baseForgeCodeGenerator
    )

    // 设置需要导入redisStarter依赖的模块
    val redisStarterProjects = setOf(
        baseForgeAuthentication,
        baseForgeAuthorization
    )

    // 设置需要导入security 认证的模块
    val securityStarterProjects = setOf(baseForgeAuthentication)

    // 设置需要导入security 授权的模块
    val securityAuthorizationProjects = setOf(baseForgeGateway)

    // 设置需要导入grpcStarter依赖的模块
    val grpcStarterProjects = setOf(
        // baseForgeAdminApi,
        baseForgeLogApi,
        baseForgeNotificationApi
    )

    // 设置需要导入utilStarter依赖的模块
    val utilStarterProjects = setOf(
        baseForgeDataDictionary,
        baseForgeGateway
    )

    // 设置需要导入i18nStarter依赖的模块
    val i18nStarterProjects = setOf(
        baseForgeDataDictionary,
        baseForgeAuthentication,
        baseForgeAuthorization,
        baseForgeCodeGenerator
    )

    // 设置需要导入coreStarter依赖的模块
    val coreStarterProjects = setOf(
        baseForgeDataDictionary,
        baseForgeConfig,
        baseForgeGateway,
        baseForgeDiscovery,
        baseForgeAuthentication,
        baseForgeAuthorization,
        baseForgeCodeGenerator
    )

    // 设置需要导入mybatisExtensionStarter依赖的模块
    val mybatisExtensionStarterProjects = setOf(
        baseForgeUser,
        baseForgeAuthorization,
        baseForgeDataDictionary,
        baseForgeLog,
        baseForgeScheduler,
        // baseForgeAdmin,
        baseForgeUser,
        baseForgeCodeGenerator
    )

    // 导入kt的协程
    val coroutineStarterProjects = setOf(
        baseForgeDataDictionary,
        baseForgeCodeGenerator
    )
    // endregion

    dependencies {
        // 公共依赖
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        // 条件依赖
        if (authorizationProjects.contains(project)) {
            implementation(baseForgeAuthorizationApi)
        }
        if (redisStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-redis-spring-boot-starter")
        }
        if (dataDictionaryProjects.contains(project)) {
            implementation(baseForgeDataDictionaryApi)
        }
        if (coroutineStarterProjects.contains(project)) {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
        }
        if (asyncStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-async-spring-boot-starter")
        }
        if (utilStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-util-spring-boot-starter")
        }
        if (coreStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-core-spring-boot-starter")
        }
        if (i18nStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-i18n-spring-boot-starter")
        }
        if (redisProjects.contains(project)) {
            implementation("org.springframework.boot:spring-boot-starter-data-redis")
        }
        if (actuatorProjects.contains(project)) {
            implementation("org.springframework.boot:spring-boot-starter-actuator")
        }
        if (grpcStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-grpc-spring-boot-starter")
        }
        if (webStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-web-spring-boot-starter-autoconfigure")
            implementation("com.gewuyou:base-forge-web-spring-boot-starter")
        }
        if (securityProjects.contains(project)) {
            implementation("org.springframework.boot:spring-boot-starter-security")
        }
        if (configClientProjects.contains(project)) {
            implementation("org.springframework.cloud:spring-cloud-starter-config")
            implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")
        }
        if (eurekaClientProjects.contains(project)) {
            implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
        }
        if (configProcessorProjects.contains(project)) {
            annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
        }
        if (springDocProjects.contains(project)) {
            implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui")
        }
        if (commonProjects.contains(project)) {
            implementation(baseForgeCommon)
        }
        if (grpcBaseProjects.contains(project)) {
            implementation("io.grpc:grpc-netty")
            implementation("io.grpc:grpc-protobuf")
            implementation("io.grpc:grpc-stub")
            implementation("org.apache.tomcat:annotations-api")
        }
        if (grpcClientProjects.contains(project)) {
            runtimeOnly("io.grpc:grpc-netty-shaded")
            implementation("io.grpc:grpc-netty")
            implementation("io.grpc:grpc-protobuf")
            implementation("io.grpc:grpc-stub")
            implementation("io.grpc:grpc-services")
            implementation("org.apache.tomcat:annotations-api")
            implementation("net.devh:grpc-client-spring-boot-starter")
            implementation("com.google.protobuf:protobuf-java")
            implementation("com.google.protobuf:protobuf-java-util")
        }
        if (grpcServerProjects.contains(project)) {
            runtimeOnly("io.grpc:grpc-netty-shaded")
            implementation("io.grpc:grpc-netty")
            implementation("io.grpc:grpc-protobuf")
            implementation("io.grpc:grpc-stub")
            implementation("io.grpc:grpc-services")
            implementation("org.apache.tomcat:annotations-api")
            implementation("net.devh:grpc-server-spring-boot-starter")
            implementation("com.google.protobuf:protobuf-java")
            implementation("com.google.protobuf:protobuf-java-util")
        }
        if (logProjects.contains(project)) {
            implementation(baseForgeLogApi)
        }
        if (mybatisPlusProjects.contains(project)) {
            implementation("com.baomidou:mybatis-plus-spring-boot3-starter")
        }
        if (openFeignProjects.contains(project)) {
            implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
        }
        if (rabbitmqProjects.contains(project)) {
            implementation("org.springframework.boot:spring-boot-starter-amqp")
            testImplementation("org.springframework.amqp:spring-rabbit-test")
        }
        if (postgresqlProjects.contains(project)) {
            runtimeOnly("org.postgresql:postgresql")
        }
        if (jpaProjects.contains(project)) {
            implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        }
        if (mybatisExtensionStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-mybatis-extension-spring-boot-starter")
        }
        if (securityStarterProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-security-authentication-spring-boot-starter")
            implementation("com.gewuyou:base-forge-security-authentication-spring-boot-starter-autoconfigure")
        }
        if (securityAuthorizationProjects.contains(project)) {
            implementation("com.gewuyou:base-forge-security-authorization-spring-boot-starter")
            implementation("com.gewuyou:base-forge-security-authorization-spring-boot-starter-autoconfigure")
        }
    }

}