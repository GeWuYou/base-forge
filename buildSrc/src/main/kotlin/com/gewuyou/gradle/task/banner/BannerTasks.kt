package com.gewuyou.gradle.task.banner

import groovy.transform.Internal
import org.gradle.api.DefaultTask
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

/**
 *横幅任务
 *
 * @since 2025-03-30 14:09:22
 * @author gewuyou
 */
abstract class BannerExtension {
    /**
     *  默认banner文件路径
     */
    var defaultSourcePath: String = "resources/banner.txt"
}

abstract class CopyBannerTask : DefaultTask() {
    @get:InputFile
    abstract val sourceBanner: RegularFileProperty

    @get:OutputFile
    abstract val targetBanner: RegularFileProperty

    @TaskAction
    fun execute() {
        if (targetBanner.get().asFile.exists()) {
            logger.lifecycle("banner.txt 已存在，跳过复制")
            return
        }
        sourceBanner.get().asFile.copyTo(targetBanner.get().asFile)
        logger.lifecycle("复制完成到 ${targetBanner.get().asFile.parent}")
    }
}

abstract class CleanBannerTask : DefaultTask() {
    @get:Internal
    abstract val targetBanner: RegularFileProperty

    @TaskAction
    fun execute() {
        if (!targetBanner.get().asFile.exists()) {
            logger.lifecycle("banner.txt 不存在，无需删除")
            return
        }
        if (targetBanner.get().asFile.delete()) {
            logger.lifecycle("已删除 banner.txt")
        } else {
            logger.error("删除 banner.txt 失败")
        }
    }
}