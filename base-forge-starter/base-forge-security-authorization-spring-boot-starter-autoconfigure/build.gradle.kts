dependencies {
    val libs = rootProject.libs
    // webflux
    implementation(libs.springBootStarter.webflux)

    // JWT
    implementation(libs.bundles.jjwtAll)
    // Base Forge Implementation Modules
    implementation(project(":base-forge-web-spring-boot-starter"))
    implementation(project(":base-forge-json-spring-boot-starter"))
    implementation(project(":base-forge-redis-spring-boot-starter"))
    implementation(project(":base-forge-security-authentication-spring-boot-starter"))
    // Base Forge API Modules
    api(project(":base-forge-request-trace-spring-boot-starter"))
    api(project(":base-forge-jwt-spring-boot-starter"))
    // Spring Security
    api(libs.springBootStarter.security)

    // Base Forge Compile Only Modules
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-core-spring-boot-starter"))
    compileOnly(project(":base-forge-security-authorization-spring-boot-starter"))
}