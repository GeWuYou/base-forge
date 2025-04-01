
dependencies {
    val libs = rootProject.libs
    implementation (libs.springCloudStarter.gateway)
    // SpringbootStarter Actuator 监控模块依赖
    implementation(libs.springBootStarter.actuator)
    // 授权api模块依赖
    implementation (project(":base-forge-authorization-api"))
    // 工具模块依赖
    implementation(libs.springBootStarter.baseforge.util)
    // 核心模块依赖
    implementation(libs.springBootStarter.baseforge.core)
    // 配置中心客户端依赖
    implementation(libs.bundles.springCloudStarter.configClient)
    // 注册中心客户端依赖
    implementation(libs.springCloudStarter.netflix.eureka.client)
    // 认证模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.authorization.all)
}

