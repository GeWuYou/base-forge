
dependencies {
    val libs = rootProject.libs
    // web 模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.web.all)
    // SpringBootStarter 邮件模块依赖
    implementation(libs.springBootStarter.mail)
    // SpringBootStarter Thymeleaf 模块依赖
    implementation(libs.springBootStarter.thymeleaf)
}
