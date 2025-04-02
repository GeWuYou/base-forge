dependencies {
    val libs = rootProject.libs

    // Spring Data Redis Starter
    implementation(libs.springBootStarter.data.redis)

    // Base Forge Core Modules (Compile Only)
    compileOnly(project(":base-forge-core-spring-boot-starter"))
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-json-spring-boot-starter"))
}