dependencies {
    val libs = rootProject.libs

    // Base Forge Modules (Compile Only)
    compileOnly(project(":base-forge-i18n-spring-boot-starter"))
    compileOnly(project(":base-forge-util-spring-boot-starter"))
    compileOnly(project(":base-forge-redis-spring-boot-starter"))
    compileOnly(project(":base-forge-json-spring-boot-starter"))
    compileOnly(project(":base-forge-core-spring-boot-starter"))
    compileOnly(project(":base-forge-request-trace-spring-boot-starter"))
    compileOnly(project(":base-forge-web-spring-boot-starter"))

    // Spring Boot Starters (Compile Only)
    compileOnly(libs.springBootStarter.aop)
    compileOnly(libs.springBootStarter.validation)

    // Redisson Starter (Compile Only)
    compileOnly(libs.redisson)

    // Apache Commons (Compile Only)
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    compileOnly(libs.commons.lang3)
}