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
    val projectName: String,
    val ports: List<String> = listOf("8080"),
    val environment: Map<String, String> = mapOf("SPRING_PROFILES_ACTIVE" to "prod"),
    val entrypoint: List<String> = emptyList(),
    val paths: List<String> = listOf("../scripts"),
    val imageName: String = projectName,
    val version: String = "latest",
    val permissions: Map<String, String> = mapOf("/scripts/entrypoint.sh" to "755")
)
