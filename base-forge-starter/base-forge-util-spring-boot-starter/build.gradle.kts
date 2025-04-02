dependencies {
    val libs = rootProject.libs

    // Base Forge Modules (Compile Only)
    compileOnly(project(":base-forge-json-spring-boot-starter"))
    compileOnly(project(":base-forge-core-spring-boot-starter"))
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))

    // SpringDoc OpenAPI
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    // UserAgent Utilities
    // https://mvnrepository.com/artifact/eu.bitwalker/UserAgentUtils
    implementation(libs.userAgentUtils)

    // Jackson Bundle
    implementation(libs.bundles.jacksonAll)
}