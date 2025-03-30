package com.gewuyou.gradle.task.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.Delete
import org.gradle.kotlin.dsl.register

/**
 *Gradle文件任务插件
 *
 * @since 2025-03-30 17:09:40
 * @author gewuyou
 */
class GradleFileTaskPlugin : Plugin<Project> {
    /**
     * Apply this plugin to the given target object.
     *
     * @param project The target object
     */
    override fun apply(project: Project) {
        project.tasks.register<Delete>("cleanGradleFiles") {
            group = "gradleFile"
            description = "删除当前子模块的 Gradle 相关的无用文件，如果当前模块是根模块，则会扫描所有子模块"
            val logger = project.logger
            val targetsToDelete = mutableListOf<Any>()
            // 判断当前是导入给哪个模块，如果是根模块，则扫描所有子模块,如果是子模块，则只扫描当前模块
            val projectsToScan = if (project == project.rootProject) project.subprojects else listOf(project)
            projectsToScan.forEach { proj ->
                val projectDir = proj.projectDir
                logger.info("扫描项目 ${proj.name} (路径: $projectDir)")
                listOf(
                    "gradlew",
                    "gradlew.bat",
                    "gradle/wrapper/gradle-wrapper.jar",
                    "gradle/wrapper/gradle-wrapper.properties",
                    ".gradle"
                ).map { path -> project.file("${projectDir}/$path") }
                    .filter { it.exists() }
                    .forEach {
                        targetsToDelete.add(it)
                        logger.info("✅ 标记待删除: $it")
                    }
            }

            delete(targetsToDelete)

            doFirst {
                if (targetsToDelete.isEmpty()) {
                    logger.lifecycle("🔍 未找到任何Gradle相关文件可清理")
                } else {
                    logger.lifecycle("🗑️ 将删除以下文件/目录:")
                    targetsToDelete.forEach { file ->
                        logger.lifecycle("  - $file")
                    }
                }
            }

            doLast {
                logger.lifecycle("♻️ Gradle文件清理完成")
            }
        }
    }

}