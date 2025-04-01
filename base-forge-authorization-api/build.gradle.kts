
dependencies {
    val libs = rootProject.libs
    // Spring Cloud OpenFeign 依赖
    implementation(libs.springCloudStarter.openfeign)
    // 公共模块依赖
    implementation(project(":base-forge-common"))
}
