dependencies {
    val libs = rootProject.libs
    implementation (libs.springCloudStarter.config.server)
    implementation (libs.springCloudStarter.bus.amqp)
    implementation (libs.springBootStarter.actuator)
    // 核心模块依赖
    implementation(libs.springBootStarter.baseforge.core)
}
