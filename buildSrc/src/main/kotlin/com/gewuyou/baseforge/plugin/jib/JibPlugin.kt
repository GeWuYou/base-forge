package com.gewuyou.baseforge.plugin.jib


import com.gewuyou.baseforge.plugin.jib.entity.JibProject
import com.google.cloud.tools.jib.gradle.JibExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 *JIB插件
 *
 * @since 2025-03-30 12:11:23
 * @author gewuyou
 */
class JibPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        // 创建扩展
        val extension = project.extensions.create("jibConfig", JibPluginExtension::class.java)
        project.afterEvaluate {
            // 只有匹配的模块才会实际应用JIB插件
            if (extension.projects.any { it.projectName == project.name }) {
                project.plugins.apply("com.google.cloud.tools.jib")
                // 调用配置逻辑
                project.configureJib(extension.projects)
            }
        }
    }

    // 新增扩展类
    open class JibPluginExtension {
        val projects = mutableListOf<JibProject>()
        fun project(configure: JibProject.() -> Unit) {
            projects.add(JibProject().apply(configure))
        }
    }

    /**
     *项目扩展
     *
     * @since 2025-03-30 01:40:10
     * @author gewuyou
     */
    private fun Project.configureJib(jibProjects: List<JibProject>) {
        val jibProject = jibProjects.find { it.projectName == project.name }
        jibProject?.let {
            extensions.configure<JibExtension> {
                from {
                    image = jibProject.baseImage
                }
                to {
                    image =
                        "${System.getenv("IMAGE_REGISTRY") ?: "localhost:5000"}/${jibProject.imageName}:${jibProject.version}"
                    auth {
                        username = "root"
                        password = System.getenv("PRIVATE_DOCKER_REGISTRY_PASSWORD") ?: ""
                    }
                }
                // 动态配置容器参数
                container {
                    ports = jibProject.ports
                    environment = jibProject.environment
                    if (jibProject.entrypoint.isNotEmpty()) {
                        entrypoint = jibProject.entrypoint
                    }
                }

                // 动态配置额外目录
                extraDirectories {
                    setPaths(jibProject.paths)
                    permissions.set(jibProject.permissions)
                }
                // 将动态部分移到任务配置中
                tasks.named("jib").configure {
                    doFirst {
                        // 只有在实际执行jib任务时才会打印日志
                        println("jibProject: $jibProject")
                    }
                }
            }
        }
    }
}