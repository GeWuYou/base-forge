package com.gewuyou.baseforge.task.banner

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.register


/**
 *横幅任务插件
 *
 * @since 2025-03-30 14:12:58
 * @author gewuyou
 */
class BannerTaskPlugin : Plugin<Project> {
    /**
     * Apply this plugin to the given target object.
     *
     * @param project The target object
     */
    override fun apply(project: Project) {
        // 1. 创建配置扩展
        val bannerExtension = project.extensions.create<BannerExtension>("bannerExtension")
        // 2. 只有匹配的项目才注册任务
        if (bannerProjects.contains(project.name)) {
            project.tasks.register<CopyBannerTask>("copyBanner") {
                group = "banner"
                description = "复制 banner 文件到资源目录"
                sourceBanner.set(project.rootProject.file(bannerExtension.defaultSourcePath))
                targetBanner.set(project.file("src/main/resources/banner.txt"))
            }

            project.tasks.register<CleanBannerTask>("cleanBanner") {
                group = "banner"
                description = "清理 banner 文件"
                targetBanner.set(project.file("src/main/resources/banner.txt"))
            }
        }
    }
}