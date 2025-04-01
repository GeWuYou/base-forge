
dependencies {
    val libs = rootProject.libs
    // web 模块依赖
    implementation(libs.bundles.springBootStarter.baseforge.web.all)
    // https://mvnrepository.com/artifact/cn.easy-es/easy-es-boot-starter
    implementation (libs.easy.es)

}
