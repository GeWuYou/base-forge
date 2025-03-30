package com.gewuyou.gradle.jib.extension

import com.gewuyou.gradle.jib.entity.JibProject
import com.google.cloud.tools.jib.gradle.JibExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 *项目扩展
 *
 * @since 2025-03-30 01:40:10
 * @author gewuyou
 */
fun Project.configureJib(jibProjects: List<JibProject>) {
    val jibProject = jibProjects.find { it.projectName == project.name }
    jibProject?.let {
        extensions.configure<JibExtension> {
            from {
                image = "eclipse-temurin:21-jdk"
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