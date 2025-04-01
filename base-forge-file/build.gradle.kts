
dependencies {
    val libs = rootProject.libs
    // web 模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.web.all)
    // 配置中心客户端依赖
    implementation(libs.bundles.springCloudStarter.configClient)
}
