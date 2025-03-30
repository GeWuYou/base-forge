package com.gewuyou.gradle.jib.entity


/**
 * Jib 项目配置类
 * @author gewuyou
 * @date 2025/03/30
 * @constructor 创建[JibProject]
 * @param [projectName]
 * @param [ports]
 * @param [environment] 环境
 * @param [entrypoint] 入口点
 * @param [imageName] 图像名称
 * @param [version] 版本
 * @param [permissions] 权限
 */
data class JibProject(
    val projectName: String,
    val ports: List<String> = listOf("8080"),
    val environment: Map<String, String> = mapOf("SPRING_PROFILES_ACTIVE" to "prod"),
    val entrypoint: List<String> = emptyList(),
    val paths: List<String> = listOf("../scripts"),
    val imageName: String = projectName,
    val version: String = "latest",
    val permissions: Map<String, String> = mapOf("/scripts/entrypoint.sh" to "755")
)
