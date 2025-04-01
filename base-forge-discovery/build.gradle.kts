dependencies {
    val libs = rootProject.libs
    implementation(libs.springCloudStarter.netflix.eureka.server)
    // 核心模块依赖
    implementation(libs.springBootStarter.baseforge.core)
    // 配置中心客户端依赖
    implementation(libs.bundles.springCloudStarter.configClient)
}
