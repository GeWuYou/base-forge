// buildSrc/build.gradle.kts
plugins {
    // 核心：启用 Kotlin DSL 支持
    `kotlin-dsl`
    alias {
        libs.plugins.kotlin.jvm
        libs.plugins.java.gradle.plugin
    }
}
dependencies {
    // 导入 jib 插件依赖
    implementation(libs.comGoogleCloud.tools.jib.gradle.plugin)
}
gradlePlugin {
    plugins {
        register("jib-plugin") {
            id = "com.gewuyou.baseforge.plugin.jib"
            implementationClass = "com.gewuyou.baseforge.plugin.jib.JibPlugin"
            description =
                "如果需要添加新的Jib打包项目，只需要在com.gewuyou.gradle.jib.config.JibAutoConfig中添加配置即可"
        }
        register("banner-task") {
            id = "com.gewuyou.baseforge.plugin.task.banner"
            implementationClass = "com.gewuyou.baseforge.task.banner.BannerTaskPlugin"
            description = "自定义gradle任务，用于生成和移除banner"
        }
        register("gradle-file-task") {
            id = "com.gewuyou.baseforge.plugin.task.gradle.file"
            implementationClass = "com.gewuyou.baseforge.task.gradle.GradleFileTaskPlugin"
            description = "自定义gradle文件操作任务，用于操作Gradle文件"
        }
    }
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