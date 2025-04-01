
dependencies {
    val libs = rootProject.libs
    // Kotlin 协程依赖
    implementation(libs.kotlinx.coroutines.core)
    // 异步模块依赖
    implementation(libs.springBootStarter.baseforge.async)
    // 工具模块依赖
    implementation(libs.springBootStarter.baseforge.util)
    // 核心模块依赖
    implementation(libs.springBootStarter.baseforge.core)
    // i18n模块依赖
    implementation(libs.springBootStarter.baseforge.i18n)
    // 缓存模块依赖
    implementation(libs.springBootStarter.data.redis)
    // web 模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.web.all)
    // 配置中心客户端依赖
    implementation(libs.bundles.springCloudStarter.configClient)
    // Springdoc OpenApi
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    // 注册中心客户端依赖
    implementation(libs.springCloudStarter.netflix.eureka.client)
    // SpringBootStarter Data JPA
    implementation(libs.springBootStarter.data.jpa)
    // pgsql 数据库驱动依赖
    runtimeOnly(libs.postgresql)
}
