package com.gewuyou.gradle.jib

import com.gewuyou.gradle.jib.config.jibProjects
import com.gewuyou.gradle.jib.extension.configureJib
import org.gradle.api.Plugin
import org.gradle.api.Project


/**
 *JIB插件
 *
 * @since 2025-03-30 12:11:23
 * @author gewuyou
 */
class JibAutoPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // 只有匹配的模块才会实际应用JIB插件
        if (jibProjects.any { it.projectName == project.name }) {
            project.plugins.apply("com.google.cloud.tools.jib")
            // 调用配置逻辑
            project.configureJib(jibProjects)
        }
    }
}