dependencies {
    val libs = rootProject.libs

    // Security
    api(libs.springBootStarter.security)
    api(project(":base-forge-jwt-spring-boot-starter"))

    // Base Forge Core Modules (Compile Only)
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-core-spring-boot-starter"))
    compileOnly(project(":base-forge-web-spring-boot-starter"))
    compileOnly(project(":base-forge-security-authentication-spring-boot-starter"))

    // Base Forge Implementation Modules
    implementation(project(":base-forge-redis-spring-boot-starter"))
    implementation(project(":base-forge-json-spring-boot-starter"))

    // Commons Lang3
    implementation(libs.commons.lang3)

    // JWT Bundle
    implementation(libs.bundles.jjwtAll)
}