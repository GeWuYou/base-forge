// buildSrc/build.gradle.kts
plugins {
    `kotlin-dsl`             // 核心：启用 Kotlin DSL 支持
    id("java-gradle-plugin") // 发布自定义插件
}
gradlePlugin {
    plugins {
        register("jib-auto-plugin") {
            id = "com.gewuyou.gradle.plugin.jib.auto"
            implementationClass = "com.gewuyou.gradle.jib.JibAutoPlugin"
            description =
                "如果需要添加新的Jib打包项目，只需要在com.gewuyou.gradle.jib.config.JibAutoConfig中添加配置即可"
        }
        register("banner-task") {
            id = "com.gewuyou.gradle.plugin.task.banner"
            implementationClass = "com.gewuyou.gradle.task.banner.BannerTaskPlugin"
            description = "自定义gradle任务，用于生成和移除banner"
        }
        register("gradle-file-task") {
            id = "com.gewuyou.gradle.plugin.task.gradle.file"
            implementationClass = "com.gewuyou.gradle.task.gradle.GradleFileTaskPlugin"
            description = "自定义gradle文件操作任务，用于操作Gradle文件"
        }
    }
}
apply {
    from("dependenciesManagement.gradle")
}
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
repositories {
    mavenLocal()          // 从本地仓库获取插件
    gradlePluginPortal()  // 从 Gradle 插件门户获取插件
    mavenCentral()        // 从 Maven 中央仓库获取依赖
}