package com.gewuyou.baseforge.plugin.jib.entity


/**
 * Jib 项目配置类
 * @author gewuyou
 * @date 2025/03/30
 * @constructor 创建[JibProject]
 * @param [projectName] 项目名称
 * @param [ports] 监听端口
 * @param [environment] 环境
 * @param [entrypoint] 入口点(用于自定义启动命令)
 * @param [imageName] 图像名称
 * @param [version] 版本
 * @param [permissions] 权限
 */
data class JibProject(
    var projectName: String = "",
    var ports: List<String> = listOf("8080"),
    var environment: Map<String, String> = mapOf("SPRING_PROFILES_ACTIVE" to "prod"),
    var entrypoint: List<String> = emptyList(),
    var paths: List<String> = listOf("../scripts"),
    var imageName: String = projectName,
    var version: String = "latest",
    var permissions: Map<String, String> = mapOf("/scripts/entrypoint.sh" to "755"),
    var baseImage: String = "eclipse-temurin:21-jdk"
)
