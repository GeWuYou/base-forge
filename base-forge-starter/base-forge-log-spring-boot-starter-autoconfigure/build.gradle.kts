dependencies {
    val libs = rootProject.libs

    // Spring Boot AOP Starter
    implementation(libs.springBootStarter.aop)

    // SpringDoc OpenAPI
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    // Base Forge modules
    implementation(project(":base-forge-i18n-spring-boot-starter"))
    implementation(project(":base-forge-util-spring-boot-starter"))
    implementation(project(":base-forge-log-spring-boot-starter"))
}