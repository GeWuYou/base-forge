package com.gewuyou.gradle.task.banner

/**
 *横幅配置
 *
 * @since 2025-03-30 14:22:09
 * @author gewuyou
 */
val bannerProjects: Set<String> = setOf(
    "base-forge-authentication",
    "base-forge-authorization",
    // 8081 端口
    "base-forge-user",
    // 'base-forge-admin',
    // 'base-forge-admin-api',
    // 8082 端口
    "base-forge-gateway",
    // 8083 端口
    "base-forge-search",
    // 8084 端口
    "base-forge-log",
    // 8085 端口
    "base-forge-scheduler",
    // 8086 端口
    "base-forge-notification",
    // 8087 端口
    "base-forge-file",
    // 8088 端口
    "base-forge-code-generator",
    // 8761 端口
    "base-forge-discovery",
    // 8888 端口
    "base-forge-config",
    "base-forge-data-dictionary",
)