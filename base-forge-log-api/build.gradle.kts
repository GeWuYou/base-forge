
dependencies {
    val libs = rootProject.libs
    // grpc模块依赖
    implementation(libs.springBootStarter.baseforge.grpc)
    // SpringBootStarter模块依赖
    implementation(libs.springBootStarter)
    // SpringBootStarterProcessor模块依赖
    implementation(libs.springBoot.configuration.processor)
    // 日志模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.log.all)
}
