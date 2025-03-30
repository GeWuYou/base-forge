// settings.gradle.kts
rootProject.name = "base-forge"
include(
    "base-forge-common",
    "base-forge-authentication",
    "base-forge-authorization",
    "base-forge-authorization-api",
    // 8081 端口
    "base-forge-user",
    "base-forge-user-api",
    // 'base-forge-admin',
    // 'base-forge-admin-api',
    // 8082 端口
    "base-forge-gateway",
    // 8083 端口
    "base-forge-search",
    // 8084 端口
    "base-forge-log",
    "base-forge-log-api",
    // 8085 端口
    "base-forge-scheduler",
    // 8086 端口
    "base-forge-notification",
    "base-forge-notification-api",
    // 8087 端口
    "base-forge-file",
    "base-forge-file-api",
    // 8088 端口
    "base-forge-code-generator",
    // 8761 端口
    "base-forge-discovery",
    // 8888 端口
    "base-forge-config",
    // "base-forge-config-api",
    "base-forge-data-dictionary",
    "base-forge-data-dictionary-api"
)
pluginManagement {
    repositories {
        mavenLocal()          // 从本地仓库获取插件
        gradlePluginPortal()  // 从 Gradle 插件门户获取插件
        mavenCentral()        // 从 Maven 中央仓库获取依赖
    }
}