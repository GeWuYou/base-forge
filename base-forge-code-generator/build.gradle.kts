dependencies {
    val libs = rootProject.libs
    // pgSQL 数据库驱动
    runtimeOnly(libs.postgresql)
    // https://mvnrepository.com/artifact/com.baomidou/mybatis-plus-generator
    implementation(libs.mybatis.plus.generator)
    // Kotlin 协程依赖
    implementation(libs.kotlinx.coroutines.core)
    // 核心模块依赖
    implementation(libs.springBootStarter.baseforge.core)
    // i18n模块依赖
    implementation(libs.springBootStarter.baseforge.i18n)
    // web 模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.web.all)
    // 配置中心客户端依赖
    implementation(libs.bundles.springCloudStarter.configClient)
    // Springdoc OpenApi
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    // 注册中心客户端依赖
    implementation(libs.springCloudStarter.netflix.eureka.client)
    // 公共模块依赖
    implementation(project(":base-forge-common"))
    // Mybatis 扩展模块 依赖
    implementation(libs.springBootStarter.baseforge.mybatis.extension)
    // SpringBootStarter Data JPA
    implementation(libs.springBootStarter.data.jpa)
    // pgsql 数据库驱动依赖
    runtimeOnly(libs.postgresql)
}