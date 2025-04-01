dependencies {
    val libs = rootProject.libs
    // 授权api模块依赖
    implementation (project(":base-forge-authorization-api"))
    // 缓存模块依赖
    implementation(libs.springBootStarter.baseforge.redis)
    // 字典api模块依赖
    implementation( project(":base-forge-data-dictionary-api"))
    // 核心模块依赖
    implementation(libs.springBootStarter.baseforge.core)
    // i18n模块依赖
    implementation(libs.springBootStarter.baseforge.i18n)
    // web 模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.web.all)
    // 配置中心客户端依赖
    implementation(libs.bundles.springCloudStarter.configClient)
    // 注册中心客户端依赖
    implementation(libs.springCloudStarter.netflix.eureka.client)
    // 认证模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.authentication.all)
    // 公共模块依赖
    implementation(project(":base-forge-common"))
}
