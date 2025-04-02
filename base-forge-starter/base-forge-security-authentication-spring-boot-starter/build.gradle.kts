dependencies {
    val libs = rootProject.libs

    // Spring Security Starter (Compile Only)
    compileOnly(libs.springBootStarter.security)

    // Base Forge Modules (Compile Only)
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-core-spring-boot-starter"))
    compileOnly(project(":base-forge-json-spring-boot-starter"))
}