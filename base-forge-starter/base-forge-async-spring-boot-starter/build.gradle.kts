dependencies {
    val libs = rootProject.libs

    // 项目依赖
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-core-spring-boot-starter"))

    // Spring Boot Starter AOP
    compileOnly(libs.springBootStarter.aop)

    // Kotlin 协程核心库
    compileOnly(libs.kotlinx.coroutines.core)
}